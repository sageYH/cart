package com.app.pointIssue.controller;

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

import com.app.pointIssue.model.PointIssueExDto;
import com.app.pointIssue.service.PointIssueService;
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
public class PointIssueController {

	@Autowired
	private PointIssueService pointIssueService;

	@RequestMapping("app/pointIssue/pointIssueList.do")
	public String pointIssueList() throws Exception {
		return "app/pointIssue/pointIssueList";
	}

	@RequestMapping("app/popup/pointIssue/pointIssuePop.do")
	public String pointIssuePop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/pointIssue/pointIssuePop";
	}

	@RequestMapping("app/popup/pointIssue/pointIssueInfoPop.do")
	public String pointIssueInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/pointIssue/pointIssueInfoPop";
	}

	@RequestMapping("app/popup/pointIssue/pointIssueSearchPop.do")
	public String pointIssueSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/pointIssue/pointIssueSearchPop";
	}

	/**
	*  List Page
	*
	* @param PointIssueExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/pointIssue/getPointIssuePageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getPointIssuePageList(HttpServletRequest request, @Valid final PointIssueExDto params) throws Exception {
		final int totalRows = this.pointIssueService.getPointIssueListCount(params);
		final List<PointIssueExDto> pointIssueList = this.pointIssueService.getPointIssueList(params);
		
		Map<String,Object> mapData = (new ResponseList(pointIssueList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param PointIssueExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/pointIssue/getPointIssueList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<PointIssueExDto> getPointIssueList(@Valid final PointIssueExDto params) throws Exception {
		final List<PointIssueExDto> listData = this.pointIssueService.getPointIssueList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param PointIssueExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/pointIssue/getPointIssue.do")
	@ResponseBody
	public Object getPointIssue(@Valid final PointIssueExDto params) throws Exception {
		final PointIssueExDto mapData = this.pointIssueService.getPointIssue(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param PointIssueExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/pointIssue/insertPointIssue.do")
	@ResponseBody
	public Map<String,Object> insertPointIssue(@Valid final PointIssueExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.pointIssueService.insertPointIssue(params);
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
	* @param PointIssueExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/pointIssue/updatePointIssue.do")
	@ResponseBody
	public Map<String,Object> updatePointIssue(@Valid final PointIssueExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.pointIssueService.updatePointIssue(params);
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
	* @param PointIssueExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/pointIssue/deletePointIssue.do")
	@ResponseBody
	public Map<String,Object> deletePointIssue(@Valid final PointIssueExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.pointIssueService.deletePointIssue(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/pointIssue/getPointIssueExcelDown.do")
	@ResponseBody
	public Object getPointIssueExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		PointIssueExDto pointIssueExDto = new PointIssueExDto();
		pointIssueExDto = (PointIssueExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), pointIssueExDto);
		final List<PointIssueExDto> listData = this.pointIssueService.getPointIssueList(pointIssueExDto);
		
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
