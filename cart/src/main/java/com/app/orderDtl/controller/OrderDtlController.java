package com.app.orderDtl.controller;

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

import com.app.orderDtl.model.OrderDtlExDto;
import com.app.orderDtl.service.OrderDtlService;
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
public class OrderDtlController {

	@Autowired
	private OrderDtlService orderDtlService;

	@RequestMapping("app/orderDtl/orderDtlList.do")
	public String orderDtlList() throws Exception {
		return "app/orderDtl/orderDtlList";
	}

	@RequestMapping("app/popup/orderDtl/orderDtlPop.do")
	public String orderDtlPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderDtl/orderDtlPop";
	}

	@RequestMapping("app/popup/orderDtl/orderDtlInfoPop.do")
	public String orderDtlInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderDtl/orderDtlInfoPop";
	}

	@RequestMapping("app/popup/orderDtl/orderDtlSearchPop.do")
	public String orderDtlSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderDtl/orderDtlSearchPop";
	}

	/**
	*  List Page
	*
	* @param OrderDtlExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderDtl/getOrderDtlPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getOrderDtlPageList(HttpServletRequest request, @Valid final OrderDtlExDto params) throws Exception {
		final int totalRows = this.orderDtlService.getOrderDtlListCount(params);
		final List<OrderDtlExDto> orderDtlList = this.orderDtlService.getOrderDtlList(params);
		
		Map<String,Object> mapData = (new ResponseList(orderDtlList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param OrderDtlExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderDtl/getOrderDtlList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<OrderDtlExDto> getOrderDtlList(@Valid final OrderDtlExDto params) throws Exception {
		final List<OrderDtlExDto> listData = this.orderDtlService.getOrderDtlList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param OrderDtlExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/orderDtl/getOrderDtl.do")
	@ResponseBody
	public Object getOrderDtl(@Valid final OrderDtlExDto params) throws Exception {
		final OrderDtlExDto mapData = this.orderDtlService.getOrderDtl(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param OrderDtlExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderDtl/insertOrderDtl.do")
	@ResponseBody
	public Map<String,Object> insertOrderDtl(@Valid final OrderDtlExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderDtlService.insertOrderDtl(params);
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
	* @param OrderDtlExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderDtl/updateOrderDtl.do")
	@ResponseBody
	public Map<String,Object> updateOrderDtl(@Valid final OrderDtlExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderDtlService.updateOrderDtl(params);
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
	* @param OrderDtlExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderDtl/deleteOrderDtl.do")
	@ResponseBody
	public Map<String,Object> deleteOrderDtl(@Valid final OrderDtlExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderDtlService.deleteOrderDtl(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/orderDtl/getOrderDtlExcelDown.do")
	@ResponseBody
	public Object getOrderDtlExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		OrderDtlExDto orderDtlExDto = new OrderDtlExDto();
		orderDtlExDto = (OrderDtlExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), orderDtlExDto);
		final List<OrderDtlExDto> listData = this.orderDtlService.getOrderDtlList(orderDtlExDto);
		
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
