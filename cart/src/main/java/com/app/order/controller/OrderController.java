package com.app.order.controller;

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

import com.app.order.model.OrderExDto;
import com.app.order.service.OrderService;
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
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping("app/order/orderList.do")
	public String orderList() throws Exception {
		return "app/order/orderList";
	}

	@RequestMapping("app/popup/order/orderPop.do")
	public String orderPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/order/orderPop";
	}

	@RequestMapping("app/popup/order/orderInfoPop.do")
	public String orderInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/order/orderInfoPop";
	}

	@RequestMapping("app/popup/order/orderSearchPop.do")
	public String orderSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/order/orderSearchPop";
	}

	/**
	*  List Page
	*
	* @param OrderExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/order/getOrderPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getOrderPageList(HttpServletRequest request, @Valid final OrderExDto params) throws Exception {
		final int totalRows = this.orderService.getOrderListCount(params);
		final List<OrderExDto> orderList = this.orderService.getOrderList(params);
		
		Map<String,Object> mapData = (new ResponseList(orderList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param OrderExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/order/getOrderList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<OrderExDto> getOrderList(@Valid final OrderExDto params) throws Exception {
		final List<OrderExDto> listData = this.orderService.getOrderList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param OrderExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/order/getOrder.do")
	@ResponseBody
	public Object getOrder(@Valid final OrderExDto params) throws Exception {
		final OrderExDto mapData = this.orderService.getOrder(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param OrderExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/order/insertOrder.do")
	@ResponseBody
	public Map<String,Object> insertOrder(@Valid final OrderExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderService.insertOrder(params);
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
	* @param OrderExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/order/updateOrder.do")
	@ResponseBody
	public Map<String,Object> updateOrder(@Valid final OrderExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderService.updateOrder(params);
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
	* @param OrderExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/order/deleteOrder.do")
	@ResponseBody
	public Map<String,Object> deleteOrder(@Valid final OrderExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderService.deleteOrder(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/order/getOrderExcelDown.do")
	@ResponseBody
	public Object getOrderExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		OrderExDto orderExDto = new OrderExDto();
		orderExDto = (OrderExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), orderExDto);
		final List<OrderExDto> listData = this.orderService.getOrderList(orderExDto);
		
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
