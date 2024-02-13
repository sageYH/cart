package com.app.faq.controller;

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

import com.app.faq.model.FaqExDto;
import com.app.faq.service.FaqService;
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
public class FaqController {

	@Autowired
	private FaqService faqService;

	@RequestMapping("app/faq/faqList.do")
	public String faqList() throws Exception {
		return "app/faq/faqList";
	}

	@RequestMapping("app/popup/faq/faqPop.do")
	public String faqPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/faq/faqPop";
	}

	@RequestMapping("app/popup/faq/faqInfoPop.do")
	public String faqInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/faq/faqInfoPop";
	}

	@RequestMapping("app/popup/faq/faqSearchPop.do")
	public String faqSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/faq/faqSearchPop";
	}

	/**
	*  List Page
	*
	* @param FaqExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/faq/getFaqPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getFaqPageList(HttpServletRequest request, @Valid final FaqExDto params) throws Exception {
		final int totalRows = this.faqService.getFaqListCount(params);
		final List<FaqExDto> faqList = this.faqService.getFaqList(params);
		
		Map<String,Object> mapData = (new ResponseList(faqList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param FaqExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/faq/getFaqList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<FaqExDto> getFaqList(@Valid final FaqExDto params) throws Exception {
		final List<FaqExDto> listData = this.faqService.getFaqList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param FaqExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/faq/getFaq.do")
	@ResponseBody
	public Object getFaq(@Valid final FaqExDto params) throws Exception {
		final FaqExDto mapData = this.faqService.getFaq(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param FaqExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/faq/insertFaq.do")
	@ResponseBody
	public Map<String,Object> insertFaq(@Valid final FaqExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.faqService.insertFaq(params);
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
	* @param FaqExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/faq/updateFaq.do")
	@ResponseBody
	public Map<String,Object> updateFaq(@Valid final FaqExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.faqService.updateFaq(params);
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
	* @param FaqExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/faq/deleteFaq.do")
	@ResponseBody
	public Map<String,Object> deleteFaq(@Valid final FaqExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.faqService.deleteFaq(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/faq/getFaqExcelDown.do")
	@ResponseBody
	public Object getFaqExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		FaqExDto faqExDto = new FaqExDto();
		faqExDto = (FaqExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), faqExDto);
		final List<FaqExDto> listData = this.faqService.getFaqList(faqExDto);
		
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
