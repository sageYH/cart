package com.app.qna.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.qna.model.QnaExDto;
import com.app.qna.service.QnaService;
import com.base.ResponseList;
import com.base.ResponseMap;
import com.common.utils.ClassUtil;
import com.common.utils.Excel;
import com.common.utils.MapUtil;

import lombok.RequiredArgsConstructor;

/**
*  관리
*
* @author
*
*/

@Controller
@Lazy
@RequiredArgsConstructor
public class QnaController {

	@Autowired
	private QnaService qnaService;

	@RequestMapping("app/qna/qnaList.do")
	public String qnaList() throws Exception {
		return "app/qna/qnaList";
	}

	@RequestMapping("app/popup/qna/qnaPop.do")
	public String qnaPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/qna/qnaPop";
	}

	@RequestMapping("app/popup/qna/qnaInfoPop.do")
	public String qnaInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/qna/qnaInfoPop";
	}

	@RequestMapping("app/popup/qna/qnaSearchPop.do")
	public String qnaSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/qna/qnaSearchPop";
	}

	/**
	*  List Page
	*
	* @param QnaExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/qna/getQnaPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getQnaPageList(HttpServletRequest request, @Valid final QnaExDto params) throws Exception {
		final int totalRows = this.qnaService.getQnaListCount(params);
		final List<QnaExDto> qnaList = this.qnaService.getQnaList(params);
		
		Map<String,Object> mapData = (new ResponseList(qnaList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param QnaExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/qna/getQnaList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<QnaExDto> getQnaList(@Valid final QnaExDto params) throws Exception {
		final List<QnaExDto> listData = this.qnaService.getQnaList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param QnaExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/qna/getQna.do")
	@ResponseBody
	public Object getQna(@Valid final QnaExDto params) throws Exception {
		final QnaExDto mapData = this.qnaService.getQna(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param QnaExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/qna/insertQna.do")
	@ResponseBody
	public Map<String,Object> insertQna(@Valid final QnaExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.qnaService.insertQna(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	*  Update
	*
	* @param QnaExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/qna/updateQna.do")
	@ResponseBody
	public Map<String,Object> updateQna(@Valid final QnaExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.qnaService.updateQna(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	*  delete
	*
	* @param QnaExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/qna/deleteQna.do")
	@ResponseBody
	public Map<String,Object> deleteQna(@Valid final QnaExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.qnaService.deleteQna(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/qna/getQnaExcelDown.do")
	@ResponseBody
	public Object getQnaExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		QnaExDto qnaExDto = new QnaExDto();
		qnaExDto = (QnaExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), qnaExDto);
		final List<QnaExDto> listData = this.qnaService.getQnaList(qnaExDto);
		
		Map<String,Object> m1 = new HashMap<String,Object>();
		m1.put("xlsName", map.get("xlsName"));
		m1.put("sheetName", map.get("sheetName"));
		m1.put("columnHeaders", map.get("columnHeaders"));
		m1.put("columnNames", map.get("columnNames"));
		try {
			Excel.downloadClsToExcel(m1, listData, response, request);
			responseMap = new ResponseMap("Y","");
		}catch(Exception e) {
			responseMap = new ResponseMap("N","download error.");
		}
		
		return responseMap.getResultMap();
	}
}
