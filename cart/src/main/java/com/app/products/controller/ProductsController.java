package com.app.products.controller;

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

import com.app.products.model.ProductsExDto;
import com.app.products.service.ProductsService;
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
public class ProductsController {

	@Autowired
	private ProductsService productsService;

	@RequestMapping("app/products/productsList.do")
	public String productsList() throws Exception {
		return "app/products/productsList";
	}

	@RequestMapping("app/popup/products/productsPop.do")
	public String productsPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/products/productsPop";
	}

	@RequestMapping("app/popup/products/productsInfoPop.do")
	public String productsInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/products/productsInfoPop";
	}

	@RequestMapping("app/popup/products/productsSearchPop.do")
	public String productsSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/products/productsSearchPop";
	}

	/**
	*  List Page
	*
	* @param ProductsExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/products/getProductsPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getProductsPageList(HttpServletRequest request, @Valid final ProductsExDto params) throws Exception {
		final int totalRows = this.productsService.getProductsListCount(params);
		final List<ProductsExDto> productsList = this.productsService.getProductsList(params);
		
		Map<String,Object> mapData = (new ResponseList(productsList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param ProductsExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/products/getProductsList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<ProductsExDto> getProductsList(@Valid final ProductsExDto params) throws Exception {
		final List<ProductsExDto> listData = this.productsService.getProductsList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param ProductsExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/products/getProducts.do")
	@ResponseBody
	public Object getProducts(@Valid final ProductsExDto params) throws Exception {
		final ProductsExDto mapData = this.productsService.getProducts(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param ProductsExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/products/insertProducts.do")
	@ResponseBody
	public Map<String,Object> insertProducts(@Valid final ProductsExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.productsService.insertProducts(params);
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
	* @param ProductsExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/products/updateProducts.do")
	@ResponseBody
	public Map<String,Object> updateProducts(@Valid final ProductsExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.productsService.updateProducts(params);
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
	* @param ProductsExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/products/deleteProducts.do")
	@ResponseBody
	public Map<String,Object> deleteProducts(@Valid final ProductsExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.productsService.deleteProducts(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/products/getProductsExcelDown.do")
	@ResponseBody
	public Object getProductsExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		ProductsExDto productsExDto = new ProductsExDto();
		productsExDto = (ProductsExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), productsExDto);
		final List<ProductsExDto> listData = this.productsService.getProductsList(productsExDto);
		
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
