package com.app.cmnCode.controller;

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

import com.app.cmnCode.model.CmnCodeExDto;
import com.app.cmnCode.service.CmnCodeService;
import com.base.ResponseList;
import com.base.ResponseMap;
import com.common.utils.ClassUtil;
import com.common.utils.Excel;
import com.common.utils.MapUtil;

import lombok.RequiredArgsConstructor;

/**
* 공통코드 관리
*
* @author
*
*/

@Controller
@Lazy
@RequiredArgsConstructor
public class CmnCodeController {

	@Autowired
	private CmnCodeService cmnCodeService;

	@RequestMapping("app/cmnCode/cmnCodeList.do")
	public String cmnCodeList() throws Exception {
		return "app/cmnCode/cmnCodeList";
	}

	@RequestMapping("app/popup/cmnCode/cmnCodePop.do")
	public String cmnCodePop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/cmnCode/cmnCodePop";
	}

	@RequestMapping("app/popup/cmnCode/cmnCodeInfoPop.do")
	public String cmnCodeInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/cmnCode/cmnCodeInfoPop";
	}

	@RequestMapping("app/popup/cmnCode/cmnCodeSearchPop.do")
	public String cmnCodeSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/cmnCode/cmnCodeSearchPop";
	}

	/**
	* 공통코드 List Page
	*
	* @param CmnCodeExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/cmnCode/getCmnCodePageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCmnCodePageList(HttpServletRequest request, @Valid final CmnCodeExDto params) throws Exception {
		final int totalRows = this.cmnCodeService.getCmnCodeListCount(params);
		final List<CmnCodeExDto> cmnCodeList = this.cmnCodeService.getCmnCodeList(params);
		
		Map<String,Object> mapData = (new ResponseList(cmnCodeList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	* 공통코드 List Inquiry
	*
	* @param CmnCodeExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/cmnCode/getCmnCodeList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CmnCodeExDto> getCmnCodeList(@Valid final CmnCodeExDto params) throws Exception {
		final List<CmnCodeExDto> listData = this.cmnCodeService.getCmnCodeList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요 공통코드 detail infomation Inquiry
	* </pre>
	*
	* @param CmnCodeExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/cmnCode/getCmnCode.do")
	@ResponseBody
	public Object getCmnCode(@Valid final CmnCodeExDto params) throws Exception {
		final CmnCodeExDto mapData = this.cmnCodeService.getCmnCode(params);
		return mapData;
	}

	/**
	* 공통코드 insert
	*
	* @param CmnCodeExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/cmnCode/insertCmnCode.do")
	@ResponseBody
	public Map<String,Object> insertCmnCode(@Valid final CmnCodeExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.cmnCodeService.insertCmnCode(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 공통코드 Update
	*
	* @param CmnCodeExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/cmnCode/updateCmnCode.do")
	@ResponseBody
	public Map<String,Object> updateCmnCode(@Valid final CmnCodeExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.cmnCodeService.updateCmnCode(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 공통코드 delete
	*
	* @param CmnCodeExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/cmnCode/deleteCmnCode.do")
	@ResponseBody
	public Map<String,Object> deleteCmnCode(@Valid final CmnCodeExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.cmnCodeService.deleteCmnCode(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/cmnCode/getCmnCodeExcelDown.do")
	@ResponseBody
	public Object getCmnCodeExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CmnCodeExDto cmnCodeExDto = new CmnCodeExDto();
		cmnCodeExDto = (CmnCodeExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), cmnCodeExDto);
		final List<CmnCodeExDto> listData = this.cmnCodeService.getCmnCodeList(cmnCodeExDto);
		
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
