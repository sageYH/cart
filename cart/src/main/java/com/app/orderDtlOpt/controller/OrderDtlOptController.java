package com.app.orderDtlOpt.controller;

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

import com.app.orderDtlOpt.model.OrderDtlOptExDto;
import com.app.orderDtlOpt.service.OrderDtlOptService;
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
public class OrderDtlOptController {

	@Autowired
	private OrderDtlOptService orderDtlOptService;

	@RequestMapping("app/orderDtlOpt/orderDtlOptList.do")
	public String orderDtlOptList() throws Exception {
		return "app/orderDtlOpt/orderDtlOptList";
	}

	@RequestMapping("app/popup/orderDtlOpt/orderDtlOptPop.do")
	public String orderDtlOptPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderDtlOpt/orderDtlOptPop";
	}

	@RequestMapping("app/popup/orderDtlOpt/orderDtlOptInfoPop.do")
	public String orderDtlOptInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderDtlOpt/orderDtlOptInfoPop";
	}

	@RequestMapping("app/popup/orderDtlOpt/orderDtlOptSearchPop.do")
	public String orderDtlOptSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderDtlOpt/orderDtlOptSearchPop";
	}

	/**
	*  List Page
	*
	* @param OrderDtlOptExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderDtlOpt/getOrderDtlOptPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getOrderDtlOptPageList(HttpServletRequest request, @Valid final OrderDtlOptExDto params) throws Exception {
		final int totalRows = this.orderDtlOptService.getOrderDtlOptListCount(params);
		final List<OrderDtlOptExDto> orderDtlOptList = this.orderDtlOptService.getOrderDtlOptList(params);
		
		Map<String,Object> mapData = (new ResponseList(orderDtlOptList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param OrderDtlOptExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderDtlOpt/getOrderDtlOptList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<OrderDtlOptExDto> getOrderDtlOptList(@Valid final OrderDtlOptExDto params) throws Exception {
		final List<OrderDtlOptExDto> listData = this.orderDtlOptService.getOrderDtlOptList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param OrderDtlOptExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/orderDtlOpt/getOrderDtlOpt.do")
	@ResponseBody
	public Object getOrderDtlOpt(@Valid final OrderDtlOptExDto params) throws Exception {
		final OrderDtlOptExDto mapData = this.orderDtlOptService.getOrderDtlOpt(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param OrderDtlOptExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderDtlOpt/insertOrderDtlOpt.do")
	@ResponseBody
	public Map<String,Object> insertOrderDtlOpt(@Valid final OrderDtlOptExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderDtlOptService.insertOrderDtlOpt(params);
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
	* @param OrderDtlOptExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderDtlOpt/updateOrderDtlOpt.do")
	@ResponseBody
	public Map<String,Object> updateOrderDtlOpt(@Valid final OrderDtlOptExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderDtlOptService.updateOrderDtlOpt(params);
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
	* @param OrderDtlOptExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderDtlOpt/deleteOrderDtlOpt.do")
	@ResponseBody
	public Map<String,Object> deleteOrderDtlOpt(@Valid final OrderDtlOptExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderDtlOptService.deleteOrderDtlOpt(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/orderDtlOpt/getOrderDtlOptExcelDown.do")
	@ResponseBody
	public Object getOrderDtlOptExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		OrderDtlOptExDto orderDtlOptExDto = new OrderDtlOptExDto();
		orderDtlOptExDto = (OrderDtlOptExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), orderDtlOptExDto);
		final List<OrderDtlOptExDto> listData = this.orderDtlOptService.getOrderDtlOptList(orderDtlOptExDto);
		
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
