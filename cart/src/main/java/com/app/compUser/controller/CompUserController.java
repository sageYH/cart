package com.app.compUser.controller;

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

import com.app.compUser.model.CompUserExDto;
import com.app.compUser.service.CompUserService;
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
public class CompUserController {

	@Autowired
	private CompUserService compUserService;

	@RequestMapping("app/compUser/compUserList.do")
	public String compUserList() throws Exception {
		return "app/compUser/compUserList";
	}

	@RequestMapping("app/popup/compUser/compUserPop.do")
	public String compUserPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/compUser/compUserPop";
	}

	@RequestMapping("app/popup/compUser/compUserInfoPop.do")
	public String compUserInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/compUser/compUserInfoPop";
	}

	@RequestMapping("app/popup/compUser/compUserSearchPop.do")
	public String compUserSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/compUser/compUserSearchPop";
	}

	/**
	*  List Page
	*
	* @param CompUserExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/compUser/getCompUserPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCompUserPageList(HttpServletRequest request, @Valid final CompUserExDto params) throws Exception {
		final int totalRows = this.compUserService.getCompUserListCount(params);
		final List<CompUserExDto> compUserList = this.compUserService.getCompUserList(params);
		
		Map<String,Object> mapData = (new ResponseList(compUserList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param CompUserExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/compUser/getCompUserList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CompUserExDto> getCompUserList(@Valid final CompUserExDto params) throws Exception {
		final List<CompUserExDto> listData = this.compUserService.getCompUserList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param CompUserExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/compUser/getCompUser.do")
	@ResponseBody
	public Object getCompUser(@Valid final CompUserExDto params) throws Exception {
		final CompUserExDto mapData = this.compUserService.getCompUser(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param CompUserExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/compUser/insertCompUser.do")
	@ResponseBody
	public Map<String,Object> insertCompUser(@Valid final CompUserExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.compUserService.insertCompUser(params);
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
	* @param CompUserExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/compUser/updateCompUser.do")
	@ResponseBody
	public Map<String,Object> updateCompUser(@Valid final CompUserExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.compUserService.updateCompUser(params);
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
	* @param CompUserExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/compUser/deleteCompUser.do")
	@ResponseBody
	public Map<String,Object> deleteCompUser(@Valid final CompUserExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.compUserService.deleteCompUser(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/compUser/getCompUserExcelDown.do")
	@ResponseBody
	public Object getCompUserExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CompUserExDto compUserExDto = new CompUserExDto();
		compUserExDto = (CompUserExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), compUserExDto);
		final List<CompUserExDto> listData = this.compUserService.getCompUserList(compUserExDto);
		
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
