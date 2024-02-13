package com.app.couponIssueMbr.controller;

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

import com.app.couponIssueMbr.model.CouponIssueMbrExDto;
import com.app.couponIssueMbr.service.CouponIssueMbrService;
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
public class CouponIssueMbrController {

	@Autowired
	private CouponIssueMbrService couponIssueMbrService;

	@RequestMapping("app/couponIssueMbr/couponIssueMbrList.do")
	public String couponIssueMbrList() throws Exception {
		return "app/couponIssueMbr/couponIssueMbrList";
	}

	@RequestMapping("app/popup/couponIssueMbr/couponIssueMbrPop.do")
	public String couponIssueMbrPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/couponIssueMbr/couponIssueMbrPop";
	}

	@RequestMapping("app/popup/couponIssueMbr/couponIssueMbrInfoPop.do")
	public String couponIssueMbrInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/couponIssueMbr/couponIssueMbrInfoPop";
	}

	@RequestMapping("app/popup/couponIssueMbr/couponIssueMbrSearchPop.do")
	public String couponIssueMbrSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/couponIssueMbr/couponIssueMbrSearchPop";
	}

	/**
	*  List Page
	*
	* @param CouponIssueMbrExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/couponIssueMbr/getCouponIssueMbrPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCouponIssueMbrPageList(HttpServletRequest request, @Valid final CouponIssueMbrExDto params) throws Exception {
		final int totalRows = this.couponIssueMbrService.getCouponIssueMbrListCount(params);
		final List<CouponIssueMbrExDto> couponIssueMbrList = this.couponIssueMbrService.getCouponIssueMbrList(params);
		
		Map<String,Object> mapData = (new ResponseList(couponIssueMbrList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param CouponIssueMbrExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/couponIssueMbr/getCouponIssueMbrList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CouponIssueMbrExDto> getCouponIssueMbrList(@Valid final CouponIssueMbrExDto params) throws Exception {
		final List<CouponIssueMbrExDto> listData = this.couponIssueMbrService.getCouponIssueMbrList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param CouponIssueMbrExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/couponIssueMbr/getCouponIssueMbr.do")
	@ResponseBody
	public Object getCouponIssueMbr(@Valid final CouponIssueMbrExDto params) throws Exception {
		final CouponIssueMbrExDto mapData = this.couponIssueMbrService.getCouponIssueMbr(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param CouponIssueMbrExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/couponIssueMbr/insertCouponIssueMbr.do")
	@ResponseBody
	public Map<String,Object> insertCouponIssueMbr(@Valid final CouponIssueMbrExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.couponIssueMbrService.insertCouponIssueMbr(params);
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
	* @param CouponIssueMbrExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/couponIssueMbr/updateCouponIssueMbr.do")
	@ResponseBody
	public Map<String,Object> updateCouponIssueMbr(@Valid final CouponIssueMbrExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.couponIssueMbrService.updateCouponIssueMbr(params);
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
	* @param CouponIssueMbrExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/couponIssueMbr/deleteCouponIssueMbr.do")
	@ResponseBody
	public Map<String,Object> deleteCouponIssueMbr(@Valid final CouponIssueMbrExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.couponIssueMbrService.deleteCouponIssueMbr(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/couponIssueMbr/getCouponIssueMbrExcelDown.do")
	@ResponseBody
	public Object getCouponIssueMbrExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CouponIssueMbrExDto couponIssueMbrExDto = new CouponIssueMbrExDto();
		couponIssueMbrExDto = (CouponIssueMbrExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), couponIssueMbrExDto);
		final List<CouponIssueMbrExDto> listData = this.couponIssueMbrService.getCouponIssueMbrList(couponIssueMbrExDto);
		
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
