package com.app.couponPymt.controller;

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

import com.app.couponPymt.model.CouponPymtExDto;
import com.app.couponPymt.service.CouponPymtService;
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
public class CouponPymtController {

	@Autowired
	private CouponPymtService couponPymtService;

	@RequestMapping("app/couponPymt/couponPymtList.do")
	public String couponPymtList() throws Exception {
		return "app/couponPymt/couponPymtList";
	}

	@RequestMapping("app/popup/couponPymt/couponPymtPop.do")
	public String couponPymtPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/couponPymt/couponPymtPop";
	}

	@RequestMapping("app/popup/couponPymt/couponPymtInfoPop.do")
	public String couponPymtInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/couponPymt/couponPymtInfoPop";
	}

	@RequestMapping("app/popup/couponPymt/couponPymtSearchPop.do")
	public String couponPymtSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/couponPymt/couponPymtSearchPop";
	}

	/**
	*  List Page
	*
	* @param CouponPymtExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/couponPymt/getCouponPymtPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCouponPymtPageList(HttpServletRequest request, @Valid final CouponPymtExDto params) throws Exception {
		final int totalRows = this.couponPymtService.getCouponPymtListCount(params);
		final List<CouponPymtExDto> couponPymtList = this.couponPymtService.getCouponPymtList(params);
		
		Map<String,Object> mapData = (new ResponseList(couponPymtList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param CouponPymtExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/couponPymt/getCouponPymtList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CouponPymtExDto> getCouponPymtList(@Valid final CouponPymtExDto params) throws Exception {
		final List<CouponPymtExDto> listData = this.couponPymtService.getCouponPymtList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param CouponPymtExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/couponPymt/getCouponPymt.do")
	@ResponseBody
	public Object getCouponPymt(@Valid final CouponPymtExDto params) throws Exception {
		final CouponPymtExDto mapData = this.couponPymtService.getCouponPymt(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param CouponPymtExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/couponPymt/insertCouponPymt.do")
	@ResponseBody
	public Map<String,Object> insertCouponPymt(@Valid final CouponPymtExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.couponPymtService.insertCouponPymt(params);
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
	* @param CouponPymtExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/couponPymt/updateCouponPymt.do")
	@ResponseBody
	public Map<String,Object> updateCouponPymt(@Valid final CouponPymtExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.couponPymtService.updateCouponPymt(params);
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
	* @param CouponPymtExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/couponPymt/deleteCouponPymt.do")
	@ResponseBody
	public Map<String,Object> deleteCouponPymt(@Valid final CouponPymtExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.couponPymtService.deleteCouponPymt(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/couponPymt/getCouponPymtExcelDown.do")
	@ResponseBody
	public Object getCouponPymtExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CouponPymtExDto couponPymtExDto = new CouponPymtExDto();
		couponPymtExDto = (CouponPymtExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), couponPymtExDto);
		final List<CouponPymtExDto> listData = this.couponPymtService.getCouponPymtList(couponPymtExDto);
		
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
