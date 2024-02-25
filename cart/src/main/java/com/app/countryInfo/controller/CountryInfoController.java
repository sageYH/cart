package com.app.countryInfo.controller;

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

import com.app.countryInfo.model.CountryInfoExDto;
import com.app.countryInfo.service.CountryInfoService;
import com.base.ResponseList;
import com.base.ResponseMap;
import com.common.utils.ClassUtil;
import com.common.utils.Excel;
import com.common.utils.MapUtil;

import lombok.RequiredArgsConstructor;

/**
* 국가정보 관리
*
* @author
*
*/

@Controller
@Lazy
@RequiredArgsConstructor
public class CountryInfoController {

	@Autowired
	private CountryInfoService countryInfoService;

	@RequestMapping("app/countryInfo/countryInfoList.do")
	public String countryInfoList() throws Exception {
		return "app/countryInfo/countryInfoList";
	}

	@RequestMapping("app/popup/countryInfo/countryInfoPop.do")
	public String countryInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/countryInfo/countryInfoPop";
	}

	@RequestMapping("app/popup/countryInfo/countryInfoInfoPop.do")
	public String countryInfoInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/countryInfo/countryInfoInfoPop";
	}

	@RequestMapping("app/popup/countryInfo/countryInfoSearchPop.do")
	public String countryInfoSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/countryInfo/countryInfoSearchPop";
	}

	/**
	* 국가정보 List Page
	*
	* @param CountryInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/countryInfo/getCountryInfoPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCountryInfoPageList(HttpServletRequest request, @Valid final CountryInfoExDto params) throws Exception {
		final int totalRows = this.countryInfoService.getCountryInfoListCount(params);
		final List<CountryInfoExDto> countryInfoList = this.countryInfoService.getCountryInfoList(params);
		
		Map<String,Object> mapData = (new ResponseList(countryInfoList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	* 국가정보 List Inquiry
	*
	* @param CountryInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/countryInfo/getCountryInfoList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CountryInfoExDto> getCountryInfoList(@Valid final CountryInfoExDto params) throws Exception {
		final List<CountryInfoExDto> listData = this.countryInfoService.getCountryInfoList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요 국가정보 detail infomation Inquiry
	* </pre>
	*
	* @param CountryInfoExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/countryInfo/getCountryInfo.do")
	@ResponseBody
	public Object getCountryInfo(@Valid final CountryInfoExDto params) throws Exception {
		final CountryInfoExDto mapData = this.countryInfoService.getCountryInfo(params);
		return mapData;
	}

	/**
	* 국가정보 insert
	*
	* @param CountryInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/countryInfo/insertCountryInfo.do")
	@ResponseBody
	public Map<String,Object> insertCountryInfo(@Valid final CountryInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.countryInfoService.insertCountryInfo(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 국가정보 Update
	*
	* @param CountryInfoExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/countryInfo/updateCountryInfo.do")
	@ResponseBody
	public Map<String,Object> updateCountryInfo(@Valid final CountryInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.countryInfoService.updateCountryInfo(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 국가정보 delete
	*
	* @param CountryInfoExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/countryInfo/deleteCountryInfo.do")
	@ResponseBody
	public Map<String,Object> deleteCountryInfo(@Valid final CountryInfoExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.countryInfoService.deleteCountryInfo(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/countryInfo/getCountryInfoExcelDown.do")
	@ResponseBody
	public Object getCountryInfoExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CountryInfoExDto countryInfoExDto = new CountryInfoExDto();
		countryInfoExDto = (CountryInfoExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), countryInfoExDto);
		final List<CountryInfoExDto> listData = this.countryInfoService.getCountryInfoList(countryInfoExDto);
		
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
