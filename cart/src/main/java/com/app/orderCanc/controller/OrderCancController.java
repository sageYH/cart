package com.app.orderCanc.controller;

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

import com.app.orderCanc.model.OrderCancExDto;
import com.app.orderCanc.service.OrderCancService;
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
public class OrderCancController {

	@Autowired
	private OrderCancService orderCancService;

	@RequestMapping("app/orderCanc/orderCancList.do")
	public String orderCancList() throws Exception {
		return "app/orderCanc/orderCancList";
	}

	@RequestMapping("app/popup/orderCanc/orderCancPop.do")
	public String orderCancPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderCanc/orderCancPop";
	}

	@RequestMapping("app/popup/orderCanc/orderCancInfoPop.do")
	public String orderCancInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderCanc/orderCancInfoPop";
	}

	@RequestMapping("app/popup/orderCanc/orderCancSearchPop.do")
	public String orderCancSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/orderCanc/orderCancSearchPop";
	}

	/**
	*  List Page
	*
	* @param OrderCancExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderCanc/getOrderCancPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getOrderCancPageList(HttpServletRequest request, @Valid final OrderCancExDto params) throws Exception {
		final int totalRows = this.orderCancService.getOrderCancListCount(params);
		final List<OrderCancExDto> orderCancList = this.orderCancService.getOrderCancList(params);
		
		Map<String,Object> mapData = (new ResponseList(orderCancList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param OrderCancExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/orderCanc/getOrderCancList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<OrderCancExDto> getOrderCancList(@Valid final OrderCancExDto params) throws Exception {
		final List<OrderCancExDto> listData = this.orderCancService.getOrderCancList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param OrderCancExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/orderCanc/getOrderCanc.do")
	@ResponseBody
	public Object getOrderCanc(@Valid final OrderCancExDto params) throws Exception {
		final OrderCancExDto mapData = this.orderCancService.getOrderCanc(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param OrderCancExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderCanc/insertOrderCanc.do")
	@ResponseBody
	public Map<String,Object> insertOrderCanc(@Valid final OrderCancExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderCancService.insertOrderCanc(params);
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
	* @param OrderCancExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderCanc/updateOrderCanc.do")
	@ResponseBody
	public Map<String,Object> updateOrderCanc(@Valid final OrderCancExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderCancService.updateOrderCanc(params);
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
	* @param OrderCancExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/orderCanc/deleteOrderCanc.do")
	@ResponseBody
	public Map<String,Object> deleteOrderCanc(@Valid final OrderCancExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.orderCancService.deleteOrderCanc(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/orderCanc/getOrderCancExcelDown.do")
	@ResponseBody
	public Object getOrderCancExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		OrderCancExDto orderCancExDto = new OrderCancExDto();
		orderCancExDto = (OrderCancExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), orderCancExDto);
		final List<OrderCancExDto> listData = this.orderCancService.getOrderCancList(orderCancExDto);
		
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
