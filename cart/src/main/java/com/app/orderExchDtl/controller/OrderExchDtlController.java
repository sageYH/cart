package com.app.orderExchDtl.controller;

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

import com.app.orderExchDtl.model.OrderExchDtlExDto;
import com.app.orderExchDtl.service.OrderExchDtlService;
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
public class OrderExchDtlController {

	@Autowired
	private OrderExchDtlService orderExchDtlService;

	@RequestMapping("app/orderExchDtl/orderExchDtlList.do")
	public String orderExchDtlList() throws Exception {
		return "app/orderExchDtl/orderExchDtlList";
	}

	@RequestMapping("app/popup/orderExchDtl/orderExchDtlPop.do")
	public String orderExchDtlPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderExchDtl/orderExchDtlPop";
	}

	@RequestMapping("app/popup/orderExchDtl/orderExchDtlInfoPop.do")
	public String orderExchDtlInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderExchDtl/orderExchDtlInfoPop";
	}

	@RequestMapping("app/popup/orderExchDtl/orderExchDtlSearchPop.do")
	public String orderExchDtlSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderExchDtl/orderExchDtlSearchPop";
	}

	/**
	*  List Page
	*
	* @param OrderExchDtlExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderExchDtl/getOrderExchDtlPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getOrderExchDtlPageList(HttpServletRequest request, @Valid final OrderExchDtlExDto params) throws Exception {
		final int totalRows = this.orderExchDtlService.getOrderExchDtlListCount(params);
		final List<OrderExchDtlExDto> orderExchDtlList = this.orderExchDtlService.getOrderExchDtlList(params);
		
		Map<String,Object> mapData = (new ResponseList(orderExchDtlList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param OrderExchDtlExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderExchDtl/getOrderExchDtlList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<OrderExchDtlExDto> getOrderExchDtlList(@Valid final OrderExchDtlExDto params) throws Exception {
		final List<OrderExchDtlExDto> listData = this.orderExchDtlService.getOrderExchDtlList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param OrderExchDtlExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/orderExchDtl/getOrderExchDtl.do")
	@ResponseBody
	public Object getOrderExchDtl(@Valid final OrderExchDtlExDto params) throws Exception {
		final OrderExchDtlExDto mapData = this.orderExchDtlService.getOrderExchDtl(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param OrderExchDtlExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderExchDtl/insertOrderExchDtl.do")
	@ResponseBody
	public Map<String,Object> insertOrderExchDtl(@Valid final OrderExchDtlExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderExchDtlService.insertOrderExchDtl(params);
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
	* @param OrderExchDtlExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderExchDtl/updateOrderExchDtl.do")
	@ResponseBody
	public Map<String,Object> updateOrderExchDtl(@Valid final OrderExchDtlExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderExchDtlService.updateOrderExchDtl(params);
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
	* @param OrderExchDtlExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderExchDtl/deleteOrderExchDtl.do")
	@ResponseBody
	public Map<String,Object> deleteOrderExchDtl(@Valid final OrderExchDtlExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderExchDtlService.deleteOrderExchDtl(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/orderExchDtl/getOrderExchDtlExcelDown.do")
	@ResponseBody
	public Object getOrderExchDtlExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		OrderExchDtlExDto orderExchDtlExDto = new OrderExchDtlExDto();
		orderExchDtlExDto = (OrderExchDtlExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), orderExchDtlExDto);
		final List<OrderExchDtlExDto> listData = this.orderExchDtlService.getOrderExchDtlList(orderExchDtlExDto);
		
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
