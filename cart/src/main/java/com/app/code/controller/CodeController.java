package com.app.code.controller;

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

import com.app.code.model.CodeExDto;
import com.app.code.service.CodeService;
import com.base.ResponseList;
import com.base.ResponseMap;
import com.common.utils.ClassUtil;
import com.common.utils.Excel;
import com.common.utils.MapUtil;

import lombok.RequiredArgsConstructor;

/**
* 공통코드관리 관리
*
* @author
*
*/

@Controller
@Lazy
@RequiredArgsConstructor
public class CodeController {

	@Autowired
	private CodeService codeService;

	@RequestMapping("app/code/codeList.do")
	public String codeList() throws Exception {
		return "app/code/codeList";
	}

	@RequestMapping("app/popup/code/codePop.do")
	public String codePop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/code/codePop";
	}

	@RequestMapping("app/popup/code/codeInfoPop.do")
	public String codeInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/code/codeInfoPop";
	}

	@RequestMapping("app/popup/code/codeSearchPop.do")
	public String codeSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/code/codeSearchPop";
	}

	/**
	* 공통코드관리 List Page
	*
	* @param CodeExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/code/getCodePageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCodePageList(HttpServletRequest request, @Valid final CodeExDto params) throws Exception {
		final int totalRows = this.codeService.getCodeListCount(params);
		final List<CodeExDto> codeList = this.codeService.getCodeList(params);
		
		Map<String,Object> mapData = (new ResponseList(codeList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	* 공통코드관리 List Inquiry
	*
	* @param CodeExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/code/getCodeList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CodeExDto> getCodeList(@Valid final CodeExDto params) throws Exception {
		final List<CodeExDto> listData = this.codeService.getCodeList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요 공통코드관리 detail infomation Inquiry
	* </pre>
	*
	* @param CodeExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/code/getCode.do")
	@ResponseBody
	public Object getCode(@Valid final CodeExDto params) throws Exception {
		final CodeExDto mapData = this.codeService.getCode(params);
		return mapData;
	}

	/**
	* 공통코드관리 insert
	*
	* @param CodeExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/code/insertCode.do")
	@ResponseBody
	public Map<String,Object> insertCode(@Valid final CodeExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.codeService.insertCode(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 공통코드관리 Update
	*
	* @param CodeExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/code/updateCode.do")
	@ResponseBody
	public Map<String,Object> updateCode(@Valid final CodeExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.codeService.updateCode(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 공통코드관리 delete
	*
	* @param CodeExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/code/deleteCode.do")
	@ResponseBody
	public Map<String,Object> deleteCode(@Valid final CodeExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.codeService.deleteCode(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/code/getCodeExcelDown.do")
	@ResponseBody
	public Object getCodeExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CodeExDto codeExDto = new CodeExDto();
		codeExDto = (CodeExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), codeExDto);
		final List<CodeExDto> listData = this.codeService.getCodeList(codeExDto);
		
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
