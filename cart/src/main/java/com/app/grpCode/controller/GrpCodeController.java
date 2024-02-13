package com.app.grpCode.controller;

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

import com.app.grpCode.model.GrpCodeExDto;
import com.app.grpCode.service.GrpCodeService;
import com.base.ResponseList;
import com.base.ResponseMap;
import com.common.utils.ClassUtil;
import com.common.utils.Excel;
import com.common.utils.MapUtil;

import lombok.RequiredArgsConstructor;

/**
* 그룹코드 관리
*
* @author
*
*/

@Controller
@Lazy
@RequiredArgsConstructor
public class GrpCodeController {

	@Autowired
	private GrpCodeService grpCodeService;

	@RequestMapping("app/grpCode/grpCodeList.do")
	public String grpCodeList() throws Exception {
		return "app/grpCode/grpCodeList";
	}

	@RequestMapping("app/popup/grpCode/grpCodePop.do")
	public String grpCodePop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/grpCode/grpCodePop";
	}

	@RequestMapping("app/popup/grpCode/grpCodeInfoPop.do")
	public String grpCodeInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/grpCode/grpCodeInfoPop";
	}

	@RequestMapping("app/popup/grpCode/grpCodeSearchPop.do")
	public String grpCodeSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/grpCode/grpCodeSearchPop";
	}

	/**
	* 그룹코드 List Page
	*
	* @param GrpCodeExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/grpCode/getGrpCodePageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getGrpCodePageList(HttpServletRequest request, @Valid final GrpCodeExDto params) throws Exception {
		final int totalRows = this.grpCodeService.getGrpCodeListCount(params);
		final List<GrpCodeExDto> grpCodeList = this.grpCodeService.getGrpCodeList(params);
		
		Map<String,Object> mapData = (new ResponseList(grpCodeList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	* 그룹코드 List Inquiry
	*
	* @param GrpCodeExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/grpCode/getGrpCodeList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<GrpCodeExDto> getGrpCodeList(@Valid final GrpCodeExDto params) throws Exception {
		final List<GrpCodeExDto> listData = this.grpCodeService.getGrpCodeList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요 그룹코드 detail infomation Inquiry
	* </pre>
	*
	* @param GrpCodeExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/grpCode/getGrpCode.do")
	@ResponseBody
	public Object getGrpCode(@Valid final GrpCodeExDto params) throws Exception {
		final GrpCodeExDto mapData = this.grpCodeService.getGrpCode(params);
		return mapData;
	}

	/**
	* 그룹코드 insert
	*
	* @param GrpCodeExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/grpCode/insertGrpCode.do")
	@ResponseBody
	public Map<String,Object> insertGrpCode(@Valid final GrpCodeExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.grpCodeService.insertGrpCode(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 그룹코드 Update
	*
	* @param GrpCodeExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/grpCode/updateGrpCode.do")
	@ResponseBody
	public Map<String,Object> updateGrpCode(@Valid final GrpCodeExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.grpCodeService.updateGrpCode(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 그룹코드 delete
	*
	* @param GrpCodeExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/grpCode/deleteGrpCode.do")
	@ResponseBody
	public Map<String,Object> deleteGrpCode(@Valid final GrpCodeExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.grpCodeService.deleteGrpCode(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/grpCode/getGrpCodeExcelDown.do")
	@ResponseBody
	public Object getGrpCodeExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		GrpCodeExDto grpCodeExDto = new GrpCodeExDto();
		grpCodeExDto = (GrpCodeExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), grpCodeExDto);
		final List<GrpCodeExDto> listData = this.grpCodeService.getGrpCodeList(grpCodeExDto);
		
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
