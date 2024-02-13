package com.app.prodOpt.controller;

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

import com.app.prodOpt.model.ProdOptExDto;
import com.app.prodOpt.service.ProdOptService;
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
public class ProdOptController {

	@Autowired
	private ProdOptService prodOptService;

	@RequestMapping("app/prodOpt/prodOptList.do")
	public String prodOptList() throws Exception {
		return "app/prodOpt/prodOptList";
	}

	@RequestMapping("app/popup/prodOpt/prodOptPop.do")
	public String prodOptPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodOpt/prodOptPop";
	}

	@RequestMapping("app/popup/prodOpt/prodOptInfoPop.do")
	public String prodOptInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodOpt/prodOptInfoPop";
	}

	@RequestMapping("app/popup/prodOpt/prodOptSearchPop.do")
	public String prodOptSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodOpt/prodOptSearchPop";
	}

	/**
	*  List Page
	*
	* @param ProdOptExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/prodOpt/getProdOptPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getProdOptPageList(HttpServletRequest request, @Valid final ProdOptExDto params) throws Exception {
		final int totalRows = this.prodOptService.getProdOptListCount(params);
		final List<ProdOptExDto> prodOptList = this.prodOptService.getProdOptList(params);
		
		Map<String,Object> mapData = (new ResponseList(prodOptList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param ProdOptExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/prodOpt/getProdOptList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<ProdOptExDto> getProdOptList(@Valid final ProdOptExDto params) throws Exception {
		final List<ProdOptExDto> listData = this.prodOptService.getProdOptList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param ProdOptExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/prodOpt/getProdOpt.do")
	@ResponseBody
	public Object getProdOpt(@Valid final ProdOptExDto params) throws Exception {
		final ProdOptExDto mapData = this.prodOptService.getProdOpt(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param ProdOptExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodOpt/insertProdOpt.do")
	@ResponseBody
	public Map<String,Object> insertProdOpt(@Valid final ProdOptExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodOptService.insertProdOpt(params);
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
	* @param ProdOptExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodOpt/updateProdOpt.do")
	@ResponseBody
	public Map<String,Object> updateProdOpt(@Valid final ProdOptExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodOptService.updateProdOpt(params);
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
	* @param ProdOptExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodOpt/deleteProdOpt.do")
	@ResponseBody
	public Map<String,Object> deleteProdOpt(@Valid final ProdOptExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodOptService.deleteProdOpt(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/prodOpt/getProdOptExcelDown.do")
	@ResponseBody
	public Object getProdOptExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		ProdOptExDto prodOptExDto = new ProdOptExDto();
		prodOptExDto = (ProdOptExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), prodOptExDto);
		final List<ProdOptExDto> listData = this.prodOptService.getProdOptList(prodOptExDto);
		
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
