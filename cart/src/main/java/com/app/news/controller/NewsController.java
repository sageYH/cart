package com.app.news.controller;

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

import com.app.news.model.NewsExDto;
import com.app.news.service.NewsService;
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
public class NewsController {

	@Autowired
	private NewsService newsService;

	@RequestMapping("app/news/newsList.do")
	public String newsList() throws Exception {
		return "app/news/newsList";
	}

	@RequestMapping("app/popup/news/newsPop.do")
	public String newsPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/news/newsPop";
	}

	@RequestMapping("app/popup/news/newsInfoPop.do")
	public String newsInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/news/newsInfoPop";
	}

	@RequestMapping("app/popup/news/newsSearchPop.do")
	public String newsSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/news/newsSearchPop";
	}

	/**
	*  List Page
	*
	* @param NewsExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/news/getNewsPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getNewsPageList(HttpServletRequest request, @Valid final NewsExDto params) throws Exception {
		final int totalRows = this.newsService.getNewsListCount(params);
		final List<NewsExDto> newsList = this.newsService.getNewsList(params);
		
		Map<String,Object> mapData = (new ResponseList(newsList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param NewsExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/news/getNewsList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<NewsExDto> getNewsList(@Valid final NewsExDto params) throws Exception {
		final List<NewsExDto> listData = this.newsService.getNewsList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param NewsExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/news/getNews.do")
	@ResponseBody
	public Object getNews(@Valid final NewsExDto params) throws Exception {
		final NewsExDto mapData = this.newsService.getNews(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param NewsExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/news/insertNews.do")
	@ResponseBody
	public Map<String,Object> insertNews(@Valid final NewsExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.newsService.insertNews(params);
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
	* @param NewsExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/news/updateNews.do")
	@ResponseBody
	public Map<String,Object> updateNews(@Valid final NewsExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.newsService.updateNews(params);
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
	* @param NewsExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/news/deleteNews.do")
	@ResponseBody
	public Map<String,Object> deleteNews(@Valid final NewsExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.newsService.deleteNews(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/news/getNewsExcelDown.do")
	@ResponseBody
	public Object getNewsExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		NewsExDto newsExDto = new NewsExDto();
		newsExDto = (NewsExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), newsExDto);
		final List<NewsExDto> listData = this.newsService.getNewsList(newsExDto);
		
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
