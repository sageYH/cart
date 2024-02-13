package com.app.mbrPymtMeth.controller;

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

import com.app.mbrPymtMeth.model.MbrPymtMethExDto;
import com.app.mbrPymtMeth.service.MbrPymtMethService;
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
public class MbrPymtMethController {

	@Autowired
	private MbrPymtMethService mbrPymtMethService;

	@RequestMapping("app/mbrPymtMeth/mbrPymtMethList.do")
	public String mbrPymtMethList() throws Exception {
		return "app/mbrPymtMeth/mbrPymtMethList";
	}

	@RequestMapping("app/popup/mbrPymtMeth/mbrPymtMethPop.do")
	public String mbrPymtMethPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/mbrPymtMeth/mbrPymtMethPop";
	}

	@RequestMapping("app/popup/mbrPymtMeth/mbrPymtMethInfoPop.do")
	public String mbrPymtMethInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/mbrPymtMeth/mbrPymtMethInfoPop";
	}

	@RequestMapping("app/popup/mbrPymtMeth/mbrPymtMethSearchPop.do")
	public String mbrPymtMethSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/mbrPymtMeth/mbrPymtMethSearchPop";
	}

	/**
	*  List Page
	*
	* @param MbrPymtMethExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/mbrPymtMeth/getMbrPymtMethPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getMbrPymtMethPageList(HttpServletRequest request, @Valid final MbrPymtMethExDto params) throws Exception {
		final int totalRows = this.mbrPymtMethService.getMbrPymtMethListCount(params);
		final List<MbrPymtMethExDto> mbrPymtMethList = this.mbrPymtMethService.getMbrPymtMethList(params);
		
		Map<String,Object> mapData = (new ResponseList(mbrPymtMethList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param MbrPymtMethExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/mbrPymtMeth/getMbrPymtMethList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<MbrPymtMethExDto> getMbrPymtMethList(@Valid final MbrPymtMethExDto params) throws Exception {
		final List<MbrPymtMethExDto> listData = this.mbrPymtMethService.getMbrPymtMethList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param MbrPymtMethExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/mbrPymtMeth/getMbrPymtMeth.do")
	@ResponseBody
	public Object getMbrPymtMeth(@Valid final MbrPymtMethExDto params) throws Exception {
		final MbrPymtMethExDto mapData = this.mbrPymtMethService.getMbrPymtMeth(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param MbrPymtMethExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/mbrPymtMeth/insertMbrPymtMeth.do")
	@ResponseBody
	public Map<String,Object> insertMbrPymtMeth(@Valid final MbrPymtMethExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.mbrPymtMethService.insertMbrPymtMeth(params);
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
	* @param MbrPymtMethExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/mbrPymtMeth/updateMbrPymtMeth.do")
	@ResponseBody
	public Map<String,Object> updateMbrPymtMeth(@Valid final MbrPymtMethExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.mbrPymtMethService.updateMbrPymtMeth(params);
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
	* @param MbrPymtMethExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/mbrPymtMeth/deleteMbrPymtMeth.do")
	@ResponseBody
	public Map<String,Object> deleteMbrPymtMeth(@Valid final MbrPymtMethExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.mbrPymtMethService.deleteMbrPymtMeth(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/mbrPymtMeth/getMbrPymtMethExcelDown.do")
	@ResponseBody
	public Object getMbrPymtMethExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		MbrPymtMethExDto mbrPymtMethExDto = new MbrPymtMethExDto();
		mbrPymtMethExDto = (MbrPymtMethExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), mbrPymtMethExDto);
		final List<MbrPymtMethExDto> listData = this.mbrPymtMethService.getMbrPymtMethList(mbrPymtMethExDto);
		
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
