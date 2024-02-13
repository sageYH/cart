package com.app.mbrLoginHist.controller;

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

import com.app.mbrLoginHist.model.MbrLoginHistExDto;
import com.app.mbrLoginHist.service.MbrLoginHistService;
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
public class MbrLoginHistController {

	@Autowired
	private MbrLoginHistService mbrLoginHistService;

	@RequestMapping("app/mbrLoginHist/mbrLoginHistList.do")
	public String mbrLoginHistList() throws Exception {
		return "app/mbrLoginHist/mbrLoginHistList";
	}

	@RequestMapping("app/popup/mbrLoginHist/mbrLoginHistPop.do")
	public String mbrLoginHistPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/mbrLoginHist/mbrLoginHistPop";
	}

	@RequestMapping("app/popup/mbrLoginHist/mbrLoginHistInfoPop.do")
	public String mbrLoginHistInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/mbrLoginHist/mbrLoginHistInfoPop";
	}

	@RequestMapping("app/popup/mbrLoginHist/mbrLoginHistSearchPop.do")
	public String mbrLoginHistSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/mbrLoginHist/mbrLoginHistSearchPop";
	}

	/**
	*  List Page
	*
	* @param MbrLoginHistExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/mbrLoginHist/getMbrLoginHistPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getMbrLoginHistPageList(HttpServletRequest request, @Valid final MbrLoginHistExDto params) throws Exception {
		final int totalRows = this.mbrLoginHistService.getMbrLoginHistListCount(params);
		final List<MbrLoginHistExDto> mbrLoginHistList = this.mbrLoginHistService.getMbrLoginHistList(params);
		
		Map<String,Object> mapData = (new ResponseList(mbrLoginHistList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param MbrLoginHistExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/mbrLoginHist/getMbrLoginHistList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<MbrLoginHistExDto> getMbrLoginHistList(@Valid final MbrLoginHistExDto params) throws Exception {
		final List<MbrLoginHistExDto> listData = this.mbrLoginHistService.getMbrLoginHistList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param MbrLoginHistExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/mbrLoginHist/getMbrLoginHist.do")
	@ResponseBody
	public Object getMbrLoginHist(@Valid final MbrLoginHistExDto params) throws Exception {
		final MbrLoginHistExDto mapData = this.mbrLoginHistService.getMbrLoginHist(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param MbrLoginHistExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/mbrLoginHist/insertMbrLoginHist.do")
	@ResponseBody
	public Map<String,Object> insertMbrLoginHist(@Valid final MbrLoginHistExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.mbrLoginHistService.insertMbrLoginHist(params);
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
	* @param MbrLoginHistExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/mbrLoginHist/updateMbrLoginHist.do")
	@ResponseBody
	public Map<String,Object> updateMbrLoginHist(@Valid final MbrLoginHistExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.mbrLoginHistService.updateMbrLoginHist(params);
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
	* @param MbrLoginHistExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/mbrLoginHist/deleteMbrLoginHist.do")
	@ResponseBody
	public Map<String,Object> deleteMbrLoginHist(@Valid final MbrLoginHistExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.mbrLoginHistService.deleteMbrLoginHist(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/mbrLoginHist/getMbrLoginHistExcelDown.do")
	@ResponseBody
	public Object getMbrLoginHistExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		MbrLoginHistExDto mbrLoginHistExDto = new MbrLoginHistExDto();
		mbrLoginHistExDto = (MbrLoginHistExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), mbrLoginHistExDto);
		final List<MbrLoginHistExDto> listData = this.mbrLoginHistService.getMbrLoginHistList(mbrLoginHistExDto);
		
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
