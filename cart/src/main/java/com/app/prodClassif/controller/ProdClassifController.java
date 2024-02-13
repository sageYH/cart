package com.app.prodClassif.controller;

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

import com.app.prodClassif.model.ProdClassifExDto;
import com.app.prodClassif.service.ProdClassifService;
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
public class ProdClassifController {

	@Autowired
	private ProdClassifService prodClassifService;

	@RequestMapping("app/prodClassif/prodClassifList.do")
	public String prodClassifList() throws Exception {
		return "app/prodClassif/prodClassifList";
	}

	@RequestMapping("app/popup/prodClassif/prodClassifPop.do")
	public String prodClassifPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodClassif/prodClassifPop";
	}

	@RequestMapping("app/popup/prodClassif/prodClassifInfoPop.do")
	public String prodClassifInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodClassif/prodClassifInfoPop";
	}

	@RequestMapping("app/popup/prodClassif/prodClassifSearchPop.do")
	public String prodClassifSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodClassif/prodClassifSearchPop";
	}

	/**
	*  List Page
	*
	* @param ProdClassifExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/prodClassif/getProdClassifPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getProdClassifPageList(HttpServletRequest request, @Valid final ProdClassifExDto params) throws Exception {
		final int totalRows = this.prodClassifService.getProdClassifListCount(params);
		final List<ProdClassifExDto> prodClassifList = this.prodClassifService.getProdClassifList(params);
		
		Map<String,Object> mapData = (new ResponseList(prodClassifList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param ProdClassifExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/prodClassif/getProdClassifList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<ProdClassifExDto> getProdClassifList(@Valid final ProdClassifExDto params) throws Exception {
		final List<ProdClassifExDto> listData = this.prodClassifService.getProdClassifList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param ProdClassifExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/prodClassif/getProdClassif.do")
	@ResponseBody
	public Object getProdClassif(@Valid final ProdClassifExDto params) throws Exception {
		final ProdClassifExDto mapData = this.prodClassifService.getProdClassif(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param ProdClassifExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodClassif/insertProdClassif.do")
	@ResponseBody
	public Map<String,Object> insertProdClassif(@Valid final ProdClassifExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodClassifService.insertProdClassif(params);
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
	* @param ProdClassifExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodClassif/updateProdClassif.do")
	@ResponseBody
	public Map<String,Object> updateProdClassif(@Valid final ProdClassifExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodClassifService.updateProdClassif(params);
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
	* @param ProdClassifExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodClassif/deleteProdClassif.do")
	@ResponseBody
	public Map<String,Object> deleteProdClassif(@Valid final ProdClassifExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodClassifService.deleteProdClassif(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/prodClassif/getProdClassifExcelDown.do")
	@ResponseBody
	public Object getProdClassifExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		ProdClassifExDto prodClassifExDto = new ProdClassifExDto();
		prodClassifExDto = (ProdClassifExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), prodClassifExDto);
		final List<ProdClassifExDto> listData = this.prodClassifService.getProdClassifList(prodClassifExDto);
		
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
