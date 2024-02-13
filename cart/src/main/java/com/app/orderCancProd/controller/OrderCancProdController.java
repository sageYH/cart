package com.app.orderCancProd.controller;

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

import com.app.orderCancProd.model.OrderCancProdExDto;
import com.app.orderCancProd.service.OrderCancProdService;
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
public class OrderCancProdController {

	@Autowired
	private OrderCancProdService orderCancProdService;

	@RequestMapping("app/orderCancProd/orderCancProdList.do")
	public String orderCancProdList() throws Exception {
		return "app/orderCancProd/orderCancProdList";
	}

	@RequestMapping("app/popup/orderCancProd/orderCancProdPop.do")
	public String orderCancProdPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderCancProd/orderCancProdPop";
	}

	@RequestMapping("app/popup/orderCancProd/orderCancProdInfoPop.do")
	public String orderCancProdInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderCancProd/orderCancProdInfoPop";
	}

	@RequestMapping("app/popup/orderCancProd/orderCancProdSearchPop.do")
	public String orderCancProdSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderCancProd/orderCancProdSearchPop";
	}

	/**
	*  List Page
	*
	* @param OrderCancProdExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderCancProd/getOrderCancProdPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getOrderCancProdPageList(HttpServletRequest request, @Valid final OrderCancProdExDto params) throws Exception {
		final int totalRows = this.orderCancProdService.getOrderCancProdListCount(params);
		final List<OrderCancProdExDto> orderCancProdList = this.orderCancProdService.getOrderCancProdList(params);
		
		Map<String,Object> mapData = (new ResponseList(orderCancProdList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param OrderCancProdExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderCancProd/getOrderCancProdList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<OrderCancProdExDto> getOrderCancProdList(@Valid final OrderCancProdExDto params) throws Exception {
		final List<OrderCancProdExDto> listData = this.orderCancProdService.getOrderCancProdList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param OrderCancProdExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/orderCancProd/getOrderCancProd.do")
	@ResponseBody
	public Object getOrderCancProd(@Valid final OrderCancProdExDto params) throws Exception {
		final OrderCancProdExDto mapData = this.orderCancProdService.getOrderCancProd(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param OrderCancProdExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderCancProd/insertOrderCancProd.do")
	@ResponseBody
	public Map<String,Object> insertOrderCancProd(@Valid final OrderCancProdExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderCancProdService.insertOrderCancProd(params);
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
	* @param OrderCancProdExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderCancProd/updateOrderCancProd.do")
	@ResponseBody
	public Map<String,Object> updateOrderCancProd(@Valid final OrderCancProdExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderCancProdService.updateOrderCancProd(params);
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
	* @param OrderCancProdExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderCancProd/deleteOrderCancProd.do")
	@ResponseBody
	public Map<String,Object> deleteOrderCancProd(@Valid final OrderCancProdExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderCancProdService.deleteOrderCancProd(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/orderCancProd/getOrderCancProdExcelDown.do")
	@ResponseBody
	public Object getOrderCancProdExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		OrderCancProdExDto orderCancProdExDto = new OrderCancProdExDto();
		orderCancProdExDto = (OrderCancProdExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), orderCancProdExDto);
		final List<OrderCancProdExDto> listData = this.orderCancProdService.getOrderCancProdList(orderCancProdExDto);
		
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
