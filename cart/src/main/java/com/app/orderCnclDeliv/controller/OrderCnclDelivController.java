package com.app.orderCnclDeliv.controller;

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

import com.app.orderCnclDeliv.model.OrderCnclDelivExDto;
import com.app.orderCnclDeliv.service.OrderCnclDelivService;
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
public class OrderCnclDelivController {

	@Autowired
	private OrderCnclDelivService orderCnclDelivService;

	@RequestMapping("app/orderCnclDeliv/orderCnclDelivList.do")
	public String orderCnclDelivList() throws Exception {
		return "app/orderCnclDeliv/orderCnclDelivList";
	}

	@RequestMapping("app/popup/orderCnclDeliv/orderCnclDelivPop.do")
	public String orderCnclDelivPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderCnclDeliv/orderCnclDelivPop";
	}

	@RequestMapping("app/popup/orderCnclDeliv/orderCnclDelivInfoPop.do")
	public String orderCnclDelivInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderCnclDeliv/orderCnclDelivInfoPop";
	}

	@RequestMapping("app/popup/orderCnclDeliv/orderCnclDelivSearchPop.do")
	public String orderCnclDelivSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderCnclDeliv/orderCnclDelivSearchPop";
	}

	/**
	*  List Page
	*
	* @param OrderCnclDelivExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderCnclDeliv/getOrderCnclDelivPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getOrderCnclDelivPageList(HttpServletRequest request, @Valid final OrderCnclDelivExDto params) throws Exception {
		final int totalRows = this.orderCnclDelivService.getOrderCnclDelivListCount(params);
		final List<OrderCnclDelivExDto> orderCnclDelivList = this.orderCnclDelivService.getOrderCnclDelivList(params);
		
		Map<String,Object> mapData = (new ResponseList(orderCnclDelivList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param OrderCnclDelivExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderCnclDeliv/getOrderCnclDelivList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<OrderCnclDelivExDto> getOrderCnclDelivList(@Valid final OrderCnclDelivExDto params) throws Exception {
		final List<OrderCnclDelivExDto> listData = this.orderCnclDelivService.getOrderCnclDelivList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param OrderCnclDelivExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/orderCnclDeliv/getOrderCnclDeliv.do")
	@ResponseBody
	public Object getOrderCnclDeliv(@Valid final OrderCnclDelivExDto params) throws Exception {
		final OrderCnclDelivExDto mapData = this.orderCnclDelivService.getOrderCnclDeliv(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param OrderCnclDelivExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderCnclDeliv/insertOrderCnclDeliv.do")
	@ResponseBody
	public Map<String,Object> insertOrderCnclDeliv(@Valid final OrderCnclDelivExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderCnclDelivService.insertOrderCnclDeliv(params);
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
	* @param OrderCnclDelivExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderCnclDeliv/updateOrderCnclDeliv.do")
	@ResponseBody
	public Map<String,Object> updateOrderCnclDeliv(@Valid final OrderCnclDelivExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderCnclDelivService.updateOrderCnclDeliv(params);
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
	* @param OrderCnclDelivExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderCnclDeliv/deleteOrderCnclDeliv.do")
	@ResponseBody
	public Map<String,Object> deleteOrderCnclDeliv(@Valid final OrderCnclDelivExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderCnclDelivService.deleteOrderCnclDeliv(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/orderCnclDeliv/getOrderCnclDelivExcelDown.do")
	@ResponseBody
	public Object getOrderCnclDelivExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		OrderCnclDelivExDto orderCnclDelivExDto = new OrderCnclDelivExDto();
		orderCnclDelivExDto = (OrderCnclDelivExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), orderCnclDelivExDto);
		final List<OrderCnclDelivExDto> listData = this.orderCnclDelivService.getOrderCnclDelivList(orderCnclDelivExDto);
		
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
