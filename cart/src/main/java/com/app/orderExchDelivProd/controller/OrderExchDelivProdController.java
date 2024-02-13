package com.app.orderExchDelivProd.controller;

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

import com.app.orderExchDelivProd.model.OrderExchDelivProdExDto;
import com.app.orderExchDelivProd.service.OrderExchDelivProdService;
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
public class OrderExchDelivProdController {

	@Autowired
	private OrderExchDelivProdService orderExchDelivProdService;

	@RequestMapping("app/orderExchDelivProd/orderExchDelivProdList.do")
	public String orderExchDelivProdList() throws Exception {
		return "app/orderExchDelivProd/orderExchDelivProdList";
	}

	@RequestMapping("app/popup/orderExchDelivProd/orderExchDelivProdPop.do")
	public String orderExchDelivProdPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderExchDelivProd/orderExchDelivProdPop";
	}

	@RequestMapping("app/popup/orderExchDelivProd/orderExchDelivProdInfoPop.do")
	public String orderExchDelivProdInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderExchDelivProd/orderExchDelivProdInfoPop";
	}

	@RequestMapping("app/popup/orderExchDelivProd/orderExchDelivProdSearchPop.do")
	public String orderExchDelivProdSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderExchDelivProd/orderExchDelivProdSearchPop";
	}

	/**
	*  List Page
	*
	* @param OrderExchDelivProdExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderExchDelivProd/getOrderExchDelivProdPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getOrderExchDelivProdPageList(HttpServletRequest request, @Valid final OrderExchDelivProdExDto params) throws Exception {
		final int totalRows = this.orderExchDelivProdService.getOrderExchDelivProdListCount(params);
		final List<OrderExchDelivProdExDto> orderExchDelivProdList = this.orderExchDelivProdService.getOrderExchDelivProdList(params);
		
		Map<String,Object> mapData = (new ResponseList(orderExchDelivProdList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param OrderExchDelivProdExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderExchDelivProd/getOrderExchDelivProdList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<OrderExchDelivProdExDto> getOrderExchDelivProdList(@Valid final OrderExchDelivProdExDto params) throws Exception {
		final List<OrderExchDelivProdExDto> listData = this.orderExchDelivProdService.getOrderExchDelivProdList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param OrderExchDelivProdExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/orderExchDelivProd/getOrderExchDelivProd.do")
	@ResponseBody
	public Object getOrderExchDelivProd(@Valid final OrderExchDelivProdExDto params) throws Exception {
		final OrderExchDelivProdExDto mapData = this.orderExchDelivProdService.getOrderExchDelivProd(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param OrderExchDelivProdExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderExchDelivProd/insertOrderExchDelivProd.do")
	@ResponseBody
	public Map<String,Object> insertOrderExchDelivProd(@Valid final OrderExchDelivProdExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderExchDelivProdService.insertOrderExchDelivProd(params);
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
	* @param OrderExchDelivProdExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderExchDelivProd/updateOrderExchDelivProd.do")
	@ResponseBody
	public Map<String,Object> updateOrderExchDelivProd(@Valid final OrderExchDelivProdExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderExchDelivProdService.updateOrderExchDelivProd(params);
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
	* @param OrderExchDelivProdExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderExchDelivProd/deleteOrderExchDelivProd.do")
	@ResponseBody
	public Map<String,Object> deleteOrderExchDelivProd(@Valid final OrderExchDelivProdExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderExchDelivProdService.deleteOrderExchDelivProd(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/orderExchDelivProd/getOrderExchDelivProdExcelDown.do")
	@ResponseBody
	public Object getOrderExchDelivProdExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		OrderExchDelivProdExDto orderExchDelivProdExDto = new OrderExchDelivProdExDto();
		orderExchDelivProdExDto = (OrderExchDelivProdExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), orderExchDelivProdExDto);
		final List<OrderExchDelivProdExDto> listData = this.orderExchDelivProdService.getOrderExchDelivProdList(orderExchDelivProdExDto);
		
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
