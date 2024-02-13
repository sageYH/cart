package com.app.event.controller;

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

import com.app.event.model.EventExDto;
import com.app.event.service.EventService;
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
public class EventController {

	@Autowired
	private EventService eventService;

	@RequestMapping("app/event/eventList.do")
	public String eventList() throws Exception {
		return "app/event/eventList";
	}

	@RequestMapping("app/popup/event/eventPop.do")
	public String eventPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/event/eventPop";
	}

	@RequestMapping("app/popup/event/eventInfoPop.do")
	public String eventInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/event/eventInfoPop";
	}

	@RequestMapping("app/popup/event/eventSearchPop.do")
	public String eventSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/event/eventSearchPop";
	}

	/**
	*  List Page
	*
	* @param EventExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/event/getEventPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getEventPageList(HttpServletRequest request, @Valid final EventExDto params) throws Exception {
		final int totalRows = this.eventService.getEventListCount(params);
		final List<EventExDto> eventList = this.eventService.getEventList(params);
		
		Map<String,Object> mapData = (new ResponseList(eventList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param EventExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/event/getEventList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<EventExDto> getEventList(@Valid final EventExDto params) throws Exception {
		final List<EventExDto> listData = this.eventService.getEventList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param EventExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/event/getEvent.do")
	@ResponseBody
	public Object getEvent(@Valid final EventExDto params) throws Exception {
		final EventExDto mapData = this.eventService.getEvent(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param EventExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/event/insertEvent.do")
	@ResponseBody
	public Map<String,Object> insertEvent(@Valid final EventExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.eventService.insertEvent(params);
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
	* @param EventExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/event/updateEvent.do")
	@ResponseBody
	public Map<String,Object> updateEvent(@Valid final EventExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.eventService.updateEvent(params);
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
	* @param EventExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/event/deleteEvent.do")
	@ResponseBody
	public Map<String,Object> deleteEvent(@Valid final EventExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.eventService.deleteEvent(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/event/getEventExcelDown.do")
	@ResponseBody
	public Object getEventExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		EventExDto eventExDto = new EventExDto();
		eventExDto = (EventExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), eventExDto);
		final List<EventExDto> listData = this.eventService.getEventList(eventExDto);
		
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
