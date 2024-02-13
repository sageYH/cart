package com.app.compProd.controller;

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

import com.app.compProd.model.CompProdExDto;
import com.app.compProd.service.CompProdService;
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
public class CompProdController {

	@Autowired
	private CompProdService compProdService;

	@RequestMapping("app/compProd/compProdList.do")
	public String compProdList() throws Exception {
		return "app/compProd/compProdList";
	}

	@RequestMapping("app/popup/compProd/compProdPop.do")
	public String compProdPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/compProd/compProdPop";
	}

	@RequestMapping("app/popup/compProd/compProdInfoPop.do")
	public String compProdInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/compProd/compProdInfoPop";
	}

	@RequestMapping("app/popup/compProd/compProdSearchPop.do")
	public String compProdSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/compProd/compProdSearchPop";
	}

	/**
	*  List Page
	*
	* @param CompProdExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/compProd/getCompProdPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCompProdPageList(HttpServletRequest request, @Valid final CompProdExDto params) throws Exception {
		final int totalRows = this.compProdService.getCompProdListCount(params);
		final List<CompProdExDto> compProdList = this.compProdService.getCompProdList(params);
		
		Map<String,Object> mapData = (new ResponseList(compProdList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param CompProdExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/compProd/getCompProdList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CompProdExDto> getCompProdList(@Valid final CompProdExDto params) throws Exception {
		final List<CompProdExDto> listData = this.compProdService.getCompProdList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param CompProdExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/compProd/getCompProd.do")
	@ResponseBody
	public Object getCompProd(@Valid final CompProdExDto params) throws Exception {
		final CompProdExDto mapData = this.compProdService.getCompProd(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param CompProdExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/compProd/insertCompProd.do")
	@ResponseBody
	public Map<String,Object> insertCompProd(@Valid final CompProdExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.compProdService.insertCompProd(params);
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
	* @param CompProdExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/compProd/updateCompProd.do")
	@ResponseBody
	public Map<String,Object> updateCompProd(@Valid final CompProdExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.compProdService.updateCompProd(params);
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
	* @param CompProdExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/compProd/deleteCompProd.do")
	@ResponseBody
	public Map<String,Object> deleteCompProd(@Valid final CompProdExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.compProdService.deleteCompProd(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/compProd/getCompProdExcelDown.do")
	@ResponseBody
	public Object getCompProdExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CompProdExDto compProdExDto = new CompProdExDto();
		compProdExDto = (CompProdExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), compProdExDto);
		final List<CompProdExDto> listData = this.compProdService.getCompProdList(compProdExDto);
		
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
