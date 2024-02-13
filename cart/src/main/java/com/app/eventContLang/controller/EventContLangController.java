package com.app.eventContLang.controller;

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

import com.app.eventContLang.model.EventContLangExDto;
import com.app.eventContLang.service.EventContLangService;
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
public class EventContLangController {

	@Autowired
	private EventContLangService eventContLangService;

	@RequestMapping("app/eventContLang/eventContLangList.do")
	public String eventContLangList() throws Exception {
		return "app/eventContLang/eventContLangList";
	}

	@RequestMapping("app/popup/eventContLang/eventContLangPop.do")
	public String eventContLangPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/eventContLang/eventContLangPop";
	}

	@RequestMapping("app/popup/eventContLang/eventContLangInfoPop.do")
	public String eventContLangInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/eventContLang/eventContLangInfoPop";
	}

	@RequestMapping("app/popup/eventContLang/eventContLangSearchPop.do")
	public String eventContLangSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/eventContLang/eventContLangSearchPop";
	}

	/**
	*  List Page
	*
	* @param EventContLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/eventContLang/getEventContLangPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getEventContLangPageList(HttpServletRequest request, @Valid final EventContLangExDto params) throws Exception {
		final int totalRows = this.eventContLangService.getEventContLangListCount(params);
		final List<EventContLangExDto> eventContLangList = this.eventContLangService.getEventContLangList(params);
		
		Map<String,Object> mapData = (new ResponseList(eventContLangList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param EventContLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/eventContLang/getEventContLangList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<EventContLangExDto> getEventContLangList(@Valid final EventContLangExDto params) throws Exception {
		final List<EventContLangExDto> listData = this.eventContLangService.getEventContLangList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param EventContLangExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/eventContLang/getEventContLang.do")
	@ResponseBody
	public Object getEventContLang(@Valid final EventContLangExDto params) throws Exception {
		final EventContLangExDto mapData = this.eventContLangService.getEventContLang(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param EventContLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/eventContLang/insertEventContLang.do")
	@ResponseBody
	public Map<String,Object> insertEventContLang(@Valid final EventContLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.eventContLangService.insertEventContLang(params);
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
	* @param EventContLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/eventContLang/updateEventContLang.do")
	@ResponseBody
	public Map<String,Object> updateEventContLang(@Valid final EventContLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.eventContLangService.updateEventContLang(params);
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
	* @param EventContLangExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/eventContLang/deleteEventContLang.do")
	@ResponseBody
	public Map<String,Object> deleteEventContLang(@Valid final EventContLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.eventContLangService.deleteEventContLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/eventContLang/getEventContLangExcelDown.do")
	@ResponseBody
	public Object getEventContLangExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		EventContLangExDto eventContLangExDto = new EventContLangExDto();
		eventContLangExDto = (EventContLangExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), eventContLangExDto);
		final List<EventContLangExDto> listData = this.eventContLangService.getEventContLangList(eventContLangExDto);
		
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
