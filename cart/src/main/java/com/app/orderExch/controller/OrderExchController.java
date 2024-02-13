package com.app.orderExch.controller;

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

import com.app.orderExch.model.OrderExchExDto;
import com.app.orderExch.service.OrderExchService;
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
public class OrderExchController {

	@Autowired
	private OrderExchService orderExchService;

	@RequestMapping("app/orderExch/orderExchList.do")
	public String orderExchList() throws Exception {
		return "app/orderExch/orderExchList";
	}

	@RequestMapping("app/popup/orderExch/orderExchPop.do")
	public String orderExchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderExch/orderExchPop";
	}

	@RequestMapping("app/popup/orderExch/orderExchInfoPop.do")
	public String orderExchInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderExch/orderExchInfoPop";
	}

	@RequestMapping("app/popup/orderExch/orderExchSearchPop.do")
	public String orderExchSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderExch/orderExchSearchPop";
	}

	/**
	*  List Page
	*
	* @param OrderExchExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderExch/getOrderExchPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getOrderExchPageList(HttpServletRequest request, @Valid final OrderExchExDto params) throws Exception {
		final int totalRows = this.orderExchService.getOrderExchListCount(params);
		final List<OrderExchExDto> orderExchList = this.orderExchService.getOrderExchList(params);
		
		Map<String,Object> mapData = (new ResponseList(orderExchList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param OrderExchExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderExch/getOrderExchList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<OrderExchExDto> getOrderExchList(@Valid final OrderExchExDto params) throws Exception {
		final List<OrderExchExDto> listData = this.orderExchService.getOrderExchList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param OrderExchExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/orderExch/getOrderExch.do")
	@ResponseBody
	public Object getOrderExch(@Valid final OrderExchExDto params) throws Exception {
		final OrderExchExDto mapData = this.orderExchService.getOrderExch(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param OrderExchExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderExch/insertOrderExch.do")
	@ResponseBody
	public Map<String,Object> insertOrderExch(@Valid final OrderExchExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderExchService.insertOrderExch(params);
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
	* @param OrderExchExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderExch/updateOrderExch.do")
	@ResponseBody
	public Map<String,Object> updateOrderExch(@Valid final OrderExchExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderExchService.updateOrderExch(params);
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
	* @param OrderExchExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderExch/deleteOrderExch.do")
	@ResponseBody
	public Map<String,Object> deleteOrderExch(@Valid final OrderExchExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderExchService.deleteOrderExch(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/orderExch/getOrderExchExcelDown.do")
	@ResponseBody
	public Object getOrderExchExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		OrderExchExDto orderExchExDto = new OrderExchExDto();
		orderExchExDto = (OrderExchExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), orderExchExDto);
		final List<OrderExchExDto> listData = this.orderExchService.getOrderExchList(orderExchExDto);
		
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
