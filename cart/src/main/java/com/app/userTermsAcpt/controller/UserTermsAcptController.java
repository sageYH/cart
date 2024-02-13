package com.app.userTermsAcpt.controller;

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

import com.app.userTermsAcpt.model.UserTermsAcptExDto;
import com.app.userTermsAcpt.service.UserTermsAcptService;
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
public class UserTermsAcptController {

	@Autowired
	private UserTermsAcptService userTermsAcptService;

	@RequestMapping("app/userTermsAcpt/userTermsAcptList.do")
	public String userTermsAcptList() throws Exception {
		return "app/userTermsAcpt/userTermsAcptList";
	}

	@RequestMapping("app/popup/userTermsAcpt/userTermsAcptPop.do")
	public String userTermsAcptPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/userTermsAcpt/userTermsAcptPop";
	}

	@RequestMapping("app/popup/userTermsAcpt/userTermsAcptInfoPop.do")
	public String userTermsAcptInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/userTermsAcpt/userTermsAcptInfoPop";
	}

	@RequestMapping("app/popup/userTermsAcpt/userTermsAcptSearchPop.do")
	public String userTermsAcptSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/userTermsAcpt/userTermsAcptSearchPop";
	}

	/**
	*  List Page
	*
	* @param UserTermsAcptExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/userTermsAcpt/getUserTermsAcptPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getUserTermsAcptPageList(HttpServletRequest request, @Valid final UserTermsAcptExDto params) throws Exception {
		final int totalRows = this.userTermsAcptService.getUserTermsAcptListCount(params);
		final List<UserTermsAcptExDto> userTermsAcptList = this.userTermsAcptService.getUserTermsAcptList(params);
		
		Map<String,Object> mapData = (new ResponseList(userTermsAcptList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param UserTermsAcptExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/userTermsAcpt/getUserTermsAcptList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<UserTermsAcptExDto> getUserTermsAcptList(@Valid final UserTermsAcptExDto params) throws Exception {
		final List<UserTermsAcptExDto> listData = this.userTermsAcptService.getUserTermsAcptList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param UserTermsAcptExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/userTermsAcpt/getUserTermsAcpt.do")
	@ResponseBody
	public Object getUserTermsAcpt(@Valid final UserTermsAcptExDto params) throws Exception {
		final UserTermsAcptExDto mapData = this.userTermsAcptService.getUserTermsAcpt(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param UserTermsAcptExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/userTermsAcpt/insertUserTermsAcpt.do")
	@ResponseBody
	public Map<String,Object> insertUserTermsAcpt(@Valid final UserTermsAcptExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.userTermsAcptService.insertUserTermsAcpt(params);
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
	* @param UserTermsAcptExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/userTermsAcpt/updateUserTermsAcpt.do")
	@ResponseBody
	public Map<String,Object> updateUserTermsAcpt(@Valid final UserTermsAcptExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.userTermsAcptService.updateUserTermsAcpt(params);
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
	* @param UserTermsAcptExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/userTermsAcpt/deleteUserTermsAcpt.do")
	@ResponseBody
	public Map<String,Object> deleteUserTermsAcpt(@Valid final UserTermsAcptExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.userTermsAcptService.deleteUserTermsAcpt(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/userTermsAcpt/getUserTermsAcptExcelDown.do")
	@ResponseBody
	public Object getUserTermsAcptExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		UserTermsAcptExDto userTermsAcptExDto = new UserTermsAcptExDto();
		userTermsAcptExDto = (UserTermsAcptExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), userTermsAcptExDto);
		final List<UserTermsAcptExDto> listData = this.userTermsAcptService.getUserTermsAcptList(userTermsAcptExDto);
		
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
