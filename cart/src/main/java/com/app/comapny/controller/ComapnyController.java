package com.app.comapny.controller;

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

import com.app.comapny.model.ComapnyExDto;
import com.app.comapny.service.ComapnyService;
import com.base.ResponseList;
import com.base.ResponseMap;
import com.common.utils.ClassUtil;
import com.common.utils.Excel;
import com.common.utils.MapUtil;

import lombok.RequiredArgsConstructor;

/**
* 업체정보 관리
*
* @author
*
*/

@Controller
@Lazy
@RequiredArgsConstructor
public class ComapnyController {

	@Autowired
	private ComapnyService comapnyService;

	@RequestMapping("app/comapny/comapnyList.do")
	public String comapnyList() throws Exception {
		return "app/comapny/comapnyList";
	}

	@RequestMapping("app/popup/comapny/comapnyPop.do")
	public String comapnyPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/comapny/comapnyPop";
	}

	@RequestMapping("app/popup/comapny/comapnyInfoPop.do")
	public String comapnyInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/comapny/comapnyInfoPop";
	}

	@RequestMapping("app/popup/comapny/comapnySearchPop.do")
	public String comapnySearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/comapny/comapnySearchPop";
	}

	/**
	* 업체정보 List Page
	*
	* @param ComapnyExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/comapny/getComapnyPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getComapnyPageList(HttpServletRequest request, @Valid final ComapnyExDto params) throws Exception {
		final int totalRows = this.comapnyService.getComapnyListCount(params);
		final List<ComapnyExDto> comapnyList = this.comapnyService.getComapnyList(params);
		
		Map<String,Object> mapData = (new ResponseList(comapnyList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	* 업체정보 List Inquiry
	*
	* @param ComapnyExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/comapny/getComapnyList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<ComapnyExDto> getComapnyList(@Valid final ComapnyExDto params) throws Exception {
		final List<ComapnyExDto> listData = this.comapnyService.getComapnyList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요 업체정보 detail infomation Inquiry
	* </pre>
	*
	* @param ComapnyExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/comapny/getComapny.do")
	@ResponseBody
	public Object getComapny(@Valid final ComapnyExDto params) throws Exception {
		final ComapnyExDto mapData = this.comapnyService.getComapny(params);
		return mapData;
	}

	/**
	* 업체정보 insert
	*
	* @param ComapnyExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/comapny/insertComapny.do")
	@ResponseBody
	public Map<String,Object> insertComapny(@Valid final ComapnyExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.comapnyService.insertComapny(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 업체정보 Update
	*
	* @param ComapnyExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/comapny/updateComapny.do")
	@ResponseBody
	public Map<String,Object> updateComapny(@Valid final ComapnyExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.comapnyService.updateComapny(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 업체정보 delete
	*
	* @param ComapnyExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/comapny/deleteComapny.do")
	@ResponseBody
	public Map<String,Object> deleteComapny(@Valid final ComapnyExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.comapnyService.deleteComapny(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/comapny/getComapnyExcelDown.do")
	@ResponseBody
	public Object getComapnyExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		ComapnyExDto comapnyExDto = new ComapnyExDto();
		comapnyExDto = (ComapnyExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), comapnyExDto);
		final List<ComapnyExDto> listData = this.comapnyService.getComapnyList(comapnyExDto);
		
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
