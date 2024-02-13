package com.app.cartOption.controller;

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

import com.app.cartOption.model.CartOptionExDto;
import com.app.cartOption.service.CartOptionService;
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
public class CartOptionController {

	@Autowired
	private CartOptionService cartOptionService;

	@RequestMapping("app/cartOption/cartOptionList.do")
	public String cartOptionList() throws Exception {
		return "app/cartOption/cartOptionList";
	}

	@RequestMapping("app/popup/cartOption/cartOptionPop.do")
	public String cartOptionPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/cartOption/cartOptionPop";
	}

	@RequestMapping("app/popup/cartOption/cartOptionInfoPop.do")
	public String cartOptionInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/cartOption/cartOptionInfoPop";
	}

	@RequestMapping("app/popup/cartOption/cartOptionSearchPop.do")
	public String cartOptionSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/cartOption/cartOptionSearchPop";
	}

	/**
	*  List Page
	*
	* @param CartOptionExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/cartOption/getCartOptionPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCartOptionPageList(HttpServletRequest request, @Valid final CartOptionExDto params) throws Exception {
		final int totalRows = this.cartOptionService.getCartOptionListCount(params);
		final List<CartOptionExDto> cartOptionList = this.cartOptionService.getCartOptionList(params);
		
		Map<String,Object> mapData = (new ResponseList(cartOptionList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param CartOptionExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/cartOption/getCartOptionList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CartOptionExDto> getCartOptionList(@Valid final CartOptionExDto params) throws Exception {
		final List<CartOptionExDto> listData = this.cartOptionService.getCartOptionList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param CartOptionExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/cartOption/getCartOption.do")
	@ResponseBody
	public Object getCartOption(@Valid final CartOptionExDto params) throws Exception {
		final CartOptionExDto mapData = this.cartOptionService.getCartOption(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param CartOptionExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/cartOption/insertCartOption.do")
	@ResponseBody
	public Map<String,Object> insertCartOption(@Valid final CartOptionExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.cartOptionService.insertCartOption(params);
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
	* @param CartOptionExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/cartOption/updateCartOption.do")
	@ResponseBody
	public Map<String,Object> updateCartOption(@Valid final CartOptionExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.cartOptionService.updateCartOption(params);
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
	* @param CartOptionExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/cartOption/deleteCartOption.do")
	@ResponseBody
	public Map<String,Object> deleteCartOption(@Valid final CartOptionExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.cartOptionService.deleteCartOption(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/cartOption/getCartOptionExcelDown.do")
	@ResponseBody
	public Object getCartOptionExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CartOptionExDto cartOptionExDto = new CartOptionExDto();
		cartOptionExDto = (CartOptionExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), cartOptionExDto);
		final List<CartOptionExDto> listData = this.cartOptionService.getCartOptionList(cartOptionExDto);
		
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
