package com.app.cartIdInfo.controller;

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

import com.app.cartIdInfo.model.CartIdInfoExDto;
import com.app.cartIdInfo.service.CartIdInfoService;
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
public class CartIdInfoController {

	@Autowired
	private CartIdInfoService cartIdInfoService;

	@RequestMapping("app/cartIdInfo/cartIdInfoList.do")
	public String cartIdInfoList() throws Exception {
		return "app/cartIdInfo/cartIdInfoList";
	}

	@RequestMapping("app/popup/cartIdInfo/cartIdInfoPop.do")
	public String cartIdInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/cartIdInfo/cartIdInfoPop";
	}

	@RequestMapping("app/popup/cartIdInfo/cartIdInfoInfoPop.do")
	public String cartIdInfoInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/cartIdInfo/cartIdInfoInfoPop";
	}

	@RequestMapping("app/popup/cartIdInfo/cartIdInfoSearchPop.do")
	public String cartIdInfoSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/cartIdInfo/cartIdInfoSearchPop";
	}

	/**
	*  List Page
	*
	* @param CartIdInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/cartIdInfo/getCartIdInfoPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCartIdInfoPageList(HttpServletRequest request, @Valid final CartIdInfoExDto params) throws Exception {
		final int totalRows = this.cartIdInfoService.getCartIdInfoListCount(params);
		final List<CartIdInfoExDto> cartIdInfoList = this.cartIdInfoService.getCartIdInfoList(params);
		
		Map<String,Object> mapData = (new ResponseList(cartIdInfoList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param CartIdInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/cartIdInfo/getCartIdInfoList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CartIdInfoExDto> getCartIdInfoList(@Valid final CartIdInfoExDto params) throws Exception {
		final List<CartIdInfoExDto> listData = this.cartIdInfoService.getCartIdInfoList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param CartIdInfoExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/cartIdInfo/getCartIdInfo.do")
	@ResponseBody
	public Object getCartIdInfo(@Valid final CartIdInfoExDto params) throws Exception {
		final CartIdInfoExDto mapData = this.cartIdInfoService.getCartIdInfo(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param CartIdInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/cartIdInfo/insertCartIdInfo.do")
	@ResponseBody
	public Map<String,Object> insertCartIdInfo(@Valid final CartIdInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.cartIdInfoService.insertCartIdInfo(params);
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
	* @param CartIdInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/cartIdInfo/updateCartIdInfo.do")
	@ResponseBody
	public Map<String,Object> updateCartIdInfo(@Valid final CartIdInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.cartIdInfoService.updateCartIdInfo(params);
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
	* @param CartIdInfoExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/cartIdInfo/deleteCartIdInfo.do")
	@ResponseBody
	public Map<String,Object> deleteCartIdInfo(@Valid final CartIdInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.cartIdInfoService.deleteCartIdInfo(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/cartIdInfo/getCartIdInfoExcelDown.do")
	@ResponseBody
	public Object getCartIdInfoExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CartIdInfoExDto cartIdInfoExDto = new CartIdInfoExDto();
		cartIdInfoExDto = (CartIdInfoExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), cartIdInfoExDto);
		final List<CartIdInfoExDto> listData = this.cartIdInfoService.getCartIdInfoList(cartIdInfoExDto);
		
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
