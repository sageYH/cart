package com.app.menuLang.controller;

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

import com.app.menuLang.model.MenuLangExDto;
import com.app.menuLang.service.MenuLangService;
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
public class MenuLangController {

	@Autowired
	private MenuLangService menuLangService;

	@RequestMapping("app/menuLang/menuLangList.do")
	public String menuLangList() throws Exception {
		return "app/menuLang/menuLangList";
	}

	@RequestMapping("app/popup/menuLang/menuLangPop.do")
	public String menuLangPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/menuLang/menuLangPop";
	}

	@RequestMapping("app/popup/menuLang/menuLangInfoPop.do")
	public String menuLangInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/menuLang/menuLangInfoPop";
	}

	@RequestMapping("app/popup/menuLang/menuLangSearchPop.do")
	public String menuLangSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/menuLang/menuLangSearchPop";
	}

	/**
	*  List Page
	*
	* @param MenuLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/menuLang/getMenuLangPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getMenuLangPageList(HttpServletRequest request, @Valid final MenuLangExDto params) throws Exception {
		final int totalRows = this.menuLangService.getMenuLangListCount(params);
		final List<MenuLangExDto> menuLangList = this.menuLangService.getMenuLangList(params);
		
		Map<String,Object> mapData = (new ResponseList(menuLangList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param MenuLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/menuLang/getMenuLangList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<MenuLangExDto> getMenuLangList(@Valid final MenuLangExDto params) throws Exception {
		final List<MenuLangExDto> listData = this.menuLangService.getMenuLangList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param MenuLangExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/menuLang/getMenuLang.do")
	@ResponseBody
	public Object getMenuLang(@Valid final MenuLangExDto params) throws Exception {
		final MenuLangExDto mapData = this.menuLangService.getMenuLang(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param MenuLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/menuLang/insertMenuLang.do")
	@ResponseBody
	public Map<String,Object> insertMenuLang(@Valid final MenuLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.menuLangService.insertMenuLang(params);
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
	* @param MenuLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/menuLang/updateMenuLang.do")
	@ResponseBody
	public Map<String,Object> updateMenuLang(@Valid final MenuLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.menuLangService.updateMenuLang(params);
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
	* @param MenuLangExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/menuLang/deleteMenuLang.do")
	@ResponseBody
	public Map<String,Object> deleteMenuLang(@Valid final MenuLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.menuLangService.deleteMenuLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/menuLang/getMenuLangExcelDown.do")
	@ResponseBody
	public Object getMenuLangExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		MenuLangExDto menuLangExDto = new MenuLangExDto();
		menuLangExDto = (MenuLangExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), menuLangExDto);
		final List<MenuLangExDto> listData = this.menuLangService.getMenuLangList(menuLangExDto);
		
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
