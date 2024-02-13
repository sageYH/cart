package com.app.faqLang.controller;

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

import com.app.faqLang.model.FaqLangExDto;
import com.app.faqLang.service.FaqLangService;
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
public class FaqLangController {

	@Autowired
	private FaqLangService faqLangService;

	@RequestMapping("app/faqLang/faqLangList.do")
	public String faqLangList() throws Exception {
		return "app/faqLang/faqLangList";
	}

	@RequestMapping("app/popup/faqLang/faqLangPop.do")
	public String faqLangPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/faqLang/faqLangPop";
	}

	@RequestMapping("app/popup/faqLang/faqLangInfoPop.do")
	public String faqLangInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/faqLang/faqLangInfoPop";
	}

	@RequestMapping("app/popup/faqLang/faqLangSearchPop.do")
	public String faqLangSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/faqLang/faqLangSearchPop";
	}

	/**
	*  List Page
	*
	* @param FaqLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/faqLang/getFaqLangPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getFaqLangPageList(HttpServletRequest request, @Valid final FaqLangExDto params) throws Exception {
		final int totalRows = this.faqLangService.getFaqLangListCount(params);
		final List<FaqLangExDto> faqLangList = this.faqLangService.getFaqLangList(params);
		
		Map<String,Object> mapData = (new ResponseList(faqLangList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param FaqLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/faqLang/getFaqLangList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<FaqLangExDto> getFaqLangList(@Valid final FaqLangExDto params) throws Exception {
		final List<FaqLangExDto> listData = this.faqLangService.getFaqLangList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param FaqLangExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/faqLang/getFaqLang.do")
	@ResponseBody
	public Object getFaqLang(@Valid final FaqLangExDto params) throws Exception {
		final FaqLangExDto mapData = this.faqLangService.getFaqLang(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param FaqLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/faqLang/insertFaqLang.do")
	@ResponseBody
	public Map<String,Object> insertFaqLang(@Valid final FaqLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.faqLangService.insertFaqLang(params);
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
	* @param FaqLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/faqLang/updateFaqLang.do")
	@ResponseBody
	public Map<String,Object> updateFaqLang(@Valid final FaqLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.faqLangService.updateFaqLang(params);
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
	* @param FaqLangExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/faqLang/deleteFaqLang.do")
	@ResponseBody
	public Map<String,Object> deleteFaqLang(@Valid final FaqLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.faqLangService.deleteFaqLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/faqLang/getFaqLangExcelDown.do")
	@ResponseBody
	public Object getFaqLangExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		FaqLangExDto faqLangExDto = new FaqLangExDto();
		faqLangExDto = (FaqLangExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), faqLangExDto);
		final List<FaqLangExDto> listData = this.faqLangService.getFaqLangList(faqLangExDto);
		
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
