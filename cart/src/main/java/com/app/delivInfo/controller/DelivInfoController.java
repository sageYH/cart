package com.app.delivInfo.controller;

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

import com.app.delivInfo.model.DelivInfoExDto;
import com.app.delivInfo.service.DelivInfoService;
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
public class DelivInfoController {

	@Autowired
	private DelivInfoService delivInfoService;

	@RequestMapping("app/delivInfo/delivInfoList.do")
	public String delivInfoList() throws Exception {
		return "app/delivInfo/delivInfoList";
	}

	@RequestMapping("app/popup/delivInfo/delivInfoPop.do")
	public String delivInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/delivInfo/delivInfoPop";
	}

	@RequestMapping("app/popup/delivInfo/delivInfoInfoPop.do")
	public String delivInfoInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/delivInfo/delivInfoInfoPop";
	}

	@RequestMapping("app/popup/delivInfo/delivInfoSearchPop.do")
	public String delivInfoSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/delivInfo/delivInfoSearchPop";
	}

	/**
	*  List Page
	*
	* @param DelivInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/delivInfo/getDelivInfoPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getDelivInfoPageList(HttpServletRequest request, @Valid final DelivInfoExDto params) throws Exception {
		final int totalRows = this.delivInfoService.getDelivInfoListCount(params);
		final List<DelivInfoExDto> delivInfoList = this.delivInfoService.getDelivInfoList(params);
		
		Map<String,Object> mapData = (new ResponseList(delivInfoList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param DelivInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/delivInfo/getDelivInfoList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<DelivInfoExDto> getDelivInfoList(@Valid final DelivInfoExDto params) throws Exception {
		final List<DelivInfoExDto> listData = this.delivInfoService.getDelivInfoList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param DelivInfoExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/delivInfo/getDelivInfo.do")
	@ResponseBody
	public Object getDelivInfo(@Valid final DelivInfoExDto params) throws Exception {
		final DelivInfoExDto mapData = this.delivInfoService.getDelivInfo(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param DelivInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/delivInfo/insertDelivInfo.do")
	@ResponseBody
	public Map<String,Object> insertDelivInfo(@Valid final DelivInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.delivInfoService.insertDelivInfo(params);
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
	* @param DelivInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/delivInfo/updateDelivInfo.do")
	@ResponseBody
	public Map<String,Object> updateDelivInfo(@Valid final DelivInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.delivInfoService.updateDelivInfo(params);
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
	* @param DelivInfoExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/delivInfo/deleteDelivInfo.do")
	@ResponseBody
	public Map<String,Object> deleteDelivInfo(@Valid final DelivInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.delivInfoService.deleteDelivInfo(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/delivInfo/getDelivInfoExcelDown.do")
	@ResponseBody
	public Object getDelivInfoExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		DelivInfoExDto delivInfoExDto = new DelivInfoExDto();
		delivInfoExDto = (DelivInfoExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), delivInfoExDto);
		final List<DelivInfoExDto> listData = this.delivInfoService.getDelivInfoList(delivInfoExDto);
		
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
