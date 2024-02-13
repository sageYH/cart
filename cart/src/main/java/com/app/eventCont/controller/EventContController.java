package com.app.eventCont.controller;

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

import com.app.eventCont.model.EventContExDto;
import com.app.eventCont.service.EventContService;
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
public class EventContController {

	@Autowired
	private EventContService eventContService;

	@RequestMapping("app/eventCont/eventContList.do")
	public String eventContList() throws Exception {
		return "app/eventCont/eventContList";
	}

	@RequestMapping("app/popup/eventCont/eventContPop.do")
	public String eventContPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/eventCont/eventContPop";
	}

	@RequestMapping("app/popup/eventCont/eventContInfoPop.do")
	public String eventContInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/eventCont/eventContInfoPop";
	}

	@RequestMapping("app/popup/eventCont/eventContSearchPop.do")
	public String eventContSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/eventCont/eventContSearchPop";
	}

	/**
	*  List Page
	*
	* @param EventContExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/eventCont/getEventContPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getEventContPageList(HttpServletRequest request, @Valid final EventContExDto params) throws Exception {
		final int totalRows = this.eventContService.getEventContListCount(params);
		final List<EventContExDto> eventContList = this.eventContService.getEventContList(params);
		
		Map<String,Object> mapData = (new ResponseList(eventContList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param EventContExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/eventCont/getEventContList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<EventContExDto> getEventContList(@Valid final EventContExDto params) throws Exception {
		final List<EventContExDto> listData = this.eventContService.getEventContList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param EventContExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/eventCont/getEventCont.do")
	@ResponseBody
	public Object getEventCont(@Valid final EventContExDto params) throws Exception {
		final EventContExDto mapData = this.eventContService.getEventCont(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param EventContExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/eventCont/insertEventCont.do")
	@ResponseBody
	public Map<String,Object> insertEventCont(@Valid final EventContExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.eventContService.insertEventCont(params);
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
	* @param EventContExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/eventCont/updateEventCont.do")
	@ResponseBody
	public Map<String,Object> updateEventCont(@Valid final EventContExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.eventContService.updateEventCont(params);
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
	* @param EventContExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/eventCont/deleteEventCont.do")
	@ResponseBody
	public Map<String,Object> deleteEventCont(@Valid final EventContExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.eventContService.deleteEventCont(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/eventCont/getEventContExcelDown.do")
	@ResponseBody
	public Object getEventContExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		EventContExDto eventContExDto = new EventContExDto();
		eventContExDto = (EventContExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), eventContExDto);
		final List<EventContExDto> listData = this.eventContService.getEventContList(eventContExDto);
		
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
