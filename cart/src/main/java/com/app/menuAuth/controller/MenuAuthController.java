package com.app.menuAuth.controller;

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

import com.app.menuAuth.model.MenuAuthExDto;
import com.app.menuAuth.service.MenuAuthService;
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
public class MenuAuthController {

	@Autowired
	private MenuAuthService menuAuthService;

	@RequestMapping("app/menuAuth/menuAuthList.do")
	public String menuAuthList() throws Exception {
		return "app/menuAuth/menuAuthList";
	}

	@RequestMapping("app/popup/menuAuth/menuAuthPop.do")
	public String menuAuthPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/menuAuth/menuAuthPop";
	}

	@RequestMapping("app/popup/menuAuth/menuAuthInfoPop.do")
	public String menuAuthInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/menuAuth/menuAuthInfoPop";
	}

	@RequestMapping("app/popup/menuAuth/menuAuthSearchPop.do")
	public String menuAuthSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/menuAuth/menuAuthSearchPop";
	}

	/**
	*  List Page
	*
	* @param MenuAuthExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/menuAuth/getMenuAuthPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getMenuAuthPageList(HttpServletRequest request, @Valid final MenuAuthExDto params) throws Exception {
		final int totalRows = this.menuAuthService.getMenuAuthListCount(params);
		final List<MenuAuthExDto> menuAuthList = this.menuAuthService.getMenuAuthList(params);
		
		Map<String,Object> mapData = (new ResponseList(menuAuthList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param MenuAuthExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/menuAuth/getMenuAuthList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<MenuAuthExDto> getMenuAuthList(@Valid final MenuAuthExDto params) throws Exception {
		final List<MenuAuthExDto> listData = this.menuAuthService.getMenuAuthList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param MenuAuthExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/menuAuth/getMenuAuth.do")
	@ResponseBody
	public Object getMenuAuth(@Valid final MenuAuthExDto params) throws Exception {
		final MenuAuthExDto mapData = this.menuAuthService.getMenuAuth(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param MenuAuthExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/menuAuth/insertMenuAuth.do")
	@ResponseBody
	public Map<String,Object> insertMenuAuth(@Valid final MenuAuthExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.menuAuthService.insertMenuAuth(params);
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
	* @param MenuAuthExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/menuAuth/updateMenuAuth.do")
	@ResponseBody
	public Map<String,Object> updateMenuAuth(@Valid final MenuAuthExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.menuAuthService.updateMenuAuth(params);
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
	* @param MenuAuthExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/menuAuth/deleteMenuAuth.do")
	@ResponseBody
	public Map<String,Object> deleteMenuAuth(@Valid final MenuAuthExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.menuAuthService.deleteMenuAuth(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/menuAuth/getMenuAuthExcelDown.do")
	@ResponseBody
	public Object getMenuAuthExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		MenuAuthExDto menuAuthExDto = new MenuAuthExDto();
		menuAuthExDto = (MenuAuthExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), menuAuthExDto);
		final List<MenuAuthExDto> listData = this.menuAuthService.getMenuAuthList(menuAuthExDto);
		
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
