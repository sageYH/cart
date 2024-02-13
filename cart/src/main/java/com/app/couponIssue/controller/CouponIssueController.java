package com.app.couponIssue.controller;

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

import com.app.couponIssue.model.CouponIssueExDto;
import com.app.couponIssue.service.CouponIssueService;
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
public class CouponIssueController {

	@Autowired
	private CouponIssueService couponIssueService;

	@RequestMapping("app/couponIssue/couponIssueList.do")
	public String couponIssueList() throws Exception {
		return "app/couponIssue/couponIssueList";
	}

	@RequestMapping("app/popup/couponIssue/couponIssuePop.do")
	public String couponIssuePop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/couponIssue/couponIssuePop";
	}

	@RequestMapping("app/popup/couponIssue/couponIssueInfoPop.do")
	public String couponIssueInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/couponIssue/couponIssueInfoPop";
	}

	@RequestMapping("app/popup/couponIssue/couponIssueSearchPop.do")
	public String couponIssueSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/couponIssue/couponIssueSearchPop";
	}

	/**
	*  List Page
	*
	* @param CouponIssueExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/couponIssue/getCouponIssuePageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCouponIssuePageList(HttpServletRequest request, @Valid final CouponIssueExDto params) throws Exception {
		final int totalRows = this.couponIssueService.getCouponIssueListCount(params);
		final List<CouponIssueExDto> couponIssueList = this.couponIssueService.getCouponIssueList(params);
		
		Map<String,Object> mapData = (new ResponseList(couponIssueList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param CouponIssueExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/couponIssue/getCouponIssueList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CouponIssueExDto> getCouponIssueList(@Valid final CouponIssueExDto params) throws Exception {
		final List<CouponIssueExDto> listData = this.couponIssueService.getCouponIssueList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param CouponIssueExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/couponIssue/getCouponIssue.do")
	@ResponseBody
	public Object getCouponIssue(@Valid final CouponIssueExDto params) throws Exception {
		final CouponIssueExDto mapData = this.couponIssueService.getCouponIssue(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param CouponIssueExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/couponIssue/insertCouponIssue.do")
	@ResponseBody
	public Map<String,Object> insertCouponIssue(@Valid final CouponIssueExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.couponIssueService.insertCouponIssue(params);
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
	* @param CouponIssueExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/couponIssue/updateCouponIssue.do")
	@ResponseBody
	public Map<String,Object> updateCouponIssue(@Valid final CouponIssueExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.couponIssueService.updateCouponIssue(params);
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
	* @param CouponIssueExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/couponIssue/deleteCouponIssue.do")
	@ResponseBody
	public Map<String,Object> deleteCouponIssue(@Valid final CouponIssueExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.couponIssueService.deleteCouponIssue(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/couponIssue/getCouponIssueExcelDown.do")
	@ResponseBody
	public Object getCouponIssueExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CouponIssueExDto couponIssueExDto = new CouponIssueExDto();
		couponIssueExDto = (CouponIssueExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), couponIssueExDto);
		final List<CouponIssueExDto> listData = this.couponIssueService.getCouponIssueList(couponIssueExDto);
		
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
