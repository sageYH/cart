package com.app.cmnCodeLang.controller;

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

import com.app.cmnCodeLang.model.CmnCodeLangExDto;
import com.app.cmnCodeLang.service.CmnCodeLangService;
import com.base.ResponseList;
import com.base.ResponseMap;
import com.common.utils.ClassUtil;
import com.common.utils.Excel;
import com.common.utils.MapUtil;

import lombok.RequiredArgsConstructor;

/**
* 공통코드_언어 관리
*
* @author
*
*/

@Controller
@Lazy
@RequiredArgsConstructor
public class CmnCodeLangController {

	@Autowired
	private CmnCodeLangService cmnCodeLangService;

	@RequestMapping("app/cmnCodeLang/cmnCodeLangList.do")
	public String cmnCodeLangList() throws Exception {
		return "app/cmnCodeLang/cmnCodeLangList";
	}

	@RequestMapping("app/popup/cmnCodeLang/cmnCodeLangPop.do")
	public String cmnCodeLangPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/cmnCodeLang/cmnCodeLangPop";
	}

	@RequestMapping("app/popup/cmnCodeLang/cmnCodeLangInfoPop.do")
	public String cmnCodeLangInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/cmnCodeLang/cmnCodeLangInfoPop";
	}

	@RequestMapping("app/popup/cmnCodeLang/cmnCodeLangSearchPop.do")
	public String cmnCodeLangSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/cmnCodeLang/cmnCodeLangSearchPop";
	}

	/**
	* 공통코드_언어 List Page
	*
	* @param CmnCodeLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/cmnCodeLang/getCmnCodeLangPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCmnCodeLangPageList(HttpServletRequest request, @Valid final CmnCodeLangExDto params) throws Exception {
		final int totalRows = this.cmnCodeLangService.getCmnCodeLangListCount(params);
		final List<CmnCodeLangExDto> cmnCodeLangList = this.cmnCodeLangService.getCmnCodeLangList(params);
		
		Map<String,Object> mapData = (new ResponseList(cmnCodeLangList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	* 공통코드_언어 List Inquiry
	*
	* @param CmnCodeLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/cmnCodeLang/getCmnCodeLangList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CmnCodeLangExDto> getCmnCodeLangList(@Valid final CmnCodeLangExDto params) throws Exception {
		final List<CmnCodeLangExDto> listData = this.cmnCodeLangService.getCmnCodeLangList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요 공통코드_언어 detail infomation Inquiry
	* </pre>
	*
	* @param CmnCodeLangExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/cmnCodeLang/getCmnCodeLang.do")
	@ResponseBody
	public Object getCmnCodeLang(@Valid final CmnCodeLangExDto params) throws Exception {
		final CmnCodeLangExDto mapData = this.cmnCodeLangService.getCmnCodeLang(params);
		return mapData;
	}

	/**
	* 공통코드_언어 insert
	*
	* @param CmnCodeLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/cmnCodeLang/insertCmnCodeLang.do")
	@ResponseBody
	public Map<String,Object> insertCmnCodeLang(@Valid final CmnCodeLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.cmnCodeLangService.insertCmnCodeLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 공통코드_언어 Update
	*
	* @param CmnCodeLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/cmnCodeLang/updateCmnCodeLang.do")
	@ResponseBody
	public Map<String,Object> updateCmnCodeLang(@Valid final CmnCodeLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.cmnCodeLangService.updateCmnCodeLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 공통코드_언어 delete
	*
	* @param CmnCodeLangExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/cmnCodeLang/deleteCmnCodeLang.do")
	@ResponseBody
	public Map<String,Object> deleteCmnCodeLang(@Valid final CmnCodeLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.cmnCodeLangService.deleteCmnCodeLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/cmnCodeLang/getCmnCodeLangExcelDown.do")
	@ResponseBody
	public Object getCmnCodeLangExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CmnCodeLangExDto cmnCodeLangExDto = new CmnCodeLangExDto();
		cmnCodeLangExDto = (CmnCodeLangExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), cmnCodeLangExDto);
		final List<CmnCodeLangExDto> listData = this.cmnCodeLangService.getCmnCodeLangList(cmnCodeLangExDto);
		
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
