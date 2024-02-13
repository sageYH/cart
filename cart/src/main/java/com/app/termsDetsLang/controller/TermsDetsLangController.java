package com.app.termsDetsLang.controller;

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

import com.app.termsDetsLang.model.TermsDetsLangExDto;
import com.app.termsDetsLang.service.TermsDetsLangService;
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
public class TermsDetsLangController {

	@Autowired
	private TermsDetsLangService termsDetsLangService;

	@RequestMapping("app/termsDetsLang/termsDetsLangList.do")
	public String termsDetsLangList() throws Exception {
		return "app/termsDetsLang/termsDetsLangList";
	}

	@RequestMapping("app/popup/termsDetsLang/termsDetsLangPop.do")
	public String termsDetsLangPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/termsDetsLang/termsDetsLangPop";
	}

	@RequestMapping("app/popup/termsDetsLang/termsDetsLangInfoPop.do")
	public String termsDetsLangInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/termsDetsLang/termsDetsLangInfoPop";
	}

	@RequestMapping("app/popup/termsDetsLang/termsDetsLangSearchPop.do")
	public String termsDetsLangSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/termsDetsLang/termsDetsLangSearchPop";
	}

	/**
	*  List Page
	*
	* @param TermsDetsLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/termsDetsLang/getTermsDetsLangPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getTermsDetsLangPageList(HttpServletRequest request, @Valid final TermsDetsLangExDto params) throws Exception {
		final int totalRows = this.termsDetsLangService.getTermsDetsLangListCount(params);
		final List<TermsDetsLangExDto> termsDetsLangList = this.termsDetsLangService.getTermsDetsLangList(params);
		
		Map<String,Object> mapData = (new ResponseList(termsDetsLangList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param TermsDetsLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/termsDetsLang/getTermsDetsLangList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<TermsDetsLangExDto> getTermsDetsLangList(@Valid final TermsDetsLangExDto params) throws Exception {
		final List<TermsDetsLangExDto> listData = this.termsDetsLangService.getTermsDetsLangList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param TermsDetsLangExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/termsDetsLang/getTermsDetsLang.do")
	@ResponseBody
	public Object getTermsDetsLang(@Valid final TermsDetsLangExDto params) throws Exception {
		final TermsDetsLangExDto mapData = this.termsDetsLangService.getTermsDetsLang(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param TermsDetsLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/termsDetsLang/insertTermsDetsLang.do")
	@ResponseBody
	public Map<String,Object> insertTermsDetsLang(@Valid final TermsDetsLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.termsDetsLangService.insertTermsDetsLang(params);
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
	* @param TermsDetsLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/termsDetsLang/updateTermsDetsLang.do")
	@ResponseBody
	public Map<String,Object> updateTermsDetsLang(@Valid final TermsDetsLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.termsDetsLangService.updateTermsDetsLang(params);
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
	* @param TermsDetsLangExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/termsDetsLang/deleteTermsDetsLang.do")
	@ResponseBody
	public Map<String,Object> deleteTermsDetsLang(@Valid final TermsDetsLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.termsDetsLangService.deleteTermsDetsLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/termsDetsLang/getTermsDetsLangExcelDown.do")
	@ResponseBody
	public Object getTermsDetsLangExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		TermsDetsLangExDto termsDetsLangExDto = new TermsDetsLangExDto();
		termsDetsLangExDto = (TermsDetsLangExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), termsDetsLangExDto);
		final List<TermsDetsLangExDto> listData = this.termsDetsLangService.getTermsDetsLangList(termsDetsLangExDto);
		
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
