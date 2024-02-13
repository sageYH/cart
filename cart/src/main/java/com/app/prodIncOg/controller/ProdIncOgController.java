package com.app.prodIncOg.controller;

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

import com.app.prodIncOg.model.ProdIncOgExDto;
import com.app.prodIncOg.service.ProdIncOgService;
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
public class ProdIncOgController {

	@Autowired
	private ProdIncOgService prodIncOgService;

	@RequestMapping("app/prodIncOg/prodIncOgList.do")
	public String prodIncOgList() throws Exception {
		return "app/prodIncOg/prodIncOgList";
	}

	@RequestMapping("app/popup/prodIncOg/prodIncOgPop.do")
	public String prodIncOgPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodIncOg/prodIncOgPop";
	}

	@RequestMapping("app/popup/prodIncOg/prodIncOgInfoPop.do")
	public String prodIncOgInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodIncOg/prodIncOgInfoPop";
	}

	@RequestMapping("app/popup/prodIncOg/prodIncOgSearchPop.do")
	public String prodIncOgSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodIncOg/prodIncOgSearchPop";
	}

	/**
	*  List Page
	*
	* @param ProdIncOgExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/prodIncOg/getProdIncOgPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getProdIncOgPageList(HttpServletRequest request, @Valid final ProdIncOgExDto params) throws Exception {
		final int totalRows = this.prodIncOgService.getProdIncOgListCount(params);
		final List<ProdIncOgExDto> prodIncOgList = this.prodIncOgService.getProdIncOgList(params);
		
		Map<String,Object> mapData = (new ResponseList(prodIncOgList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param ProdIncOgExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/prodIncOg/getProdIncOgList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<ProdIncOgExDto> getProdIncOgList(@Valid final ProdIncOgExDto params) throws Exception {
		final List<ProdIncOgExDto> listData = this.prodIncOgService.getProdIncOgList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param ProdIncOgExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/prodIncOg/getProdIncOg.do")
	@ResponseBody
	public Object getProdIncOg(@Valid final ProdIncOgExDto params) throws Exception {
		final ProdIncOgExDto mapData = this.prodIncOgService.getProdIncOg(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param ProdIncOgExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodIncOg/insertProdIncOg.do")
	@ResponseBody
	public Map<String,Object> insertProdIncOg(@Valid final ProdIncOgExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodIncOgService.insertProdIncOg(params);
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
	* @param ProdIncOgExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodIncOg/updateProdIncOg.do")
	@ResponseBody
	public Map<String,Object> updateProdIncOg(@Valid final ProdIncOgExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodIncOgService.updateProdIncOg(params);
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
	* @param ProdIncOgExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodIncOg/deleteProdIncOg.do")
	@ResponseBody
	public Map<String,Object> deleteProdIncOg(@Valid final ProdIncOgExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodIncOgService.deleteProdIncOg(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/prodIncOg/getProdIncOgExcelDown.do")
	@ResponseBody
	public Object getProdIncOgExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		ProdIncOgExDto prodIncOgExDto = new ProdIncOgExDto();
		prodIncOgExDto = (ProdIncOgExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), prodIncOgExDto);
		final List<ProdIncOgExDto> listData = this.prodIncOgService.getProdIncOgList(prodIncOgExDto);
		
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
