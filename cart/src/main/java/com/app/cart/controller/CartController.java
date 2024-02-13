package com.app.cart.controller;

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

import com.app.cart.model.CartExDto;
import com.app.cart.service.CartService;
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
public class CartController {

	@Autowired
	private CartService cartService;

	@RequestMapping("app/cart/cartList.do")
	public String cartList() throws Exception {
		return "app/cart/cartList";
	}

	@RequestMapping("app/popup/cart/cartPop.do")
	public String cartPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/cart/cartPop";
	}

	@RequestMapping("app/popup/cart/cartInfoPop.do")
	public String cartInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/cart/cartInfoPop";
	}

	@RequestMapping("app/popup/cart/cartSearchPop.do")
	public String cartSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/cart/cartSearchPop";
	}

	/**
	*  List Page
	*
	* @param CartExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/cart/getCartPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCartPageList(HttpServletRequest request, @Valid final CartExDto params) throws Exception {
		final int totalRows = this.cartService.getCartListCount(params);
		final List<CartExDto> cartList = this.cartService.getCartList(params);
		
		Map<String,Object> mapData = (new ResponseList(cartList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param CartExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/cart/getCartList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CartExDto> getCartList(@Valid final CartExDto params) throws Exception {
		final List<CartExDto> listData = this.cartService.getCartList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param CartExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/cart/getCart.do")
	@ResponseBody
	public Object getCart(@Valid final CartExDto params) throws Exception {
		final CartExDto mapData = this.cartService.getCart(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param CartExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/cart/insertCart.do")
	@ResponseBody
	public Map<String,Object> insertCart(@Valid final CartExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.cartService.insertCart(params);
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
	* @param CartExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/cart/updateCart.do")
	@ResponseBody
	public Map<String,Object> updateCart(@Valid final CartExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.cartService.updateCart(params);
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
	* @param CartExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/cart/deleteCart.do")
	@ResponseBody
	public Map<String,Object> deleteCart(@Valid final CartExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.cartService.deleteCart(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/cart/getCartExcelDown.do")
	@ResponseBody
	public Object getCartExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CartExDto cartExDto = new CartExDto();
		cartExDto = (CartExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), cartExDto);
		final List<CartExDto> listData = this.cartService.getCartList(cartExDto);
		
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
