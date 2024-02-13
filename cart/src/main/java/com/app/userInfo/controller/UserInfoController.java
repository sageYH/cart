package com.app.userInfo.controller;

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

import com.app.userInfo.model.UserInfoExDto;
import com.app.userInfo.service.UserInfoService;
import com.base.ResponseList;
import com.base.ResponseMap;
import com.common.utils.ClassUtil;
import com.common.utils.Excel;
import com.common.utils.MapUtil;

import lombok.RequiredArgsConstructor;

/**
* 사용자정보 관리
*
* @author
*
*/

@Controller
@Lazy
@RequiredArgsConstructor
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping("app/userInfo/userInfoList.do")
	public String userInfoList() throws Exception {
		return "app/userInfo/userInfoList";
	}

	@RequestMapping("app/popup/userInfo/userInfoPop.do")
	public String userInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/userInfo/userInfoPop";
	}

	@RequestMapping("app/popup/userInfo/userInfoInfoPop.do")
	public String userInfoInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/userInfo/userInfoInfoPop";
	}

	@RequestMapping("app/popup/userInfo/userInfoSearchPop.do")
	public String userInfoSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/userInfo/userInfoSearchPop";
	}

	/**
	* 사용자정보 List Page
	*
	* @param UserInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/userInfo/getUserInfoPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getUserInfoPageList(HttpServletRequest request, @Valid final UserInfoExDto params) throws Exception {
		final int totalRows = this.userInfoService.getUserInfoListCount(params);
		final List<UserInfoExDto> userInfoList = this.userInfoService.getUserInfoList(params);
		
		Map<String,Object> mapData = (new ResponseList(userInfoList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	* 사용자정보 List Inquiry
	*
	* @param UserInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/userInfo/getUserInfoList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<UserInfoExDto> getUserInfoList(@Valid final UserInfoExDto params) throws Exception {
		final List<UserInfoExDto> listData = this.userInfoService.getUserInfoList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요 사용자정보 detail infomation Inquiry
	* </pre>
	*
	* @param UserInfoExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/userInfo/getUserInfo.do")
	@ResponseBody
	public Object getUserInfo(@Valid final UserInfoExDto params) throws Exception {
		final UserInfoExDto mapData = this.userInfoService.getUserInfo(params);
		return mapData;
	}

	/**
	* 사용자정보 insert
	*
	* @param UserInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/userInfo/insertUserInfo.do")
	@ResponseBody
	public Map<String,Object> insertUserInfo(@Valid final UserInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.userInfoService.insertUserInfo(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 사용자정보 Update
	*
	* @param UserInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/userInfo/updateUserInfo.do")
	@ResponseBody
	public Map<String,Object> updateUserInfo(@Valid final UserInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.userInfoService.updateUserInfo(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 사용자정보 delete
	*
	* @param UserInfoExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/userInfo/deleteUserInfo.do")
	@ResponseBody
	public Map<String,Object> deleteUserInfo(@Valid final UserInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.userInfoService.deleteUserInfo(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/userInfo/getUserInfoExcelDown.do")
	@ResponseBody
	public Object getUserInfoExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		UserInfoExDto userInfoExDto = new UserInfoExDto();
		userInfoExDto = (UserInfoExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), userInfoExDto);
		final List<UserInfoExDto> listData = this.userInfoService.getUserInfoList(userInfoExDto);
		
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
