package com.app.userAuth.controller;

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

import com.app.userAuth.model.UserAuthExDto;
import com.app.userAuth.service.UserAuthService;
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
public class UserAuthController {

	@Autowired
	private UserAuthService userAuthService;

	@RequestMapping("app/userAuth/userAuthList.do")
	public String userAuthList() throws Exception {
		return "app/userAuth/userAuthList";
	}

	@RequestMapping("app/popup/userAuth/userAuthPop.do")
	public String userAuthPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/userAuth/userAuthPop";
	}

	@RequestMapping("app/popup/userAuth/userAuthInfoPop.do")
	public String userAuthInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/userAuth/userAuthInfoPop";
	}

	@RequestMapping("app/popup/userAuth/userAuthSearchPop.do")
	public String userAuthSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/userAuth/userAuthSearchPop";
	}

	/**
	*  List Page
	*
	* @param UserAuthExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/userAuth/getUserAuthPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getUserAuthPageList(HttpServletRequest request, @Valid final UserAuthExDto params) throws Exception {
		final int totalRows = this.userAuthService.getUserAuthListCount(params);
		final List<UserAuthExDto> userAuthList = this.userAuthService.getUserAuthList(params);
		
		Map<String,Object> mapData = (new ResponseList(userAuthList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param UserAuthExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/userAuth/getUserAuthList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<UserAuthExDto> getUserAuthList(@Valid final UserAuthExDto params) throws Exception {
		final List<UserAuthExDto> listData = this.userAuthService.getUserAuthList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param UserAuthExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/userAuth/getUserAuth.do")
	@ResponseBody
	public Object getUserAuth(@Valid final UserAuthExDto params) throws Exception {
		final UserAuthExDto mapData = this.userAuthService.getUserAuth(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param UserAuthExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/userAuth/insertUserAuth.do")
	@ResponseBody
	public Map<String,Object> insertUserAuth(@Valid final UserAuthExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.userAuthService.insertUserAuth(params);
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
	* @param UserAuthExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/userAuth/updateUserAuth.do")
	@ResponseBody
	public Map<String,Object> updateUserAuth(@Valid final UserAuthExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.userAuthService.updateUserAuth(params);
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
	* @param UserAuthExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/userAuth/deleteUserAuth.do")
	@ResponseBody
	public Map<String,Object> deleteUserAuth(@Valid final UserAuthExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.userAuthService.deleteUserAuth(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/userAuth/getUserAuthExcelDown.do")
	@ResponseBody
	public Object getUserAuthExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		UserAuthExDto userAuthExDto = new UserAuthExDto();
		userAuthExDto = (UserAuthExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), userAuthExDto);
		final List<UserAuthExDto> listData = this.userAuthService.getUserAuthList(userAuthExDto);
		
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
