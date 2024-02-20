package com.app.menu.controller;

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

import com.app.menu.model.MenuExDto;
import com.app.menu.service.MenuService;
import com.base.ResponseList;
import com.base.ResponseMap;
import com.common.utils.ClassUtil;
import com.common.utils.Excel;
import com.common.utils.MapUtil;

import lombok.RequiredArgsConstructor;

/**
* 메뉴 관리
*
* @author
*
*/

@Controller
@Lazy
@RequiredArgsConstructor
public class MenuController {

	@Autowired
	private MenuService menuService;

	@RequestMapping("app/menu/menuList.do")
	public String menuList() throws Exception {
		return "app/menu/menuList";
	}

	@RequestMapping("app/popup/menu/menuPop.do")
	public String menuPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/menu/menuPop";
	}

	@RequestMapping("app/popup/menu/menuInfoPop.do")
	public String menuInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/menu/menuInfoPop";
	}

	@RequestMapping("app/popup/menu/menuSearchPop.do")
	public String menuSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/menu/menuSearchPop";
	}

	/**
	* 메뉴 List Page
	*
	* @param MenuExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/menu/getMenuPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getMenuPageList(HttpServletRequest request, @Valid final MenuExDto params) throws Exception {
		final int totalRows = this.menuService.getMenuListCount(params);
		final List<MenuExDto> menuList = this.menuService.getMenuList(params);
		
		Map<String,Object> mapData = (new ResponseList(menuList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	* 메뉴 List Inquiry
	*
	* @param MenuExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/menu/getMenuList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<MenuExDto> getMenuList(@Valid final MenuExDto params) throws Exception {
		final List<MenuExDto> listData = this.menuService.getMenuList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요 메뉴 detail infomation Inquiry
	* </pre>
	*
	* @param MenuExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/menu/getMenu.do")
	@ResponseBody
	public Object getMenu(@Valid final MenuExDto params) throws Exception {
		final MenuExDto mapData = this.menuService.getMenu(params);
		return mapData;
	}

	/**
	* 메뉴 insert
	*
	* @param MenuExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/menu/insertMenu.do")
	@ResponseBody
	public Map<String,Object> insertMenu(@Valid final MenuExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.menuService.insertMenu(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 메뉴 Update
	*
	* @param MenuExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/menu/updateMenu.do")
	@ResponseBody
	public Map<String,Object> updateMenu(@Valid final MenuExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.menuService.updateMenu(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 메뉴 delete
	*
	* @param MenuExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/menu/deleteMenu.do")
	@ResponseBody
	public Map<String,Object> deleteMenu(@Valid final MenuExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.menuService.deleteMenu(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/menu/getMenuExcelDown.do")
	@ResponseBody
	public Object getMenuExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		MenuExDto menuExDto = new MenuExDto();
		menuExDto = (MenuExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), menuExDto);
		final List<MenuExDto> listData = this.menuService.getMenuList(menuExDto);
		
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

	@RequestMapping(value = "app/menu/getGnbMenu.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Map> getGnbMenu(@Valid final Map params) throws Exception {
		final List<Map> listData = this.menuService.getGnbMenu(params);
		
		return listData;
	}
}
