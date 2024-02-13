package com.app.userGrp.controller;

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

import com.app.userGrp.model.UserGrpExDto;
import com.app.userGrp.service.UserGrpService;
import com.base.ResponseList;
import com.base.ResponseMap;
import com.common.utils.ClassUtil;
import com.common.utils.Excel;
import com.common.utils.MapUtil;

import lombok.RequiredArgsConstructor;

/**
* 사용자그룹 관리
*
* @author
*
*/

@Controller
@Lazy
@RequiredArgsConstructor
public class UserGrpController {

	@Autowired
	private UserGrpService userGrpService;

	@RequestMapping("app/userGrp/userGrpList.do")
	public String userGrpList() throws Exception {
		return "app/userGrp/userGrpList";
	}

	@RequestMapping("app/popup/userGrp/userGrpPop.do")
	public String userGrpPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/userGrp/userGrpPop";
	}

	@RequestMapping("app/popup/userGrp/userGrpInfoPop.do")
	public String userGrpInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/userGrp/userGrpInfoPop";
	}

	@RequestMapping("app/popup/userGrp/userGrpSearchPop.do")
	public String userGrpSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/userGrp/userGrpSearchPop";
	}

	/**
	* 사용자그룹 List Page
	*
	* @param UserGrpExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/userGrp/getUserGrpPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getUserGrpPageList(HttpServletRequest request, @Valid final UserGrpExDto params) throws Exception {
		final int totalRows = this.userGrpService.getUserGrpListCount(params);
		final List<UserGrpExDto> userGrpList = this.userGrpService.getUserGrpList(params);
		
		Map<String,Object> mapData = (new ResponseList(userGrpList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	* 사용자그룹 List Inquiry
	*
	* @param UserGrpExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/userGrp/getUserGrpList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<UserGrpExDto> getUserGrpList(@Valid final UserGrpExDto params) throws Exception {
		final List<UserGrpExDto> listData = this.userGrpService.getUserGrpList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요 사용자그룹 detail infomation Inquiry
	* </pre>
	*
	* @param UserGrpExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/userGrp/getUserGrp.do")
	@ResponseBody
	public Object getUserGrp(@Valid final UserGrpExDto params) throws Exception {
		final UserGrpExDto mapData = this.userGrpService.getUserGrp(params);
		return mapData;
	}

	/**
	* 사용자그룹 insert
	*
	* @param UserGrpExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/userGrp/insertUserGrp.do")
	@ResponseBody
	public Map<String,Object> insertUserGrp(@Valid final UserGrpExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.userGrpService.insertUserGrp(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 사용자그룹 Update
	*
	* @param UserGrpExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/userGrp/updateUserGrp.do")
	@ResponseBody
	public Map<String,Object> updateUserGrp(@Valid final UserGrpExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.userGrpService.updateUserGrp(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 사용자그룹 delete
	*
	* @param UserGrpExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/userGrp/deleteUserGrp.do")
	@ResponseBody
	public Map<String,Object> deleteUserGrp(@Valid final UserGrpExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.userGrpService.deleteUserGrp(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/userGrp/getUserGrpExcelDown.do")
	@ResponseBody
	public Object getUserGrpExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		UserGrpExDto userGrpExDto = new UserGrpExDto();
		userGrpExDto = (UserGrpExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), userGrpExDto);
		final List<UserGrpExDto> listData = this.userGrpService.getUserGrpList(userGrpExDto);
		
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
