package com.app.commSendTgt.controller;

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

import com.app.commSendTgt.model.CommSendTgtExDto;
import com.app.commSendTgt.service.CommSendTgtService;
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
public class CommSendTgtController {

	@Autowired
	private CommSendTgtService commSendTgtService;

	@RequestMapping("app/commSendTgt/commSendTgtList.do")
	public String commSendTgtList() throws Exception {
		return "app/commSendTgt/commSendTgtList";
	}

	@RequestMapping("app/popup/commSendTgt/commSendTgtPop.do")
	public String commSendTgtPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/commSendTgt/commSendTgtPop";
	}

	@RequestMapping("app/popup/commSendTgt/commSendTgtInfoPop.do")
	public String commSendTgtInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/commSendTgt/commSendTgtInfoPop";
	}

	@RequestMapping("app/popup/commSendTgt/commSendTgtSearchPop.do")
	public String commSendTgtSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/commSendTgt/commSendTgtSearchPop";
	}

	/**
	*  List Page
	*
	* @param CommSendTgtExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/commSendTgt/getCommSendTgtPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCommSendTgtPageList(HttpServletRequest request, @Valid final CommSendTgtExDto params) throws Exception {
		final int totalRows = this.commSendTgtService.getCommSendTgtListCount(params);
		final List<CommSendTgtExDto> commSendTgtList = this.commSendTgtService.getCommSendTgtList(params);
		
		Map<String,Object> mapData = (new ResponseList(commSendTgtList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param CommSendTgtExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/commSendTgt/getCommSendTgtList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CommSendTgtExDto> getCommSendTgtList(@Valid final CommSendTgtExDto params) throws Exception {
		final List<CommSendTgtExDto> listData = this.commSendTgtService.getCommSendTgtList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param CommSendTgtExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/commSendTgt/getCommSendTgt.do")
	@ResponseBody
	public Object getCommSendTgt(@Valid final CommSendTgtExDto params) throws Exception {
		final CommSendTgtExDto mapData = this.commSendTgtService.getCommSendTgt(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param CommSendTgtExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/commSendTgt/insertCommSendTgt.do")
	@ResponseBody
	public Map<String,Object> insertCommSendTgt(@Valid final CommSendTgtExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.commSendTgtService.insertCommSendTgt(params);
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
	* @param CommSendTgtExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/commSendTgt/updateCommSendTgt.do")
	@ResponseBody
	public Map<String,Object> updateCommSendTgt(@Valid final CommSendTgtExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.commSendTgtService.updateCommSendTgt(params);
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
	* @param CommSendTgtExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/commSendTgt/deleteCommSendTgt.do")
	@ResponseBody
	public Map<String,Object> deleteCommSendTgt(@Valid final CommSendTgtExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.commSendTgtService.deleteCommSendTgt(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/commSendTgt/getCommSendTgtExcelDown.do")
	@ResponseBody
	public Object getCommSendTgtExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CommSendTgtExDto commSendTgtExDto = new CommSendTgtExDto();
		commSendTgtExDto = (CommSendTgtExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), commSendTgtExDto);
		final List<CommSendTgtExDto> listData = this.commSendTgtService.getCommSendTgtList(commSendTgtExDto);
		
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
