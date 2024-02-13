package com.app.prodContLang.controller;

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

import com.app.prodContLang.model.ProdContLangExDto;
import com.app.prodContLang.service.ProdContLangService;
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
public class ProdContLangController {

	@Autowired
	private ProdContLangService prodContLangService;

	@RequestMapping("app/prodContLang/prodContLangList.do")
	public String prodContLangList() throws Exception {
		return "app/prodContLang/prodContLangList";
	}

	@RequestMapping("app/popup/prodContLang/prodContLangPop.do")
	public String prodContLangPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodContLang/prodContLangPop";
	}

	@RequestMapping("app/popup/prodContLang/prodContLangInfoPop.do")
	public String prodContLangInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodContLang/prodContLangInfoPop";
	}

	@RequestMapping("app/popup/prodContLang/prodContLangSearchPop.do")
	public String prodContLangSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodContLang/prodContLangSearchPop";
	}

	/**
	*  List Page
	*
	* @param ProdContLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/prodContLang/getProdContLangPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getProdContLangPageList(HttpServletRequest request, @Valid final ProdContLangExDto params) throws Exception {
		final int totalRows = this.prodContLangService.getProdContLangListCount(params);
		final List<ProdContLangExDto> prodContLangList = this.prodContLangService.getProdContLangList(params);
		
		Map<String,Object> mapData = (new ResponseList(prodContLangList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param ProdContLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/prodContLang/getProdContLangList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<ProdContLangExDto> getProdContLangList(@Valid final ProdContLangExDto params) throws Exception {
		final List<ProdContLangExDto> listData = this.prodContLangService.getProdContLangList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param ProdContLangExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/prodContLang/getProdContLang.do")
	@ResponseBody
	public Object getProdContLang(@Valid final ProdContLangExDto params) throws Exception {
		final ProdContLangExDto mapData = this.prodContLangService.getProdContLang(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param ProdContLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodContLang/insertProdContLang.do")
	@ResponseBody
	public Map<String,Object> insertProdContLang(@Valid final ProdContLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodContLangService.insertProdContLang(params);
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
	* @param ProdContLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodContLang/updateProdContLang.do")
	@ResponseBody
	public Map<String,Object> updateProdContLang(@Valid final ProdContLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodContLangService.updateProdContLang(params);
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
	* @param ProdContLangExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodContLang/deleteProdContLang.do")
	@ResponseBody
	public Map<String,Object> deleteProdContLang(@Valid final ProdContLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodContLangService.deleteProdContLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/prodContLang/getProdContLangExcelDown.do")
	@ResponseBody
	public Object getProdContLangExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		ProdContLangExDto prodContLangExDto = new ProdContLangExDto();
		prodContLangExDto = (ProdContLangExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), prodContLangExDto);
		final List<ProdContLangExDto> listData = this.prodContLangService.getProdContLangList(prodContLangExDto);
		
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
