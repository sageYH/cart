package com.app.orderCnclDelivProd.controller;

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

import com.app.orderCnclDelivProd.model.OrderCnclDelivProdExDto;
import com.app.orderCnclDelivProd.service.OrderCnclDelivProdService;
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
public class OrderCnclDelivProdController {

	@Autowired
	private OrderCnclDelivProdService orderCnclDelivProdService;

	@RequestMapping("app/orderCnclDelivProd/orderCnclDelivProdList.do")
	public String orderCnclDelivProdList() throws Exception {
		return "app/orderCnclDelivProd/orderCnclDelivProdList";
	}

	@RequestMapping("app/popup/orderCnclDelivProd/orderCnclDelivProdPop.do")
	public String orderCnclDelivProdPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderCnclDelivProd/orderCnclDelivProdPop";
	}

	@RequestMapping("app/popup/orderCnclDelivProd/orderCnclDelivProdInfoPop.do")
	public String orderCnclDelivProdInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderCnclDelivProd/orderCnclDelivProdInfoPop";
	}

	@RequestMapping("app/popup/orderCnclDelivProd/orderCnclDelivProdSearchPop.do")
	public String orderCnclDelivProdSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderCnclDelivProd/orderCnclDelivProdSearchPop";
	}

	/**
	*  List Page
	*
	* @param OrderCnclDelivProdExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderCnclDelivProd/getOrderCnclDelivProdPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getOrderCnclDelivProdPageList(HttpServletRequest request, @Valid final OrderCnclDelivProdExDto params) throws Exception {
		final int totalRows = this.orderCnclDelivProdService.getOrderCnclDelivProdListCount(params);
		final List<OrderCnclDelivProdExDto> orderCnclDelivProdList = this.orderCnclDelivProdService.getOrderCnclDelivProdList(params);
		
		Map<String,Object> mapData = (new ResponseList(orderCnclDelivProdList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param OrderCnclDelivProdExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderCnclDelivProd/getOrderCnclDelivProdList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<OrderCnclDelivProdExDto> getOrderCnclDelivProdList(@Valid final OrderCnclDelivProdExDto params) throws Exception {
		final List<OrderCnclDelivProdExDto> listData = this.orderCnclDelivProdService.getOrderCnclDelivProdList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param OrderCnclDelivProdExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/orderCnclDelivProd/getOrderCnclDelivProd.do")
	@ResponseBody
	public Object getOrderCnclDelivProd(@Valid final OrderCnclDelivProdExDto params) throws Exception {
		final OrderCnclDelivProdExDto mapData = this.orderCnclDelivProdService.getOrderCnclDelivProd(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param OrderCnclDelivProdExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderCnclDelivProd/insertOrderCnclDelivProd.do")
	@ResponseBody
	public Map<String,Object> insertOrderCnclDelivProd(@Valid final OrderCnclDelivProdExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderCnclDelivProdService.insertOrderCnclDelivProd(params);
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
	* @param OrderCnclDelivProdExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderCnclDelivProd/updateOrderCnclDelivProd.do")
	@ResponseBody
	public Map<String,Object> updateOrderCnclDelivProd(@Valid final OrderCnclDelivProdExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderCnclDelivProdService.updateOrderCnclDelivProd(params);
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
	* @param OrderCnclDelivProdExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderCnclDelivProd/deleteOrderCnclDelivProd.do")
	@ResponseBody
	public Map<String,Object> deleteOrderCnclDelivProd(@Valid final OrderCnclDelivProdExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderCnclDelivProdService.deleteOrderCnclDelivProd(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/orderCnclDelivProd/getOrderCnclDelivProdExcelDown.do")
	@ResponseBody
	public Object getOrderCnclDelivProdExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		OrderCnclDelivProdExDto orderCnclDelivProdExDto = new OrderCnclDelivProdExDto();
		orderCnclDelivProdExDto = (OrderCnclDelivProdExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), orderCnclDelivProdExDto);
		final List<OrderCnclDelivProdExDto> listData = this.orderCnclDelivProdService.getOrderCnclDelivProdList(orderCnclDelivProdExDto);
		
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
