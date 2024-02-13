package com.app.userCert.controller;

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

import com.app.userCert.model.UserCertExDto;
import com.app.userCert.service.UserCertService;
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
public class UserCertController {

	@Autowired
	private UserCertService userCertService;

	@RequestMapping("app/userCert/userCertList.do")
	public String userCertList() throws Exception {
		return "app/userCert/userCertList";
	}

	@RequestMapping("app/popup/userCert/userCertPop.do")
	public String userCertPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/userCert/userCertPop";
	}

	@RequestMapping("app/popup/userCert/userCertInfoPop.do")
	public String userCertInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/userCert/userCertInfoPop";
	}

	@RequestMapping("app/popup/userCert/userCertSearchPop.do")
	public String userCertSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/userCert/userCertSearchPop";
	}

	/**
	*  List Page
	*
	* @param UserCertExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/userCert/getUserCertPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getUserCertPageList(HttpServletRequest request, @Valid final UserCertExDto params) throws Exception {
		final int totalRows = this.userCertService.getUserCertListCount(params);
		final List<UserCertExDto> userCertList = this.userCertService.getUserCertList(params);
		
		Map<String,Object> mapData = (new ResponseList(userCertList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param UserCertExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/userCert/getUserCertList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<UserCertExDto> getUserCertList(@Valid final UserCertExDto params) throws Exception {
		final List<UserCertExDto> listData = this.userCertService.getUserCertList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param UserCertExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/userCert/getUserCert.do")
	@ResponseBody
	public Object getUserCert(@Valid final UserCertExDto params) throws Exception {
		final UserCertExDto mapData = this.userCertService.getUserCert(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param UserCertExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/userCert/insertUserCert.do")
	@ResponseBody
	public Map<String,Object> insertUserCert(@Valid final UserCertExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.userCertService.insertUserCert(params);
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
	* @param UserCertExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/userCert/updateUserCert.do")
	@ResponseBody
	public Map<String,Object> updateUserCert(@Valid final UserCertExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.userCertService.updateUserCert(params);
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
	* @param UserCertExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/userCert/deleteUserCert.do")
	@ResponseBody
	public Map<String,Object> deleteUserCert(@Valid final UserCertExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.userCertService.deleteUserCert(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/userCert/getUserCertExcelDown.do")
	@ResponseBody
	public Object getUserCertExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		UserCertExDto userCertExDto = new UserCertExDto();
		userCertExDto = (UserCertExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), userCertExDto);
		final List<UserCertExDto> listData = this.userCertService.getUserCertList(userCertExDto);
		
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
