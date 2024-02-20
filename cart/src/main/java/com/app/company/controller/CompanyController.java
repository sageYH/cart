package com.app.company.controller;

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

import com.app.company.model.CompanyExDto;
import com.app.company.service.CompanyService;
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
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@RequestMapping("app/company/companyList.do")
	public String companyList() throws Exception {
		return "app/company/companyList";
	}

	@RequestMapping("app/popup/company/companyPop.do")
	public String companyPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/company/companyPop";
	}

	@RequestMapping("app/popup/company/companyInfoPop.do")
	public String companyInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/company/companyInfoPop";
	}

	@RequestMapping("app/popup/company/companySearchPop.do")
	public String companySearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/company/companySearchPop";
	}

	/**
	*  List Page
	*
	* @param CompanyExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/company/getCompanyPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCompanyPageList(HttpServletRequest request, @Valid final CompanyExDto params) throws Exception {
		final int totalRows = this.companyService.getCompanyListCount(params);
		final List<CompanyExDto> companyList = this.companyService.getCompanyList(params);
		
		Map<String,Object> mapData = (new ResponseList(companyList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param CompanyExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/company/getCompanyList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CompanyExDto> getCompanyList(@Valid final CompanyExDto params) throws Exception {
		final List<CompanyExDto> listData = this.companyService.getCompanyList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param CompanyExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/company/getCompany.do")
	@ResponseBody
	public Object getCompany(@Valid final CompanyExDto params) throws Exception {
		final CompanyExDto mapData = this.companyService.getCompany(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param CompanyExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/company/insertCompany.do")
	@ResponseBody
	public Map<String,Object> insertCompany(@Valid final CompanyExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.companyService.insertCompany(params);
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
	* @param CompanyExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/company/updateCompany.do")
	@ResponseBody
	public Map<String,Object> updateCompany(@Valid final CompanyExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.companyService.updateCompany(params);
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
	* @param CompanyExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/company/deleteCompany.do")
	@ResponseBody
	public Map<String,Object> deleteCompany(@Valid final CompanyExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.companyService.deleteCompany(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/company/getCompanyExcelDown.do")
	@ResponseBody
	public Object getCompanyExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CompanyExDto companyExDto = new CompanyExDto();
		companyExDto = (CompanyExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), companyExDto);
		final List<CompanyExDto> listData = this.companyService.getCompanyList(companyExDto);
		
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
