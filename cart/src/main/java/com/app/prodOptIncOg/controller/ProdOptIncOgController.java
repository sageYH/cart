package com.app.prodOptIncOg.controller;

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

import com.app.prodOptIncOg.model.ProdOptIncOgExDto;
import com.app.prodOptIncOg.service.ProdOptIncOgService;
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
public class ProdOptIncOgController {

	@Autowired
	private ProdOptIncOgService prodOptIncOgService;

	@RequestMapping("app/prodOptIncOg/prodOptIncOgList.do")
	public String prodOptIncOgList() throws Exception {
		return "app/prodOptIncOg/prodOptIncOgList";
	}

	@RequestMapping("app/popup/prodOptIncOg/prodOptIncOgPop.do")
	public String prodOptIncOgPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodOptIncOg/prodOptIncOgPop";
	}

	@RequestMapping("app/popup/prodOptIncOg/prodOptIncOgInfoPop.do")
	public String prodOptIncOgInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodOptIncOg/prodOptIncOgInfoPop";
	}

	@RequestMapping("app/popup/prodOptIncOg/prodOptIncOgSearchPop.do")
	public String prodOptIncOgSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodOptIncOg/prodOptIncOgSearchPop";
	}

	/**
	*  List Page
	*
	* @param ProdOptIncOgExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/prodOptIncOg/getProdOptIncOgPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getProdOptIncOgPageList(HttpServletRequest request, @Valid final ProdOptIncOgExDto params) throws Exception {
		final int totalRows = this.prodOptIncOgService.getProdOptIncOgListCount(params);
		final List<ProdOptIncOgExDto> prodOptIncOgList = this.prodOptIncOgService.getProdOptIncOgList(params);
		
		Map<String,Object> mapData = (new ResponseList(prodOptIncOgList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param ProdOptIncOgExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/prodOptIncOg/getProdOptIncOgList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<ProdOptIncOgExDto> getProdOptIncOgList(@Valid final ProdOptIncOgExDto params) throws Exception {
		final List<ProdOptIncOgExDto> listData = this.prodOptIncOgService.getProdOptIncOgList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param ProdOptIncOgExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/prodOptIncOg/getProdOptIncOg.do")
	@ResponseBody
	public Object getProdOptIncOg(@Valid final ProdOptIncOgExDto params) throws Exception {
		final ProdOptIncOgExDto mapData = this.prodOptIncOgService.getProdOptIncOg(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param ProdOptIncOgExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodOptIncOg/insertProdOptIncOg.do")
	@ResponseBody
	public Map<String,Object> insertProdOptIncOg(@Valid final ProdOptIncOgExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodOptIncOgService.insertProdOptIncOg(params);
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
	* @param ProdOptIncOgExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodOptIncOg/updateProdOptIncOg.do")
	@ResponseBody
	public Map<String,Object> updateProdOptIncOg(@Valid final ProdOptIncOgExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodOptIncOgService.updateProdOptIncOg(params);
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
	* @param ProdOptIncOgExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodOptIncOg/deleteProdOptIncOg.do")
	@ResponseBody
	public Map<String,Object> deleteProdOptIncOg(@Valid final ProdOptIncOgExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodOptIncOgService.deleteProdOptIncOg(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/prodOptIncOg/getProdOptIncOgExcelDown.do")
	@ResponseBody
	public Object getProdOptIncOgExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		ProdOptIncOgExDto prodOptIncOgExDto = new ProdOptIncOgExDto();
		prodOptIncOgExDto = (ProdOptIncOgExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), prodOptIncOgExDto);
		final List<ProdOptIncOgExDto> listData = this.prodOptIncOgService.getProdOptIncOgList(prodOptIncOgExDto);
		
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
