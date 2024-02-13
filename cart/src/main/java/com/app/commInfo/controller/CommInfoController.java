package com.app.commInfo.controller;

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

import com.app.commInfo.model.CommInfoExDto;
import com.app.commInfo.service.CommInfoService;
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
public class CommInfoController {

	@Autowired
	private CommInfoService commInfoService;

	@RequestMapping("app/commInfo/commInfoList.do")
	public String commInfoList() throws Exception {
		return "app/commInfo/commInfoList";
	}

	@RequestMapping("app/popup/commInfo/commInfoPop.do")
	public String commInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/commInfo/commInfoPop";
	}

	@RequestMapping("app/popup/commInfo/commInfoInfoPop.do")
	public String commInfoInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/commInfo/commInfoInfoPop";
	}

	@RequestMapping("app/popup/commInfo/commInfoSearchPop.do")
	public String commInfoSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/commInfo/commInfoSearchPop";
	}

	/**
	*  List Page
	*
	* @param CommInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/commInfo/getCommInfoPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCommInfoPageList(HttpServletRequest request, @Valid final CommInfoExDto params) throws Exception {
		final int totalRows = this.commInfoService.getCommInfoListCount(params);
		final List<CommInfoExDto> commInfoList = this.commInfoService.getCommInfoList(params);
		
		Map<String,Object> mapData = (new ResponseList(commInfoList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param CommInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/commInfo/getCommInfoList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CommInfoExDto> getCommInfoList(@Valid final CommInfoExDto params) throws Exception {
		final List<CommInfoExDto> listData = this.commInfoService.getCommInfoList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param CommInfoExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/commInfo/getCommInfo.do")
	@ResponseBody
	public Object getCommInfo(@Valid final CommInfoExDto params) throws Exception {
		final CommInfoExDto mapData = this.commInfoService.getCommInfo(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param CommInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/commInfo/insertCommInfo.do")
	@ResponseBody
	public Map<String,Object> insertCommInfo(@Valid final CommInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.commInfoService.insertCommInfo(params);
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
	* @param CommInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/commInfo/updateCommInfo.do")
	@ResponseBody
	public Map<String,Object> updateCommInfo(@Valid final CommInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.commInfoService.updateCommInfo(params);
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
	* @param CommInfoExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/commInfo/deleteCommInfo.do")
	@ResponseBody
	public Map<String,Object> deleteCommInfo(@Valid final CommInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.commInfoService.deleteCommInfo(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/commInfo/getCommInfoExcelDown.do")
	@ResponseBody
	public Object getCommInfoExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CommInfoExDto commInfoExDto = new CommInfoExDto();
		commInfoExDto = (CommInfoExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), commInfoExDto);
		final List<CommInfoExDto> listData = this.commInfoService.getCommInfoList(commInfoExDto);
		
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
