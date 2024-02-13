package com.app.compAuthnKey.controller;

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

import com.app.compAuthnKey.model.CompAuthnKeyExDto;
import com.app.compAuthnKey.service.CompAuthnKeyService;
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
public class CompAuthnKeyController {

	@Autowired
	private CompAuthnKeyService compAuthnKeyService;

	@RequestMapping("app/compAuthnKey/compAuthnKeyList.do")
	public String compAuthnKeyList() throws Exception {
		return "app/compAuthnKey/compAuthnKeyList";
	}

	@RequestMapping("app/popup/compAuthnKey/compAuthnKeyPop.do")
	public String compAuthnKeyPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/compAuthnKey/compAuthnKeyPop";
	}

	@RequestMapping("app/popup/compAuthnKey/compAuthnKeyInfoPop.do")
	public String compAuthnKeyInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/compAuthnKey/compAuthnKeyInfoPop";
	}

	@RequestMapping("app/popup/compAuthnKey/compAuthnKeySearchPop.do")
	public String compAuthnKeySearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/compAuthnKey/compAuthnKeySearchPop";
	}

	/**
	*  List Page
	*
	* @param CompAuthnKeyExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/compAuthnKey/getCompAuthnKeyPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCompAuthnKeyPageList(HttpServletRequest request, @Valid final CompAuthnKeyExDto params) throws Exception {
		final int totalRows = this.compAuthnKeyService.getCompAuthnKeyListCount(params);
		final List<CompAuthnKeyExDto> compAuthnKeyList = this.compAuthnKeyService.getCompAuthnKeyList(params);
		
		Map<String,Object> mapData = (new ResponseList(compAuthnKeyList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param CompAuthnKeyExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/compAuthnKey/getCompAuthnKeyList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CompAuthnKeyExDto> getCompAuthnKeyList(@Valid final CompAuthnKeyExDto params) throws Exception {
		final List<CompAuthnKeyExDto> listData = this.compAuthnKeyService.getCompAuthnKeyList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param CompAuthnKeyExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/compAuthnKey/getCompAuthnKey.do")
	@ResponseBody
	public Object getCompAuthnKey(@Valid final CompAuthnKeyExDto params) throws Exception {
		final CompAuthnKeyExDto mapData = this.compAuthnKeyService.getCompAuthnKey(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param CompAuthnKeyExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/compAuthnKey/insertCompAuthnKey.do")
	@ResponseBody
	public Map<String,Object> insertCompAuthnKey(@Valid final CompAuthnKeyExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.compAuthnKeyService.insertCompAuthnKey(params);
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
	* @param CompAuthnKeyExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/compAuthnKey/updateCompAuthnKey.do")
	@ResponseBody
	public Map<String,Object> updateCompAuthnKey(@Valid final CompAuthnKeyExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.compAuthnKeyService.updateCompAuthnKey(params);
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
	* @param CompAuthnKeyExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/compAuthnKey/deleteCompAuthnKey.do")
	@ResponseBody
	public Map<String,Object> deleteCompAuthnKey(@Valid final CompAuthnKeyExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.compAuthnKeyService.deleteCompAuthnKey(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/compAuthnKey/getCompAuthnKeyExcelDown.do")
	@ResponseBody
	public Object getCompAuthnKeyExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CompAuthnKeyExDto compAuthnKeyExDto = new CompAuthnKeyExDto();
		compAuthnKeyExDto = (CompAuthnKeyExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), compAuthnKeyExDto);
		final List<CompAuthnKeyExDto> listData = this.compAuthnKeyService.getCompAuthnKeyList(compAuthnKeyExDto);
		
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
