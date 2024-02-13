package com.app.prodClassifLang.controller;

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

import com.app.prodClassifLang.model.ProdClassifLangExDto;
import com.app.prodClassifLang.service.ProdClassifLangService;
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
public class ProdClassifLangController {

	@Autowired
	private ProdClassifLangService prodClassifLangService;

	@RequestMapping("app/prodClassifLang/prodClassifLangList.do")
	public String prodClassifLangList() throws Exception {
		return "app/prodClassifLang/prodClassifLangList";
	}

	@RequestMapping("app/popup/prodClassifLang/prodClassifLangPop.do")
	public String prodClassifLangPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodClassifLang/prodClassifLangPop";
	}

	@RequestMapping("app/popup/prodClassifLang/prodClassifLangInfoPop.do")
	public String prodClassifLangInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodClassifLang/prodClassifLangInfoPop";
	}

	@RequestMapping("app/popup/prodClassifLang/prodClassifLangSearchPop.do")
	public String prodClassifLangSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodClassifLang/prodClassifLangSearchPop";
	}

	/**
	*  List Page
	*
	* @param ProdClassifLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/prodClassifLang/getProdClassifLangPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getProdClassifLangPageList(HttpServletRequest request, @Valid final ProdClassifLangExDto params) throws Exception {
		final int totalRows = this.prodClassifLangService.getProdClassifLangListCount(params);
		final List<ProdClassifLangExDto> prodClassifLangList = this.prodClassifLangService.getProdClassifLangList(params);
		
		Map<String,Object> mapData = (new ResponseList(prodClassifLangList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param ProdClassifLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/prodClassifLang/getProdClassifLangList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<ProdClassifLangExDto> getProdClassifLangList(@Valid final ProdClassifLangExDto params) throws Exception {
		final List<ProdClassifLangExDto> listData = this.prodClassifLangService.getProdClassifLangList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param ProdClassifLangExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/prodClassifLang/getProdClassifLang.do")
	@ResponseBody
	public Object getProdClassifLang(@Valid final ProdClassifLangExDto params) throws Exception {
		final ProdClassifLangExDto mapData = this.prodClassifLangService.getProdClassifLang(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param ProdClassifLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodClassifLang/insertProdClassifLang.do")
	@ResponseBody
	public Map<String,Object> insertProdClassifLang(@Valid final ProdClassifLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodClassifLangService.insertProdClassifLang(params);
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
	* @param ProdClassifLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodClassifLang/updateProdClassifLang.do")
	@ResponseBody
	public Map<String,Object> updateProdClassifLang(@Valid final ProdClassifLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodClassifLangService.updateProdClassifLang(params);
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
	* @param ProdClassifLangExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodClassifLang/deleteProdClassifLang.do")
	@ResponseBody
	public Map<String,Object> deleteProdClassifLang(@Valid final ProdClassifLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodClassifLangService.deleteProdClassifLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/prodClassifLang/getProdClassifLangExcelDown.do")
	@ResponseBody
	public Object getProdClassifLangExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		ProdClassifLangExDto prodClassifLangExDto = new ProdClassifLangExDto();
		prodClassifLangExDto = (ProdClassifLangExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), prodClassifLangExDto);
		final List<ProdClassifLangExDto> listData = this.prodClassifLangService.getProdClassifLangList(prodClassifLangExDto);
		
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
