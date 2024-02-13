package com.app.mbrLvlBase.controller;

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

import com.app.mbrLvlBase.model.MbrLvlBaseExDto;
import com.app.mbrLvlBase.service.MbrLvlBaseService;
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
public class MbrLvlBaseController {

	@Autowired
	private MbrLvlBaseService mbrLvlBaseService;

	@RequestMapping("app/mbrLvlBase/mbrLvlBaseList.do")
	public String mbrLvlBaseList() throws Exception {
		return "app/mbrLvlBase/mbrLvlBaseList";
	}

	@RequestMapping("app/popup/mbrLvlBase/mbrLvlBasePop.do")
	public String mbrLvlBasePop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/mbrLvlBase/mbrLvlBasePop";
	}

	@RequestMapping("app/popup/mbrLvlBase/mbrLvlBaseInfoPop.do")
	public String mbrLvlBaseInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/mbrLvlBase/mbrLvlBaseInfoPop";
	}

	@RequestMapping("app/popup/mbrLvlBase/mbrLvlBaseSearchPop.do")
	public String mbrLvlBaseSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/mbrLvlBase/mbrLvlBaseSearchPop";
	}

	/**
	*  List Page
	*
	* @param MbrLvlBaseExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/mbrLvlBase/getMbrLvlBasePageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getMbrLvlBasePageList(HttpServletRequest request, @Valid final MbrLvlBaseExDto params) throws Exception {
		final int totalRows = this.mbrLvlBaseService.getMbrLvlBaseListCount(params);
		final List<MbrLvlBaseExDto> mbrLvlBaseList = this.mbrLvlBaseService.getMbrLvlBaseList(params);
		
		Map<String,Object> mapData = (new ResponseList(mbrLvlBaseList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param MbrLvlBaseExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/mbrLvlBase/getMbrLvlBaseList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<MbrLvlBaseExDto> getMbrLvlBaseList(@Valid final MbrLvlBaseExDto params) throws Exception {
		final List<MbrLvlBaseExDto> listData = this.mbrLvlBaseService.getMbrLvlBaseList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param MbrLvlBaseExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/mbrLvlBase/getMbrLvlBase.do")
	@ResponseBody
	public Object getMbrLvlBase(@Valid final MbrLvlBaseExDto params) throws Exception {
		final MbrLvlBaseExDto mapData = this.mbrLvlBaseService.getMbrLvlBase(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param MbrLvlBaseExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/mbrLvlBase/insertMbrLvlBase.do")
	@ResponseBody
	public Map<String,Object> insertMbrLvlBase(@Valid final MbrLvlBaseExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.mbrLvlBaseService.insertMbrLvlBase(params);
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
	* @param MbrLvlBaseExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/mbrLvlBase/updateMbrLvlBase.do")
	@ResponseBody
	public Map<String,Object> updateMbrLvlBase(@Valid final MbrLvlBaseExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.mbrLvlBaseService.updateMbrLvlBase(params);
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
	* @param MbrLvlBaseExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/mbrLvlBase/deleteMbrLvlBase.do")
	@ResponseBody
	public Map<String,Object> deleteMbrLvlBase(@Valid final MbrLvlBaseExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.mbrLvlBaseService.deleteMbrLvlBase(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/mbrLvlBase/getMbrLvlBaseExcelDown.do")
	@ResponseBody
	public Object getMbrLvlBaseExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		MbrLvlBaseExDto mbrLvlBaseExDto = new MbrLvlBaseExDto();
		mbrLvlBaseExDto = (MbrLvlBaseExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), mbrLvlBaseExDto);
		final List<MbrLvlBaseExDto> listData = this.mbrLvlBaseService.getMbrLvlBaseList(mbrLvlBaseExDto);
		
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
