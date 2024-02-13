package com.app.mbrLvlBaseLang.controller;

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

import com.app.mbrLvlBaseLang.model.MbrLvlBaseLangExDto;
import com.app.mbrLvlBaseLang.service.MbrLvlBaseLangService;
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
public class MbrLvlBaseLangController {

	@Autowired
	private MbrLvlBaseLangService mbrLvlBaseLangService;

	@RequestMapping("app/mbrLvlBaseLang/mbrLvlBaseLangList.do")
	public String mbrLvlBaseLangList() throws Exception {
		return "app/mbrLvlBaseLang/mbrLvlBaseLangList";
	}

	@RequestMapping("app/popup/mbrLvlBaseLang/mbrLvlBaseLangPop.do")
	public String mbrLvlBaseLangPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/mbrLvlBaseLang/mbrLvlBaseLangPop";
	}

	@RequestMapping("app/popup/mbrLvlBaseLang/mbrLvlBaseLangInfoPop.do")
	public String mbrLvlBaseLangInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/mbrLvlBaseLang/mbrLvlBaseLangInfoPop";
	}

	@RequestMapping("app/popup/mbrLvlBaseLang/mbrLvlBaseLangSearchPop.do")
	public String mbrLvlBaseLangSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/mbrLvlBaseLang/mbrLvlBaseLangSearchPop";
	}

	/**
	*  List Page
	*
	* @param MbrLvlBaseLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/mbrLvlBaseLang/getMbrLvlBaseLangPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getMbrLvlBaseLangPageList(HttpServletRequest request, @Valid final MbrLvlBaseLangExDto params) throws Exception {
		final int totalRows = this.mbrLvlBaseLangService.getMbrLvlBaseLangListCount(params);
		final List<MbrLvlBaseLangExDto> mbrLvlBaseLangList = this.mbrLvlBaseLangService.getMbrLvlBaseLangList(params);
		
		Map<String,Object> mapData = (new ResponseList(mbrLvlBaseLangList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param MbrLvlBaseLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/mbrLvlBaseLang/getMbrLvlBaseLangList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<MbrLvlBaseLangExDto> getMbrLvlBaseLangList(@Valid final MbrLvlBaseLangExDto params) throws Exception {
		final List<MbrLvlBaseLangExDto> listData = this.mbrLvlBaseLangService.getMbrLvlBaseLangList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param MbrLvlBaseLangExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/mbrLvlBaseLang/getMbrLvlBaseLang.do")
	@ResponseBody
	public Object getMbrLvlBaseLang(@Valid final MbrLvlBaseLangExDto params) throws Exception {
		final MbrLvlBaseLangExDto mapData = this.mbrLvlBaseLangService.getMbrLvlBaseLang(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param MbrLvlBaseLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/mbrLvlBaseLang/insertMbrLvlBaseLang.do")
	@ResponseBody
	public Map<String,Object> insertMbrLvlBaseLang(@Valid final MbrLvlBaseLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.mbrLvlBaseLangService.insertMbrLvlBaseLang(params);
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
	* @param MbrLvlBaseLangExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/mbrLvlBaseLang/updateMbrLvlBaseLang.do")
	@ResponseBody
	public Map<String,Object> updateMbrLvlBaseLang(@Valid final MbrLvlBaseLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.mbrLvlBaseLangService.updateMbrLvlBaseLang(params);
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
	* @param MbrLvlBaseLangExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/mbrLvlBaseLang/deleteMbrLvlBaseLang.do")
	@ResponseBody
	public Map<String,Object> deleteMbrLvlBaseLang(@Valid final MbrLvlBaseLangExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.mbrLvlBaseLangService.deleteMbrLvlBaseLang(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/mbrLvlBaseLang/getMbrLvlBaseLangExcelDown.do")
	@ResponseBody
	public Object getMbrLvlBaseLangExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		MbrLvlBaseLangExDto mbrLvlBaseLangExDto = new MbrLvlBaseLangExDto();
		mbrLvlBaseLangExDto = (MbrLvlBaseLangExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), mbrLvlBaseLangExDto);
		final List<MbrLvlBaseLangExDto> listData = this.mbrLvlBaseLangService.getMbrLvlBaseLangList(mbrLvlBaseLangExDto);
		
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
