package com.app.noticeLang.controller;

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

import com.app.noticeLang.model.NoticeLangExDto;
import com.app.noticeLang.service.NoticeLangService;
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
public class NoticeLangController {

	@Autowired
	private NoticeLangService noticeLangService;

	@RequestMapping("app/noticeLang/noticeLangList.do")
	public String noticeLangList() throws Exception {
		return "app/noticeLang/noticeLangList";
	}

	@RequestMapping("app/popup/noticeLang/noticeLangPop.do")
	public String noticeLangPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/noticeLang/noticeLangPop";
	}

	@RequestMapping("app/popup/noticeLang/noticeLangInfoPop.do")
	public String noticeLangInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/noticeLang/noticeLangInfoPop";
	}

	@RequestMapping("app/popup/noticeLang/noticeLangSearchPop.do")
	public String noticeLangSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/noticeLang/noticeLangSearchPop";
	}

	/**
	*  List Page
	*
	* @param NoticeLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/noticeLang/getNoticeLangPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getNoticeLangPageList(HttpServletRequest request, @Valid final NoticeLangExDto params) throws Exception {
		final int totalRows = this.noticeLangService.getNoticeLangListCount(params);
		final List<NoticeLangExDto> noticeLangList = this.noticeLangService.getNoticeLangList(params);
		
		Map<String,Object> mapData = (new ResponseList(noticeLangList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param NoticeLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/noticeLang/getNoticeLangList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<NoticeLangExDto> getNoticeLangList(@Valid final NoticeLangExDto params) throws Exception {
		final List<NoticeLangExDto> listData = this.noticeLangService.getNoticeLangList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param NoticeLangExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/noticeLang/getNoticeLang.do")
	@ResponseBody
	public Object getNoticeLang(@Valid final NoticeLangExDto params) throws Exception {
		final NoticeLangExDto mapData = this.noticeLangService.getNoticeLang(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param NoticeLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/noticeLang/insertNoticeLang.do")
	@ResponseBody
	public Map<String,Object> insertNoticeLang(@Valid final NoticeLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.noticeLangService.insertNoticeLang(params);
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
	* @param NoticeLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/noticeLang/updateNoticeLang.do")
	@ResponseBody
	public Map<String,Object> updateNoticeLang(@Valid final NoticeLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.noticeLangService.updateNoticeLang(params);
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
	* @param NoticeLangExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/noticeLang/deleteNoticeLang.do")
	@ResponseBody
	public Map<String,Object> deleteNoticeLang(@Valid final NoticeLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.noticeLangService.deleteNoticeLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/noticeLang/getNoticeLangExcelDown.do")
	@ResponseBody
	public Object getNoticeLangExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		NoticeLangExDto noticeLangExDto = new NoticeLangExDto();
		noticeLangExDto = (NoticeLangExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), noticeLangExDto);
		final List<NoticeLangExDto> listData = this.noticeLangService.getNoticeLangList(noticeLangExDto);
		
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
