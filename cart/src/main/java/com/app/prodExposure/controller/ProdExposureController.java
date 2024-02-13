package com.app.prodExposure.controller;

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

import com.app.prodExposure.model.ProdExposureExDto;
import com.app.prodExposure.service.ProdExposureService;
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
public class ProdExposureController {

	@Autowired
	private ProdExposureService prodExposureService;

	@RequestMapping("app/prodExposure/prodExposureList.do")
	public String prodExposureList() throws Exception {
		return "app/prodExposure/prodExposureList";
	}

	@RequestMapping("app/popup/prodExposure/prodExposurePop.do")
	public String prodExposurePop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodExposure/prodExposurePop";
	}

	@RequestMapping("app/popup/prodExposure/prodExposureInfoPop.do")
	public String prodExposureInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodExposure/prodExposureInfoPop";
	}

	@RequestMapping("app/popup/prodExposure/prodExposureSearchPop.do")
	public String prodExposureSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodExposure/prodExposureSearchPop";
	}

	/**
	*  List Page
	*
	* @param ProdExposureExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/prodExposure/getProdExposurePageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getProdExposurePageList(HttpServletRequest request, @Valid final ProdExposureExDto params) throws Exception {
		final int totalRows = this.prodExposureService.getProdExposureListCount(params);
		final List<ProdExposureExDto> prodExposureList = this.prodExposureService.getProdExposureList(params);
		
		Map<String,Object> mapData = (new ResponseList(prodExposureList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param ProdExposureExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/prodExposure/getProdExposureList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<ProdExposureExDto> getProdExposureList(@Valid final ProdExposureExDto params) throws Exception {
		final List<ProdExposureExDto> listData = this.prodExposureService.getProdExposureList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param ProdExposureExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/prodExposure/getProdExposure.do")
	@ResponseBody
	public Object getProdExposure(@Valid final ProdExposureExDto params) throws Exception {
		final ProdExposureExDto mapData = this.prodExposureService.getProdExposure(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param ProdExposureExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodExposure/insertProdExposure.do")
	@ResponseBody
	public Map<String,Object> insertProdExposure(@Valid final ProdExposureExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodExposureService.insertProdExposure(params);
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
	* @param ProdExposureExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodExposure/updateProdExposure.do")
	@ResponseBody
	public Map<String,Object> updateProdExposure(@Valid final ProdExposureExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodExposureService.updateProdExposure(params);
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
	* @param ProdExposureExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodExposure/deleteProdExposure.do")
	@ResponseBody
	public Map<String,Object> deleteProdExposure(@Valid final ProdExposureExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodExposureService.deleteProdExposure(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/prodExposure/getProdExposureExcelDown.do")
	@ResponseBody
	public Object getProdExposureExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		ProdExposureExDto prodExposureExDto = new ProdExposureExDto();
		prodExposureExDto = (ProdExposureExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), prodExposureExDto);
		final List<ProdExposureExDto> listData = this.prodExposureService.getProdExposureList(prodExposureExDto);
		
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
