package com.app.grpCodeLang.controller;

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

import com.app.grpCodeLang.model.GrpCodeLangExDto;
import com.app.grpCodeLang.service.GrpCodeLangService;
import com.base.ResponseList;
import com.base.ResponseMap;
import com.common.utils.ClassUtil;
import com.common.utils.Excel;
import com.common.utils.MapUtil;

import lombok.RequiredArgsConstructor;

/**
* 그룹코드_언어 관리
*
* @author
*
*/

@Controller
@Lazy
@RequiredArgsConstructor
public class GrpCodeLangController {

	@Autowired
	private GrpCodeLangService grpCodeLangService;

	@RequestMapping("app/grpCodeLang/grpCodeLangList.do")
	public String grpCodeLangList() throws Exception {
		return "app/grpCodeLang/grpCodeLangList";
	}

	@RequestMapping("app/popup/grpCodeLang/grpCodeLangPop.do")
	public String grpCodeLangPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/grpCodeLang/grpCodeLangPop";
	}

	@RequestMapping("app/popup/grpCodeLang/grpCodeLangInfoPop.do")
	public String grpCodeLangInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/grpCodeLang/grpCodeLangInfoPop";
	}

	@RequestMapping("app/popup/grpCodeLang/grpCodeLangSearchPop.do")
	public String grpCodeLangSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/grpCodeLang/grpCodeLangSearchPop";
	}

	/**
	* 그룹코드_언어 List Page
	*
	* @param GrpCodeLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/grpCodeLang/getGrpCodeLangPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getGrpCodeLangPageList(HttpServletRequest request, @Valid final GrpCodeLangExDto params) throws Exception {
		final int totalRows = this.grpCodeLangService.getGrpCodeLangListCount(params);
		final List<GrpCodeLangExDto> grpCodeLangList = this.grpCodeLangService.getGrpCodeLangList(params);
		
		Map<String,Object> mapData = (new ResponseList(grpCodeLangList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	* 그룹코드_언어 List Inquiry
	*
	* @param GrpCodeLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/grpCodeLang/getGrpCodeLangList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<GrpCodeLangExDto> getGrpCodeLangList(@Valid final GrpCodeLangExDto params) throws Exception {
		final List<GrpCodeLangExDto> listData = this.grpCodeLangService.getGrpCodeLangList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요 그룹코드_언어 detail infomation Inquiry
	* </pre>
	*
	* @param GrpCodeLangExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/grpCodeLang/getGrpCodeLang.do")
	@ResponseBody
	public Object getGrpCodeLang(@Valid final GrpCodeLangExDto params) throws Exception {
		final GrpCodeLangExDto mapData = this.grpCodeLangService.getGrpCodeLang(params);
		return mapData;
	}

	/**
	* 그룹코드_언어 insert
	*
	* @param GrpCodeLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/grpCodeLang/insertGrpCodeLang.do")
	@ResponseBody
	public Map<String,Object> insertGrpCodeLang(@Valid final GrpCodeLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.grpCodeLangService.insertGrpCodeLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 그룹코드_언어 Update
	*
	* @param GrpCodeLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/grpCodeLang/updateGrpCodeLang.do")
	@ResponseBody
	public Map<String,Object> updateGrpCodeLang(@Valid final GrpCodeLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.grpCodeLangService.updateGrpCodeLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 그룹코드_언어 delete
	*
	* @param GrpCodeLangExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/grpCodeLang/deleteGrpCodeLang.do")
	@ResponseBody
	public Map<String,Object> deleteGrpCodeLang(@Valid final GrpCodeLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.grpCodeLangService.deleteGrpCodeLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/grpCodeLang/getGrpCodeLangExcelDown.do")
	@ResponseBody
	public Object getGrpCodeLangExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		GrpCodeLangExDto grpCodeLangExDto = new GrpCodeLangExDto();
		grpCodeLangExDto = (GrpCodeLangExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), grpCodeLangExDto);
		final List<GrpCodeLangExDto> listData = this.grpCodeLangService.getGrpCodeLangList(grpCodeLangExDto);
		
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
