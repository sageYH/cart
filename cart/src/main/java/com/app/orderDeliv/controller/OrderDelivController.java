package com.app.orderDeliv.controller;

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

import com.app.orderDeliv.model.OrderDelivExDto;
import com.app.orderDeliv.service.OrderDelivService;
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
public class OrderDelivController {

	@Autowired
	private OrderDelivService orderDelivService;

	@RequestMapping("app/orderDeliv/orderDelivList.do")
	public String orderDelivList() throws Exception {
		return "app/orderDeliv/orderDelivList";
	}

	@RequestMapping("app/popup/orderDeliv/orderDelivPop.do")
	public String orderDelivPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderDeliv/orderDelivPop";
	}

	@RequestMapping("app/popup/orderDeliv/orderDelivInfoPop.do")
	public String orderDelivInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderDeliv/orderDelivInfoPop";
	}

	@RequestMapping("app/popup/orderDeliv/orderDelivSearchPop.do")
	public String orderDelivSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderDeliv/orderDelivSearchPop";
	}

	/**
	*  List Page
	*
	* @param OrderDelivExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderDeliv/getOrderDelivPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getOrderDelivPageList(HttpServletRequest request, @Valid final OrderDelivExDto params) throws Exception {
		final int totalRows = this.orderDelivService.getOrderDelivListCount(params);
		final List<OrderDelivExDto> orderDelivList = this.orderDelivService.getOrderDelivList(params);
		
		Map<String,Object> mapData = (new ResponseList(orderDelivList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param OrderDelivExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderDeliv/getOrderDelivList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<OrderDelivExDto> getOrderDelivList(@Valid final OrderDelivExDto params) throws Exception {
		final List<OrderDelivExDto> listData = this.orderDelivService.getOrderDelivList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param OrderDelivExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/orderDeliv/getOrderDeliv.do")
	@ResponseBody
	public Object getOrderDeliv(@Valid final OrderDelivExDto params) throws Exception {
		final OrderDelivExDto mapData = this.orderDelivService.getOrderDeliv(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param OrderDelivExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderDeliv/insertOrderDeliv.do")
	@ResponseBody
	public Map<String,Object> insertOrderDeliv(@Valid final OrderDelivExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderDelivService.insertOrderDeliv(params);
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
	* @param OrderDelivExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderDeliv/updateOrderDeliv.do")
	@ResponseBody
	public Map<String,Object> updateOrderDeliv(@Valid final OrderDelivExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderDelivService.updateOrderDeliv(params);
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
	* @param OrderDelivExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderDeliv/deleteOrderDeliv.do")
	@ResponseBody
	public Map<String,Object> deleteOrderDeliv(@Valid final OrderDelivExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderDelivService.deleteOrderDeliv(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/orderDeliv/getOrderDelivExcelDown.do")
	@ResponseBody
	public Object getOrderDelivExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		OrderDelivExDto orderDelivExDto = new OrderDelivExDto();
		orderDelivExDto = (OrderDelivExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), orderDelivExDto);
		final List<OrderDelivExDto> listData = this.orderDelivService.getOrderDelivList(orderDelivExDto);
		
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
