package com.app.newsLang.controller;

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

import com.app.newsLang.model.NewsLangExDto;
import com.app.newsLang.service.NewsLangService;
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
public class NewsLangController {

	@Autowired
	private NewsLangService newsLangService;

	@RequestMapping("app/newsLang/newsLangList.do")
	public String newsLangList() throws Exception {
		return "app/newsLang/newsLangList";
	}

	@RequestMapping("app/popup/newsLang/newsLangPop.do")
	public String newsLangPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/newsLang/newsLangPop";
	}

	@RequestMapping("app/popup/newsLang/newsLangInfoPop.do")
	public String newsLangInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/newsLang/newsLangInfoPop";
	}

	@RequestMapping("app/popup/newsLang/newsLangSearchPop.do")
	public String newsLangSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/newsLang/newsLangSearchPop";
	}

	/**
	*  List Page
	*
	* @param NewsLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/newsLang/getNewsLangPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getNewsLangPageList(HttpServletRequest request, @Valid final NewsLangExDto params) throws Exception {
		final int totalRows = this.newsLangService.getNewsLangListCount(params);
		final List<NewsLangExDto> newsLangList = this.newsLangService.getNewsLangList(params);
		
		Map<String,Object> mapData = (new ResponseList(newsLangList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param NewsLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/newsLang/getNewsLangList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<NewsLangExDto> getNewsLangList(@Valid final NewsLangExDto params) throws Exception {
		final List<NewsLangExDto> listData = this.newsLangService.getNewsLangList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param NewsLangExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/newsLang/getNewsLang.do")
	@ResponseBody
	public Object getNewsLang(@Valid final NewsLangExDto params) throws Exception {
		final NewsLangExDto mapData = this.newsLangService.getNewsLang(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param NewsLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/newsLang/insertNewsLang.do")
	@ResponseBody
	public Map<String,Object> insertNewsLang(@Valid final NewsLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.newsLangService.insertNewsLang(params);
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
	* @param NewsLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/newsLang/updateNewsLang.do")
	@ResponseBody
	public Map<String,Object> updateNewsLang(@Valid final NewsLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.newsLangService.updateNewsLang(params);
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
	* @param NewsLangExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/newsLang/deleteNewsLang.do")
	@ResponseBody
	public Map<String,Object> deleteNewsLang(@Valid final NewsLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.newsLangService.deleteNewsLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/newsLang/getNewsLangExcelDown.do")
	@ResponseBody
	public Object getNewsLangExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		NewsLangExDto newsLangExDto = new NewsLangExDto();
		newsLangExDto = (NewsLangExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), newsLangExDto);
		final List<NewsLangExDto> listData = this.newsLangService.getNewsLangList(newsLangExDto);
		
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
