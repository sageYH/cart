package com.app.mbrTermsAcpt.controller;

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

import com.app.mbrTermsAcpt.model.MbrTermsAcptExDto;
import com.app.mbrTermsAcpt.service.MbrTermsAcptService;
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
public class MbrTermsAcptController {

	@Autowired
	private MbrTermsAcptService mbrTermsAcptService;

	@RequestMapping("app/mbrTermsAcpt/mbrTermsAcptList.do")
	public String mbrTermsAcptList() throws Exception {
		return "app/mbrTermsAcpt/mbrTermsAcptList";
	}

	@RequestMapping("app/popup/mbrTermsAcpt/mbrTermsAcptPop.do")
	public String mbrTermsAcptPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/mbrTermsAcpt/mbrTermsAcptPop";
	}

	@RequestMapping("app/popup/mbrTermsAcpt/mbrTermsAcptInfoPop.do")
	public String mbrTermsAcptInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/mbrTermsAcpt/mbrTermsAcptInfoPop";
	}

	@RequestMapping("app/popup/mbrTermsAcpt/mbrTermsAcptSearchPop.do")
	public String mbrTermsAcptSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/mbrTermsAcpt/mbrTermsAcptSearchPop";
	}

	/**
	*  List Page
	*
	* @param MbrTermsAcptExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/mbrTermsAcpt/getMbrTermsAcptPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getMbrTermsAcptPageList(HttpServletRequest request, @Valid final MbrTermsAcptExDto params) throws Exception {
		final int totalRows = this.mbrTermsAcptService.getMbrTermsAcptListCount(params);
		final List<MbrTermsAcptExDto> mbrTermsAcptList = this.mbrTermsAcptService.getMbrTermsAcptList(params);
		
		Map<String,Object> mapData = (new ResponseList(mbrTermsAcptList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param MbrTermsAcptExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/mbrTermsAcpt/getMbrTermsAcptList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<MbrTermsAcptExDto> getMbrTermsAcptList(@Valid final MbrTermsAcptExDto params) throws Exception {
		final List<MbrTermsAcptExDto> listData = this.mbrTermsAcptService.getMbrTermsAcptList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param MbrTermsAcptExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/mbrTermsAcpt/getMbrTermsAcpt.do")
	@ResponseBody
	public Object getMbrTermsAcpt(@Valid final MbrTermsAcptExDto params) throws Exception {
		final MbrTermsAcptExDto mapData = this.mbrTermsAcptService.getMbrTermsAcpt(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param MbrTermsAcptExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/mbrTermsAcpt/insertMbrTermsAcpt.do")
	@ResponseBody
	public Map<String,Object> insertMbrTermsAcpt(@Valid final MbrTermsAcptExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.mbrTermsAcptService.insertMbrTermsAcpt(params);
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
	* @param MbrTermsAcptExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/mbrTermsAcpt/updateMbrTermsAcpt.do")
	@ResponseBody
	public Map<String,Object> updateMbrTermsAcpt(@Valid final MbrTermsAcptExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.mbrTermsAcptService.updateMbrTermsAcpt(params);
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
	* @param MbrTermsAcptExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/mbrTermsAcpt/deleteMbrTermsAcpt.do")
	@ResponseBody
	public Map<String,Object> deleteMbrTermsAcpt(@Valid final MbrTermsAcptExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.mbrTermsAcptService.deleteMbrTermsAcpt(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/mbrTermsAcpt/getMbrTermsAcptExcelDown.do")
	@ResponseBody
	public Object getMbrTermsAcptExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		MbrTermsAcptExDto mbrTermsAcptExDto = new MbrTermsAcptExDto();
		mbrTermsAcptExDto = (MbrTermsAcptExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), mbrTermsAcptExDto);
		final List<MbrTermsAcptExDto> listData = this.mbrTermsAcptService.getMbrTermsAcptList(mbrTermsAcptExDto);
		
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
