package com.app.mbrLvl.controller;

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

import com.app.mbrLvl.model.MbrLvlExDto;
import com.app.mbrLvl.service.MbrLvlService;
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
public class MbrLvlController {

	@Autowired
	private MbrLvlService mbrLvlService;

	@RequestMapping("app/mbrLvl/mbrLvlList.do")
	public String mbrLvlList() throws Exception {
		return "app/mbrLvl/mbrLvlList";
	}

	@RequestMapping("app/popup/mbrLvl/mbrLvlPop.do")
	public String mbrLvlPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/mbrLvl/mbrLvlPop";
	}

	@RequestMapping("app/popup/mbrLvl/mbrLvlInfoPop.do")
	public String mbrLvlInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/mbrLvl/mbrLvlInfoPop";
	}

	@RequestMapping("app/popup/mbrLvl/mbrLvlSearchPop.do")
	public String mbrLvlSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/mbrLvl/mbrLvlSearchPop";
	}

	/**
	*  List Page
	*
	* @param MbrLvlExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/mbrLvl/getMbrLvlPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getMbrLvlPageList(HttpServletRequest request, @Valid final MbrLvlExDto params) throws Exception {
		final int totalRows = this.mbrLvlService.getMbrLvlListCount(params);
		final List<MbrLvlExDto> mbrLvlList = this.mbrLvlService.getMbrLvlList(params);
		
		Map<String,Object> mapData = (new ResponseList(mbrLvlList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param MbrLvlExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/mbrLvl/getMbrLvlList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<MbrLvlExDto> getMbrLvlList(@Valid final MbrLvlExDto params) throws Exception {
		final List<MbrLvlExDto> listData = this.mbrLvlService.getMbrLvlList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param MbrLvlExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/mbrLvl/getMbrLvl.do")
	@ResponseBody
	public Object getMbrLvl(@Valid final MbrLvlExDto params) throws Exception {
		final MbrLvlExDto mapData = this.mbrLvlService.getMbrLvl(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param MbrLvlExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/mbrLvl/insertMbrLvl.do")
	@ResponseBody
	public Map<String,Object> insertMbrLvl(@Valid final MbrLvlExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.mbrLvlService.insertMbrLvl(params);
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
	* @param MbrLvlExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/mbrLvl/updateMbrLvl.do")
	@ResponseBody
	public Map<String,Object> updateMbrLvl(@Valid final MbrLvlExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.mbrLvlService.updateMbrLvl(params);
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
	* @param MbrLvlExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/mbrLvl/deleteMbrLvl.do")
	@ResponseBody
	public Map<String,Object> deleteMbrLvl(@Valid final MbrLvlExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.mbrLvlService.deleteMbrLvl(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/mbrLvl/getMbrLvlExcelDown.do")
	@ResponseBody
	public Object getMbrLvlExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		MbrLvlExDto mbrLvlExDto = new MbrLvlExDto();
		mbrLvlExDto = (MbrLvlExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), mbrLvlExDto);
		final List<MbrLvlExDto> listData = this.mbrLvlService.getMbrLvlList(mbrLvlExDto);
		
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
