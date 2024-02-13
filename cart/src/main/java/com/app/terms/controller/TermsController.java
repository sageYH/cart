package com.app.terms.controller;

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

import com.app.terms.model.TermsExDto;
import com.app.terms.service.TermsService;
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
public class TermsController {

	@Autowired
	private TermsService termsService;

	@RequestMapping("app/terms/termsList.do")
	public String termsList() throws Exception {
		return "app/terms/termsList";
	}

	@RequestMapping("app/popup/terms/termsPop.do")
	public String termsPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/terms/termsPop";
	}

	@RequestMapping("app/popup/terms/termsInfoPop.do")
	public String termsInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/terms/termsInfoPop";
	}

	@RequestMapping("app/popup/terms/termsSearchPop.do")
	public String termsSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/terms/termsSearchPop";
	}

	/**
	*  List Page
	*
	* @param TermsExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/terms/getTermsPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getTermsPageList(HttpServletRequest request, @Valid final TermsExDto params) throws Exception {
		final int totalRows = this.termsService.getTermsListCount(params);
		final List<TermsExDto> termsList = this.termsService.getTermsList(params);
		
		Map<String,Object> mapData = (new ResponseList(termsList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param TermsExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/terms/getTermsList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<TermsExDto> getTermsList(@Valid final TermsExDto params) throws Exception {
		final List<TermsExDto> listData = this.termsService.getTermsList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param TermsExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/terms/getTerms.do")
	@ResponseBody
	public Object getTerms(@Valid final TermsExDto params) throws Exception {
		final TermsExDto mapData = this.termsService.getTerms(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param TermsExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/terms/insertTerms.do")
	@ResponseBody
	public Map<String,Object> insertTerms(@Valid final TermsExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.termsService.insertTerms(params);
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
	* @param TermsExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/terms/updateTerms.do")
	@ResponseBody
	public Map<String,Object> updateTerms(@Valid final TermsExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.termsService.updateTerms(params);
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
	* @param TermsExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/terms/deleteTerms.do")
	@ResponseBody
	public Map<String,Object> deleteTerms(@Valid final TermsExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.termsService.deleteTerms(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/terms/getTermsExcelDown.do")
	@ResponseBody
	public Object getTermsExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		TermsExDto termsExDto = new TermsExDto();
		termsExDto = (TermsExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), termsExDto);
		final List<TermsExDto> listData = this.termsService.getTermsList(termsExDto);
		
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
