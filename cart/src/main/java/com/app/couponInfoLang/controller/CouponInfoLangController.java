package com.app.couponInfoLang.controller;

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

import com.app.couponInfoLang.model.CouponInfoLangExDto;
import com.app.couponInfoLang.service.CouponInfoLangService;
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
public class CouponInfoLangController {

	@Autowired
	private CouponInfoLangService couponInfoLangService;

	@RequestMapping("app/couponInfoLang/couponInfoLangList.do")
	public String couponInfoLangList() throws Exception {
		return "app/couponInfoLang/couponInfoLangList";
	}

	@RequestMapping("app/popup/couponInfoLang/couponInfoLangPop.do")
	public String couponInfoLangPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/couponInfoLang/couponInfoLangPop";
	}

	@RequestMapping("app/popup/couponInfoLang/couponInfoLangInfoPop.do")
	public String couponInfoLangInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/couponInfoLang/couponInfoLangInfoPop";
	}

	@RequestMapping("app/popup/couponInfoLang/couponInfoLangSearchPop.do")
	public String couponInfoLangSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/couponInfoLang/couponInfoLangSearchPop";
	}

	/**
	*  List Page
	*
	* @param CouponInfoLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/couponInfoLang/getCouponInfoLangPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCouponInfoLangPageList(HttpServletRequest request, @Valid final CouponInfoLangExDto params) throws Exception {
		final int totalRows = this.couponInfoLangService.getCouponInfoLangListCount(params);
		final List<CouponInfoLangExDto> couponInfoLangList = this.couponInfoLangService.getCouponInfoLangList(params);
		
		Map<String,Object> mapData = (new ResponseList(couponInfoLangList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param CouponInfoLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/couponInfoLang/getCouponInfoLangList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CouponInfoLangExDto> getCouponInfoLangList(@Valid final CouponInfoLangExDto params) throws Exception {
		final List<CouponInfoLangExDto> listData = this.couponInfoLangService.getCouponInfoLangList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param CouponInfoLangExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/couponInfoLang/getCouponInfoLang.do")
	@ResponseBody
	public Object getCouponInfoLang(@Valid final CouponInfoLangExDto params) throws Exception {
		final CouponInfoLangExDto mapData = this.couponInfoLangService.getCouponInfoLang(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param CouponInfoLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/couponInfoLang/insertCouponInfoLang.do")
	@ResponseBody
	public Map<String,Object> insertCouponInfoLang(@Valid final CouponInfoLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.couponInfoLangService.insertCouponInfoLang(params);
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
	* @param CouponInfoLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/couponInfoLang/updateCouponInfoLang.do")
	@ResponseBody
	public Map<String,Object> updateCouponInfoLang(@Valid final CouponInfoLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.couponInfoLangService.updateCouponInfoLang(params);
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
	* @param CouponInfoLangExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/couponInfoLang/deleteCouponInfoLang.do")
	@ResponseBody
	public Map<String,Object> deleteCouponInfoLang(@Valid final CouponInfoLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.couponInfoLangService.deleteCouponInfoLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/couponInfoLang/getCouponInfoLangExcelDown.do")
	@ResponseBody
	public Object getCouponInfoLangExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CouponInfoLangExDto couponInfoLangExDto = new CouponInfoLangExDto();
		couponInfoLangExDto = (CouponInfoLangExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), couponInfoLangExDto);
		final List<CouponInfoLangExDto> listData = this.couponInfoLangService.getCouponInfoLangList(couponInfoLangExDto);
		
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
