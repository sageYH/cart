package com.app.productsLang.controller;

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

import com.app.productsLang.model.ProductsLangExDto;
import com.app.productsLang.service.ProductsLangService;
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
public class ProductsLangController {

	@Autowired
	private ProductsLangService productsLangService;

	@RequestMapping("app/productsLang/productsLangList.do")
	public String productsLangList() throws Exception {
		return "app/productsLang/productsLangList";
	}

	@RequestMapping("app/popup/productsLang/productsLangPop.do")
	public String productsLangPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/productsLang/productsLangPop";
	}

	@RequestMapping("app/popup/productsLang/productsLangInfoPop.do")
	public String productsLangInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/productsLang/productsLangInfoPop";
	}

	@RequestMapping("app/popup/productsLang/productsLangSearchPop.do")
	public String productsLangSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/productsLang/productsLangSearchPop";
	}

	/**
	*  List Page
	*
	* @param ProductsLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/productsLang/getProductsLangPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getProductsLangPageList(HttpServletRequest request, @Valid final ProductsLangExDto params) throws Exception {
		final int totalRows = this.productsLangService.getProductsLangListCount(params);
		final List<ProductsLangExDto> productsLangList = this.productsLangService.getProductsLangList(params);
		
		Map<String,Object> mapData = (new ResponseList(productsLangList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param ProductsLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/productsLang/getProductsLangList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<ProductsLangExDto> getProductsLangList(@Valid final ProductsLangExDto params) throws Exception {
		final List<ProductsLangExDto> listData = this.productsLangService.getProductsLangList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param ProductsLangExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/productsLang/getProductsLang.do")
	@ResponseBody
	public Object getProductsLang(@Valid final ProductsLangExDto params) throws Exception {
		final ProductsLangExDto mapData = this.productsLangService.getProductsLang(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param ProductsLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/productsLang/insertProductsLang.do")
	@ResponseBody
	public Map<String,Object> insertProductsLang(@Valid final ProductsLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.productsLangService.insertProductsLang(params);
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
	* @param ProductsLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/productsLang/updateProductsLang.do")
	@ResponseBody
	public Map<String,Object> updateProductsLang(@Valid final ProductsLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.productsLangService.updateProductsLang(params);
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
	* @param ProductsLangExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/productsLang/deleteProductsLang.do")
	@ResponseBody
	public Map<String,Object> deleteProductsLang(@Valid final ProductsLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.productsLangService.deleteProductsLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/productsLang/getProductsLangExcelDown.do")
	@ResponseBody
	public Object getProductsLangExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		ProductsLangExDto productsLangExDto = new ProductsLangExDto();
		productsLangExDto = (ProductsLangExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), productsLangExDto);
		final List<ProductsLangExDto> listData = this.productsLangService.getProductsLangList(productsLangExDto);
		
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
