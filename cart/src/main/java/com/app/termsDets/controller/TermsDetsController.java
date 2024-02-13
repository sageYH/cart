package com.app.termsDets.controller;

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

import com.app.termsDets.model.TermsDetsExDto;
import com.app.termsDets.service.TermsDetsService;
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
public class TermsDetsController {

	@Autowired
	private TermsDetsService termsDetsService;

	@RequestMapping("app/termsDets/termsDetsList.do")
	public String termsDetsList() throws Exception {
		return "app/termsDets/termsDetsList";
	}

	@RequestMapping("app/popup/termsDets/termsDetsPop.do")
	public String termsDetsPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/termsDets/termsDetsPop";
	}

	@RequestMapping("app/popup/termsDets/termsDetsInfoPop.do")
	public String termsDetsInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/termsDets/termsDetsInfoPop";
	}

	@RequestMapping("app/popup/termsDets/termsDetsSearchPop.do")
	public String termsDetsSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/termsDets/termsDetsSearchPop";
	}

	/**
	*  List Page
	*
	* @param TermsDetsExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/termsDets/getTermsDetsPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getTermsDetsPageList(HttpServletRequest request, @Valid final TermsDetsExDto params) throws Exception {
		final int totalRows = this.termsDetsService.getTermsDetsListCount(params);
		final List<TermsDetsExDto> termsDetsList = this.termsDetsService.getTermsDetsList(params);
		
		Map<String,Object> mapData = (new ResponseList(termsDetsList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param TermsDetsExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/termsDets/getTermsDetsList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<TermsDetsExDto> getTermsDetsList(@Valid final TermsDetsExDto params) throws Exception {
		final List<TermsDetsExDto> listData = this.termsDetsService.getTermsDetsList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param TermsDetsExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/termsDets/getTermsDets.do")
	@ResponseBody
	public Object getTermsDets(@Valid final TermsDetsExDto params) throws Exception {
		final TermsDetsExDto mapData = this.termsDetsService.getTermsDets(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param TermsDetsExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/termsDets/insertTermsDets.do")
	@ResponseBody
	public Map<String,Object> insertTermsDets(@Valid final TermsDetsExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.termsDetsService.insertTermsDets(params);
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
	* @param TermsDetsExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/termsDets/updateTermsDets.do")
	@ResponseBody
	public Map<String,Object> updateTermsDets(@Valid final TermsDetsExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.termsDetsService.updateTermsDets(params);
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
	* @param TermsDetsExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/termsDets/deleteTermsDets.do")
	@ResponseBody
	public Map<String,Object> deleteTermsDets(@Valid final TermsDetsExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.termsDetsService.deleteTermsDets(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/termsDets/getTermsDetsExcelDown.do")
	@ResponseBody
	public Object getTermsDetsExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		TermsDetsExDto termsDetsExDto = new TermsDetsExDto();
		termsDetsExDto = (TermsDetsExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), termsDetsExDto);
		final List<TermsDetsExDto> listData = this.termsDetsService.getTermsDetsList(termsDetsExDto);
		
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
