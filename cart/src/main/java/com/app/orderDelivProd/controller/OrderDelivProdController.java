package com.app.orderDelivProd.controller;

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

import com.app.orderDelivProd.model.OrderDelivProdExDto;
import com.app.orderDelivProd.service.OrderDelivProdService;
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
public class OrderDelivProdController {

	@Autowired
	private OrderDelivProdService orderDelivProdService;

	@RequestMapping("app/orderDelivProd/orderDelivProdList.do")
	public String orderDelivProdList() throws Exception {
		return "app/orderDelivProd/orderDelivProdList";
	}

	@RequestMapping("app/popup/orderDelivProd/orderDelivProdPop.do")
	public String orderDelivProdPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderDelivProd/orderDelivProdPop";
	}

	@RequestMapping("app/popup/orderDelivProd/orderDelivProdInfoPop.do")
	public String orderDelivProdInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderDelivProd/orderDelivProdInfoPop";
	}

	@RequestMapping("app/popup/orderDelivProd/orderDelivProdSearchPop.do")
	public String orderDelivProdSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderDelivProd/orderDelivProdSearchPop";
	}

	/**
	*  List Page
	*
	* @param OrderDelivProdExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderDelivProd/getOrderDelivProdPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getOrderDelivProdPageList(HttpServletRequest request, @Valid final OrderDelivProdExDto params) throws Exception {
		final int totalRows = this.orderDelivProdService.getOrderDelivProdListCount(params);
		final List<OrderDelivProdExDto> orderDelivProdList = this.orderDelivProdService.getOrderDelivProdList(params);
		
		Map<String,Object> mapData = (new ResponseList(orderDelivProdList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param OrderDelivProdExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderDelivProd/getOrderDelivProdList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<OrderDelivProdExDto> getOrderDelivProdList(@Valid final OrderDelivProdExDto params) throws Exception {
		final List<OrderDelivProdExDto> listData = this.orderDelivProdService.getOrderDelivProdList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param OrderDelivProdExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/orderDelivProd/getOrderDelivProd.do")
	@ResponseBody
	public Object getOrderDelivProd(@Valid final OrderDelivProdExDto params) throws Exception {
		final OrderDelivProdExDto mapData = this.orderDelivProdService.getOrderDelivProd(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param OrderDelivProdExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderDelivProd/insertOrderDelivProd.do")
	@ResponseBody
	public Map<String,Object> insertOrderDelivProd(@Valid final OrderDelivProdExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderDelivProdService.insertOrderDelivProd(params);
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
	* @param OrderDelivProdExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderDelivProd/updateOrderDelivProd.do")
	@ResponseBody
	public Map<String,Object> updateOrderDelivProd(@Valid final OrderDelivProdExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderDelivProdService.updateOrderDelivProd(params);
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
	* @param OrderDelivProdExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderDelivProd/deleteOrderDelivProd.do")
	@ResponseBody
	public Map<String,Object> deleteOrderDelivProd(@Valid final OrderDelivProdExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderDelivProdService.deleteOrderDelivProd(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/orderDelivProd/getOrderDelivProdExcelDown.do")
	@ResponseBody
	public Object getOrderDelivProdExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		OrderDelivProdExDto orderDelivProdExDto = new OrderDelivProdExDto();
		orderDelivProdExDto = (OrderDelivProdExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), orderDelivProdExDto);
		final List<OrderDelivProdExDto> listData = this.orderDelivProdService.getOrderDelivProdList(orderDelivProdExDto);
		
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
