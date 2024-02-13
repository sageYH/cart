package com.app.pointPaid.controller;

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

import com.app.pointPaid.model.PointPaidExDto;
import com.app.pointPaid.service.PointPaidService;
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
public class PointPaidController {

	@Autowired
	private PointPaidService pointPaidService;

	@RequestMapping("app/pointPaid/pointPaidList.do")
	public String pointPaidList() throws Exception {
		return "app/pointPaid/pointPaidList";
	}

	@RequestMapping("app/popup/pointPaid/pointPaidPop.do")
	public String pointPaidPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/pointPaid/pointPaidPop";
	}

	@RequestMapping("app/popup/pointPaid/pointPaidInfoPop.do")
	public String pointPaidInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/pointPaid/pointPaidInfoPop";
	}

	@RequestMapping("app/popup/pointPaid/pointPaidSearchPop.do")
	public String pointPaidSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/pointPaid/pointPaidSearchPop";
	}

	/**
	*  List Page
	*
	* @param PointPaidExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/pointPaid/getPointPaidPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getPointPaidPageList(HttpServletRequest request, @Valid final PointPaidExDto params) throws Exception {
		final int totalRows = this.pointPaidService.getPointPaidListCount(params);
		final List<PointPaidExDto> pointPaidList = this.pointPaidService.getPointPaidList(params);
		
		Map<String,Object> mapData = (new ResponseList(pointPaidList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param PointPaidExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/pointPaid/getPointPaidList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<PointPaidExDto> getPointPaidList(@Valid final PointPaidExDto params) throws Exception {
		final List<PointPaidExDto> listData = this.pointPaidService.getPointPaidList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param PointPaidExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/pointPaid/getPointPaid.do")
	@ResponseBody
	public Object getPointPaid(@Valid final PointPaidExDto params) throws Exception {
		final PointPaidExDto mapData = this.pointPaidService.getPointPaid(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param PointPaidExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/pointPaid/insertPointPaid.do")
	@ResponseBody
	public Map<String,Object> insertPointPaid(@Valid final PointPaidExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.pointPaidService.insertPointPaid(params);
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
	* @param PointPaidExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/pointPaid/updatePointPaid.do")
	@ResponseBody
	public Map<String,Object> updatePointPaid(@Valid final PointPaidExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.pointPaidService.updatePointPaid(params);
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
	* @param PointPaidExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/pointPaid/deletePointPaid.do")
	@ResponseBody
	public Map<String,Object> deletePointPaid(@Valid final PointPaidExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.pointPaidService.deletePointPaid(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/pointPaid/getPointPaidExcelDown.do")
	@ResponseBody
	public Object getPointPaidExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		PointPaidExDto pointPaidExDto = new PointPaidExDto();
		pointPaidExDto = (PointPaidExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), pointPaidExDto);
		final List<PointPaidExDto> listData = this.pointPaidService.getPointPaidList(pointPaidExDto);
		
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
