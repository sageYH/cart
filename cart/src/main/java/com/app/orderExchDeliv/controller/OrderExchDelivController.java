package com.app.orderExchDeliv.controller;

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

import com.app.orderExchDeliv.model.OrderExchDelivExDto;
import com.app.orderExchDeliv.service.OrderExchDelivService;
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
public class OrderExchDelivController {

	@Autowired
	private OrderExchDelivService orderExchDelivService;

	@RequestMapping("app/orderExchDeliv/orderExchDelivList.do")
	public String orderExchDelivList() throws Exception {
		return "app/orderExchDeliv/orderExchDelivList";
	}

	@RequestMapping("app/popup/orderExchDeliv/orderExchDelivPop.do")
	public String orderExchDelivPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderExchDeliv/orderExchDelivPop";
	}

	@RequestMapping("app/popup/orderExchDeliv/orderExchDelivInfoPop.do")
	public String orderExchDelivInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderExchDeliv/orderExchDelivInfoPop";
	}

	@RequestMapping("app/popup/orderExchDeliv/orderExchDelivSearchPop.do")
	public String orderExchDelivSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderExchDeliv/orderExchDelivSearchPop";
	}

	/**
	*  List Page
	*
	* @param OrderExchDelivExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderExchDeliv/getOrderExchDelivPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getOrderExchDelivPageList(HttpServletRequest request, @Valid final OrderExchDelivExDto params) throws Exception {
		final int totalRows = this.orderExchDelivService.getOrderExchDelivListCount(params);
		final List<OrderExchDelivExDto> orderExchDelivList = this.orderExchDelivService.getOrderExchDelivList(params);
		
		Map<String,Object> mapData = (new ResponseList(orderExchDelivList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param OrderExchDelivExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderExchDeliv/getOrderExchDelivList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<OrderExchDelivExDto> getOrderExchDelivList(@Valid final OrderExchDelivExDto params) throws Exception {
		final List<OrderExchDelivExDto> listData = this.orderExchDelivService.getOrderExchDelivList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param OrderExchDelivExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/orderExchDeliv/getOrderExchDeliv.do")
	@ResponseBody
	public Object getOrderExchDeliv(@Valid final OrderExchDelivExDto params) throws Exception {
		final OrderExchDelivExDto mapData = this.orderExchDelivService.getOrderExchDeliv(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param OrderExchDelivExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderExchDeliv/insertOrderExchDeliv.do")
	@ResponseBody
	public Map<String,Object> insertOrderExchDeliv(@Valid final OrderExchDelivExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderExchDelivService.insertOrderExchDeliv(params);
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
	* @param OrderExchDelivExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderExchDeliv/updateOrderExchDeliv.do")
	@ResponseBody
	public Map<String,Object> updateOrderExchDeliv(@Valid final OrderExchDelivExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderExchDelivService.updateOrderExchDeliv(params);
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
	* @param OrderExchDelivExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderExchDeliv/deleteOrderExchDeliv.do")
	@ResponseBody
	public Map<String,Object> deleteOrderExchDeliv(@Valid final OrderExchDelivExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderExchDelivService.deleteOrderExchDeliv(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/orderExchDeliv/getOrderExchDelivExcelDown.do")
	@ResponseBody
	public Object getOrderExchDelivExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		OrderExchDelivExDto orderExchDelivExDto = new OrderExchDelivExDto();
		orderExchDelivExDto = (OrderExchDelivExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), orderExchDelivExDto);
		final List<OrderExchDelivExDto> listData = this.orderExchDelivService.getOrderExchDelivList(orderExchDelivExDto);
		
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
