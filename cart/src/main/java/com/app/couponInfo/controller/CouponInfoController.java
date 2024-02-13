package com.app.couponInfo.controller;

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

import com.app.couponInfo.model.CouponInfoExDto;
import com.app.couponInfo.service.CouponInfoService;
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
public class CouponInfoController {

	@Autowired
	private CouponInfoService couponInfoService;

	@RequestMapping("app/couponInfo/couponInfoList.do")
	public String couponInfoList() throws Exception {
		return "app/couponInfo/couponInfoList";
	}

	@RequestMapping("app/popup/couponInfo/couponInfoPop.do")
	public String couponInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/couponInfo/couponInfoPop";
	}

	@RequestMapping("app/popup/couponInfo/couponInfoInfoPop.do")
	public String couponInfoInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/couponInfo/couponInfoInfoPop";
	}

	@RequestMapping("app/popup/couponInfo/couponInfoSearchPop.do")
	public String couponInfoSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/couponInfo/couponInfoSearchPop";
	}

	/**
	*  List Page
	*
	* @param CouponInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/couponInfo/getCouponInfoPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCouponInfoPageList(HttpServletRequest request, @Valid final CouponInfoExDto params) throws Exception {
		final int totalRows = this.couponInfoService.getCouponInfoListCount(params);
		final List<CouponInfoExDto> couponInfoList = this.couponInfoService.getCouponInfoList(params);
		
		Map<String,Object> mapData = (new ResponseList(couponInfoList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param CouponInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/couponInfo/getCouponInfoList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CouponInfoExDto> getCouponInfoList(@Valid final CouponInfoExDto params) throws Exception {
		final List<CouponInfoExDto> listData = this.couponInfoService.getCouponInfoList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param CouponInfoExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/couponInfo/getCouponInfo.do")
	@ResponseBody
	public Object getCouponInfo(@Valid final CouponInfoExDto params) throws Exception {
		final CouponInfoExDto mapData = this.couponInfoService.getCouponInfo(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param CouponInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/couponInfo/insertCouponInfo.do")
	@ResponseBody
	public Map<String,Object> insertCouponInfo(@Valid final CouponInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.couponInfoService.insertCouponInfo(params);
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
	* @param CouponInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/couponInfo/updateCouponInfo.do")
	@ResponseBody
	public Map<String,Object> updateCouponInfo(@Valid final CouponInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.couponInfoService.updateCouponInfo(params);
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
	* @param CouponInfoExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/couponInfo/deleteCouponInfo.do")
	@ResponseBody
	public Map<String,Object> deleteCouponInfo(@Valid final CouponInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.couponInfoService.deleteCouponInfo(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/couponInfo/getCouponInfoExcelDown.do")
	@ResponseBody
	public Object getCouponInfoExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CouponInfoExDto couponInfoExDto = new CouponInfoExDto();
		couponInfoExDto = (CouponInfoExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), couponInfoExDto);
		final List<CouponInfoExDto> listData = this.couponInfoService.getCouponInfoList(couponInfoExDto);
		
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
