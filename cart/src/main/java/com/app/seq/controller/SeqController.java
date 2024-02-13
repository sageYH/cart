package com.app.seq.controller;

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

import com.app.seq.model.SeqExDto;
import com.app.seq.service.SeqService;
import com.base.ResponseList;
import com.base.ResponseMap;
import com.common.utils.ClassUtil;
import com.common.utils.Excel;
import com.common.utils.MapUtil;

import lombok.RequiredArgsConstructor;

/**
* 일련번호 관리
*
* @author
*
*/

@Controller
@Lazy
@RequiredArgsConstructor
public class SeqController {

	@Autowired
	private SeqService seqService;

	@RequestMapping("app/seq/seqList.do")
	public String seqList() throws Exception {
		return "app/seq/seqList";
	}

	@RequestMapping("app/popup/seq/seqPop.do")
	public String seqPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/seq/seqPop";
	}

	@RequestMapping("app/popup/seq/seqInfoPop.do")
	public String seqInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/seq/seqInfoPop";
	}

	@RequestMapping("app/popup/seq/seqSearchPop.do")
	public String seqSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/seq/seqSearchPop";
	}

	/**
	* 일련번호 List Page
	*
	* @param SeqExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/seq/getSeqPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getSeqPageList(HttpServletRequest request, @Valid final SeqExDto params) throws Exception {
		final int totalRows = this.seqService.getSeqListCount(params);
		final List<SeqExDto> seqList = this.seqService.getSeqList(params);
		
		Map<String,Object> mapData = (new ResponseList(seqList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	* 일련번호 List Inquiry
	*
	* @param SeqExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/seq/getSeqList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<SeqExDto> getSeqList(@Valid final SeqExDto params) throws Exception {
		final List<SeqExDto> listData = this.seqService.getSeqList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요 일련번호 detail infomation Inquiry
	* </pre>
	*
	* @param SeqExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/seq/getSeq.do")
	@ResponseBody
	public Object getSeq(@Valid final SeqExDto params) throws Exception {
		final SeqExDto mapData = this.seqService.getSeq(params);
		return mapData;
	}

	/**
	* 일련번호 insert
	*
	* @param SeqExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/seq/insertSeq.do")
	@ResponseBody
	public Map<String,Object> insertSeq(@Valid final SeqExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.seqService.insertSeq(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 일련번호 Update
	*
	* @param SeqExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/seq/updateSeq.do")
	@ResponseBody
	public Map<String,Object> updateSeq(@Valid final SeqExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.seqService.updateSeq(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 일련번호 delete
	*
	* @param SeqExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/seq/deleteSeq.do")
	@ResponseBody
	public Map<String,Object> deleteSeq(@Valid final SeqExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.seqService.deleteSeq(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/seq/getSeqExcelDown.do")
	@ResponseBody
	public Object getSeqExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		SeqExDto seqExDto = new SeqExDto();
		seqExDto = (SeqExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), seqExDto);
		final List<SeqExDto> listData = this.seqService.getSeqList(seqExDto);
		
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
