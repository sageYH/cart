package com.app.pointInfoLang.controller;

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

import com.app.pointInfoLang.model.PointInfoLangExDto;
import com.app.pointInfoLang.service.PointInfoLangService;
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
public class PointInfoLangController {

	@Autowired
	private PointInfoLangService pointInfoLangService;

	@RequestMapping("app/pointInfoLang/pointInfoLangList.do")
	public String pointInfoLangList() throws Exception {
		return "app/pointInfoLang/pointInfoLangList";
	}

	@RequestMapping("app/popup/pointInfoLang/pointInfoLangPop.do")
	public String pointInfoLangPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/pointInfoLang/pointInfoLangPop";
	}

	@RequestMapping("app/popup/pointInfoLang/pointInfoLangInfoPop.do")
	public String pointInfoLangInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/pointInfoLang/pointInfoLangInfoPop";
	}

	@RequestMapping("app/popup/pointInfoLang/pointInfoLangSearchPop.do")
	public String pointInfoLangSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/pointInfoLang/pointInfoLangSearchPop";
	}

	/**
	*  List Page
	*
	* @param PointInfoLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/pointInfoLang/getPointInfoLangPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getPointInfoLangPageList(HttpServletRequest request, @Valid final PointInfoLangExDto params) throws Exception {
		final int totalRows = this.pointInfoLangService.getPointInfoLangListCount(params);
		final List<PointInfoLangExDto> pointInfoLangList = this.pointInfoLangService.getPointInfoLangList(params);
		
		Map<String,Object> mapData = (new ResponseList(pointInfoLangList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param PointInfoLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/pointInfoLang/getPointInfoLangList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<PointInfoLangExDto> getPointInfoLangList(@Valid final PointInfoLangExDto params) throws Exception {
		final List<PointInfoLangExDto> listData = this.pointInfoLangService.getPointInfoLangList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param PointInfoLangExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/pointInfoLang/getPointInfoLang.do")
	@ResponseBody
	public Object getPointInfoLang(@Valid final PointInfoLangExDto params) throws Exception {
		final PointInfoLangExDto mapData = this.pointInfoLangService.getPointInfoLang(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param PointInfoLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/pointInfoLang/insertPointInfoLang.do")
	@ResponseBody
	public Map<String,Object> insertPointInfoLang(@Valid final PointInfoLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.pointInfoLangService.insertPointInfoLang(params);
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
	* @param PointInfoLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/pointInfoLang/updatePointInfoLang.do")
	@ResponseBody
	public Map<String,Object> updatePointInfoLang(@Valid final PointInfoLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.pointInfoLangService.updatePointInfoLang(params);
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
	* @param PointInfoLangExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/pointInfoLang/deletePointInfoLang.do")
	@ResponseBody
	public Map<String,Object> deletePointInfoLang(@Valid final PointInfoLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.pointInfoLangService.deletePointInfoLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/pointInfoLang/getPointInfoLangExcelDown.do")
	@ResponseBody
	public Object getPointInfoLangExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		PointInfoLangExDto pointInfoLangExDto = new PointInfoLangExDto();
		pointInfoLangExDto = (PointInfoLangExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), pointInfoLangExDto);
		final List<PointInfoLangExDto> listData = this.pointInfoLangService.getPointInfoLangList(pointInfoLangExDto);
		
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
