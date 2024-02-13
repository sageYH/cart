package com.app.prodOptLang.controller;

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

import com.app.prodOptLang.model.ProdOptLangExDto;
import com.app.prodOptLang.service.ProdOptLangService;
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
public class ProdOptLangController {

	@Autowired
	private ProdOptLangService prodOptLangService;

	@RequestMapping("app/prodOptLang/prodOptLangList.do")
	public String prodOptLangList() throws Exception {
		return "app/prodOptLang/prodOptLangList";
	}

	@RequestMapping("app/popup/prodOptLang/prodOptLangPop.do")
	public String prodOptLangPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodOptLang/prodOptLangPop";
	}

	@RequestMapping("app/popup/prodOptLang/prodOptLangInfoPop.do")
	public String prodOptLangInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodOptLang/prodOptLangInfoPop";
	}

	@RequestMapping("app/popup/prodOptLang/prodOptLangSearchPop.do")
	public String prodOptLangSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodOptLang/prodOptLangSearchPop";
	}

	/**
	*  List Page
	*
	* @param ProdOptLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/prodOptLang/getProdOptLangPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getProdOptLangPageList(HttpServletRequest request, @Valid final ProdOptLangExDto params) throws Exception {
		final int totalRows = this.prodOptLangService.getProdOptLangListCount(params);
		final List<ProdOptLangExDto> prodOptLangList = this.prodOptLangService.getProdOptLangList(params);
		
		Map<String,Object> mapData = (new ResponseList(prodOptLangList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param ProdOptLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/prodOptLang/getProdOptLangList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<ProdOptLangExDto> getProdOptLangList(@Valid final ProdOptLangExDto params) throws Exception {
		final List<ProdOptLangExDto> listData = this.prodOptLangService.getProdOptLangList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param ProdOptLangExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/prodOptLang/getProdOptLang.do")
	@ResponseBody
	public Object getProdOptLang(@Valid final ProdOptLangExDto params) throws Exception {
		final ProdOptLangExDto mapData = this.prodOptLangService.getProdOptLang(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param ProdOptLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodOptLang/insertProdOptLang.do")
	@ResponseBody
	public Map<String,Object> insertProdOptLang(@Valid final ProdOptLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodOptLangService.insertProdOptLang(params);
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
	* @param ProdOptLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodOptLang/updateProdOptLang.do")
	@ResponseBody
	public Map<String,Object> updateProdOptLang(@Valid final ProdOptLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodOptLangService.updateProdOptLang(params);
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
	* @param ProdOptLangExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodOptLang/deleteProdOptLang.do")
	@ResponseBody
	public Map<String,Object> deleteProdOptLang(@Valid final ProdOptLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodOptLangService.deleteProdOptLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/prodOptLang/getProdOptLangExcelDown.do")
	@ResponseBody
	public Object getProdOptLangExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		ProdOptLangExDto prodOptLangExDto = new ProdOptLangExDto();
		prodOptLangExDto = (ProdOptLangExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), prodOptLangExDto);
		final List<ProdOptLangExDto> listData = this.prodOptLangService.getProdOptLangList(prodOptLangExDto);
		
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
