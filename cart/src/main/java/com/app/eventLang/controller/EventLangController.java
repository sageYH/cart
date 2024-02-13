package com.app.eventLang.controller;

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

import com.app.eventLang.model.EventLangExDto;
import com.app.eventLang.service.EventLangService;
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
public class EventLangController {

	@Autowired
	private EventLangService eventLangService;

	@RequestMapping("app/eventLang/eventLangList.do")
	public String eventLangList() throws Exception {
		return "app/eventLang/eventLangList";
	}

	@RequestMapping("app/popup/eventLang/eventLangPop.do")
	public String eventLangPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/eventLang/eventLangPop";
	}

	@RequestMapping("app/popup/eventLang/eventLangInfoPop.do")
	public String eventLangInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/eventLang/eventLangInfoPop";
	}

	@RequestMapping("app/popup/eventLang/eventLangSearchPop.do")
	public String eventLangSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/eventLang/eventLangSearchPop";
	}

	/**
	*  List Page
	*
	* @param EventLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/eventLang/getEventLangPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getEventLangPageList(HttpServletRequest request, @Valid final EventLangExDto params) throws Exception {
		final int totalRows = this.eventLangService.getEventLangListCount(params);
		final List<EventLangExDto> eventLangList = this.eventLangService.getEventLangList(params);
		
		Map<String,Object> mapData = (new ResponseList(eventLangList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param EventLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/eventLang/getEventLangList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<EventLangExDto> getEventLangList(@Valid final EventLangExDto params) throws Exception {
		final List<EventLangExDto> listData = this.eventLangService.getEventLangList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param EventLangExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/eventLang/getEventLang.do")
	@ResponseBody
	public Object getEventLang(@Valid final EventLangExDto params) throws Exception {
		final EventLangExDto mapData = this.eventLangService.getEventLang(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param EventLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/eventLang/insertEventLang.do")
	@ResponseBody
	public Map<String,Object> insertEventLang(@Valid final EventLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.eventLangService.insertEventLang(params);
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
	* @param EventLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/eventLang/updateEventLang.do")
	@ResponseBody
	public Map<String,Object> updateEventLang(@Valid final EventLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.eventLangService.updateEventLang(params);
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
	* @param EventLangExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/eventLang/deleteEventLang.do")
	@ResponseBody
	public Map<String,Object> deleteEventLang(@Valid final EventLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.eventLangService.deleteEventLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/eventLang/getEventLangExcelDown.do")
	@ResponseBody
	public Object getEventLangExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		EventLangExDto eventLangExDto = new EventLangExDto();
		eventLangExDto = (EventLangExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), eventLangExDto);
		final List<EventLangExDto> listData = this.eventLangService.getEventLangList(eventLangExDto);
		
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
