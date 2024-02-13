package com.app.pointInfo.controller;

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

import com.app.pointInfo.model.PointInfoExDto;
import com.app.pointInfo.service.PointInfoService;
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
public class PointInfoController {

	@Autowired
	private PointInfoService pointInfoService;

	@RequestMapping("app/pointInfo/pointInfoList.do")
	public String pointInfoList() throws Exception {
		return "app/pointInfo/pointInfoList";
	}

	@RequestMapping("app/popup/pointInfo/pointInfoPop.do")
	public String pointInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/pointInfo/pointInfoPop";
	}

	@RequestMapping("app/popup/pointInfo/pointInfoInfoPop.do")
	public String pointInfoInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/pointInfo/pointInfoInfoPop";
	}

	@RequestMapping("app/popup/pointInfo/pointInfoSearchPop.do")
	public String pointInfoSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/pointInfo/pointInfoSearchPop";
	}

	/**
	*  List Page
	*
	* @param PointInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/pointInfo/getPointInfoPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getPointInfoPageList(HttpServletRequest request, @Valid final PointInfoExDto params) throws Exception {
		final int totalRows = this.pointInfoService.getPointInfoListCount(params);
		final List<PointInfoExDto> pointInfoList = this.pointInfoService.getPointInfoList(params);
		
		Map<String,Object> mapData = (new ResponseList(pointInfoList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param PointInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/pointInfo/getPointInfoList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<PointInfoExDto> getPointInfoList(@Valid final PointInfoExDto params) throws Exception {
		final List<PointInfoExDto> listData = this.pointInfoService.getPointInfoList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param PointInfoExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/pointInfo/getPointInfo.do")
	@ResponseBody
	public Object getPointInfo(@Valid final PointInfoExDto params) throws Exception {
		final PointInfoExDto mapData = this.pointInfoService.getPointInfo(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param PointInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/pointInfo/insertPointInfo.do")
	@ResponseBody
	public Map<String,Object> insertPointInfo(@Valid final PointInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.pointInfoService.insertPointInfo(params);
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
	* @param PointInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/pointInfo/updatePointInfo.do")
	@ResponseBody
	public Map<String,Object> updatePointInfo(@Valid final PointInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.pointInfoService.updatePointInfo(params);
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
	* @param PointInfoExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/pointInfo/deletePointInfo.do")
	@ResponseBody
	public Map<String,Object> deletePointInfo(@Valid final PointInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.pointInfoService.deletePointInfo(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/pointInfo/getPointInfoExcelDown.do")
	@ResponseBody
	public Object getPointInfoExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		PointInfoExDto pointInfoExDto = new PointInfoExDto();
		pointInfoExDto = (PointInfoExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), pointInfoExDto);
		final List<PointInfoExDto> listData = this.pointInfoService.getPointInfoList(pointInfoExDto);
		
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
