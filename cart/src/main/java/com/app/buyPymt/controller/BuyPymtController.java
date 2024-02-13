package com.app.buyPymt.controller;

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

import com.app.buyPymt.model.BuyPymtExDto;
import com.app.buyPymt.service.BuyPymtService;
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
public class BuyPymtController {

	@Autowired
	private BuyPymtService buyPymtService;

	@RequestMapping("app/buyPymt/buyPymtList.do")
	public String buyPymtList() throws Exception {
		return "app/buyPymt/buyPymtList";
	}

	@RequestMapping("app/popup/buyPymt/buyPymtPop.do")
	public String buyPymtPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/buyPymt/buyPymtPop";
	}

	@RequestMapping("app/popup/buyPymt/buyPymtInfoPop.do")
	public String buyPymtInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/buyPymt/buyPymtInfoPop";
	}

	@RequestMapping("app/popup/buyPymt/buyPymtSearchPop.do")
	public String buyPymtSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/buyPymt/buyPymtSearchPop";
	}

	/**
	*  List Page
	*
	* @param BuyPymtExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/buyPymt/getBuyPymtPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getBuyPymtPageList(HttpServletRequest request, @Valid final BuyPymtExDto params) throws Exception {
		final int totalRows = this.buyPymtService.getBuyPymtListCount(params);
		final List<BuyPymtExDto> buyPymtList = this.buyPymtService.getBuyPymtList(params);
		
		Map<String,Object> mapData = (new ResponseList(buyPymtList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param BuyPymtExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/buyPymt/getBuyPymtList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<BuyPymtExDto> getBuyPymtList(@Valid final BuyPymtExDto params) throws Exception {
		final List<BuyPymtExDto> listData = this.buyPymtService.getBuyPymtList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param BuyPymtExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/buyPymt/getBuyPymt.do")
	@ResponseBody
	public Object getBuyPymt(@Valid final BuyPymtExDto params) throws Exception {
		final BuyPymtExDto mapData = this.buyPymtService.getBuyPymt(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param BuyPymtExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/buyPymt/insertBuyPymt.do")
	@ResponseBody
	public Map<String,Object> insertBuyPymt(@Valid final BuyPymtExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.buyPymtService.insertBuyPymt(params);
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
	* @param BuyPymtExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/buyPymt/updateBuyPymt.do")
	@ResponseBody
	public Map<String,Object> updateBuyPymt(@Valid final BuyPymtExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.buyPymtService.updateBuyPymt(params);
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
	* @param BuyPymtExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/buyPymt/deleteBuyPymt.do")
	@ResponseBody
	public Map<String,Object> deleteBuyPymt(@Valid final BuyPymtExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.buyPymtService.deleteBuyPymt(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/buyPymt/getBuyPymtExcelDown.do")
	@ResponseBody
	public Object getBuyPymtExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		BuyPymtExDto buyPymtExDto = new BuyPymtExDto();
		buyPymtExDto = (BuyPymtExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), buyPymtExDto);
		final List<BuyPymtExDto> listData = this.buyPymtService.getBuyPymtList(buyPymtExDto);
		
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
