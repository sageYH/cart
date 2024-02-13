package com.app.commInfoLang.controller;

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

import com.app.commInfoLang.model.CommInfoLangExDto;
import com.app.commInfoLang.service.CommInfoLangService;
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
public class CommInfoLangController {

	@Autowired
	private CommInfoLangService commInfoLangService;

	@RequestMapping("app/commInfoLang/commInfoLangList.do")
	public String commInfoLangList() throws Exception {
		return "app/commInfoLang/commInfoLangList";
	}

	@RequestMapping("app/popup/commInfoLang/commInfoLangPop.do")
	public String commInfoLangPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/commInfoLang/commInfoLangPop";
	}

	@RequestMapping("app/popup/commInfoLang/commInfoLangInfoPop.do")
	public String commInfoLangInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/commInfoLang/commInfoLangInfoPop";
	}

	@RequestMapping("app/popup/commInfoLang/commInfoLangSearchPop.do")
	public String commInfoLangSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/commInfoLang/commInfoLangSearchPop";
	}

	/**
	*  List Page
	*
	* @param CommInfoLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/commInfoLang/getCommInfoLangPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCommInfoLangPageList(HttpServletRequest request, @Valid final CommInfoLangExDto params) throws Exception {
		final int totalRows = this.commInfoLangService.getCommInfoLangListCount(params);
		final List<CommInfoLangExDto> commInfoLangList = this.commInfoLangService.getCommInfoLangList(params);
		
		Map<String,Object> mapData = (new ResponseList(commInfoLangList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param CommInfoLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/commInfoLang/getCommInfoLangList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CommInfoLangExDto> getCommInfoLangList(@Valid final CommInfoLangExDto params) throws Exception {
		final List<CommInfoLangExDto> listData = this.commInfoLangService.getCommInfoLangList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param CommInfoLangExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/commInfoLang/getCommInfoLang.do")
	@ResponseBody
	public Object getCommInfoLang(@Valid final CommInfoLangExDto params) throws Exception {
		final CommInfoLangExDto mapData = this.commInfoLangService.getCommInfoLang(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param CommInfoLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/commInfoLang/insertCommInfoLang.do")
	@ResponseBody
	public Map<String,Object> insertCommInfoLang(@Valid final CommInfoLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.commInfoLangService.insertCommInfoLang(params);
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
	* @param CommInfoLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/commInfoLang/updateCommInfoLang.do")
	@ResponseBody
	public Map<String,Object> updateCommInfoLang(@Valid final CommInfoLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.commInfoLangService.updateCommInfoLang(params);
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
	* @param CommInfoLangExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/commInfoLang/deleteCommInfoLang.do")
	@ResponseBody
	public Map<String,Object> deleteCommInfoLang(@Valid final CommInfoLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.commInfoLangService.deleteCommInfoLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/commInfoLang/getCommInfoLangExcelDown.do")
	@ResponseBody
	public Object getCommInfoLangExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CommInfoLangExDto commInfoLangExDto = new CommInfoLangExDto();
		commInfoLangExDto = (CommInfoLangExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), commInfoLangExDto);
		final List<CommInfoLangExDto> listData = this.commInfoLangService.getCommInfoLangList(commInfoLangExDto);
		
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
