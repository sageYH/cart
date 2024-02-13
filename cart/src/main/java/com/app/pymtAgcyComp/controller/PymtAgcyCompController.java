package com.app.pymtAgcyComp.controller;

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

import com.app.pymtAgcyComp.model.PymtAgcyCompExDto;
import com.app.pymtAgcyComp.service.PymtAgcyCompService;
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
public class PymtAgcyCompController {

	@Autowired
	private PymtAgcyCompService pymtAgcyCompService;

	@RequestMapping("app/pymtAgcyComp/pymtAgcyCompList.do")
	public String pymtAgcyCompList() throws Exception {
		return "app/pymtAgcyComp/pymtAgcyCompList";
	}

	@RequestMapping("app/popup/pymtAgcyComp/pymtAgcyCompPop.do")
	public String pymtAgcyCompPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/pymtAgcyComp/pymtAgcyCompPop";
	}

	@RequestMapping("app/popup/pymtAgcyComp/pymtAgcyCompInfoPop.do")
	public String pymtAgcyCompInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/pymtAgcyComp/pymtAgcyCompInfoPop";
	}

	@RequestMapping("app/popup/pymtAgcyComp/pymtAgcyCompSearchPop.do")
	public String pymtAgcyCompSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/pymtAgcyComp/pymtAgcyCompSearchPop";
	}

	/**
	*  List Page
	*
	* @param PymtAgcyCompExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/pymtAgcyComp/getPymtAgcyCompPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getPymtAgcyCompPageList(HttpServletRequest request, @Valid final PymtAgcyCompExDto params) throws Exception {
		final int totalRows = this.pymtAgcyCompService.getPymtAgcyCompListCount(params);
		final List<PymtAgcyCompExDto> pymtAgcyCompList = this.pymtAgcyCompService.getPymtAgcyCompList(params);
		
		Map<String,Object> mapData = (new ResponseList(pymtAgcyCompList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param PymtAgcyCompExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/pymtAgcyComp/getPymtAgcyCompList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<PymtAgcyCompExDto> getPymtAgcyCompList(@Valid final PymtAgcyCompExDto params) throws Exception {
		final List<PymtAgcyCompExDto> listData = this.pymtAgcyCompService.getPymtAgcyCompList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param PymtAgcyCompExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/pymtAgcyComp/getPymtAgcyComp.do")
	@ResponseBody
	public Object getPymtAgcyComp(@Valid final PymtAgcyCompExDto params) throws Exception {
		final PymtAgcyCompExDto mapData = this.pymtAgcyCompService.getPymtAgcyComp(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param PymtAgcyCompExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/pymtAgcyComp/insertPymtAgcyComp.do")
	@ResponseBody
	public Map<String,Object> insertPymtAgcyComp(@Valid final PymtAgcyCompExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.pymtAgcyCompService.insertPymtAgcyComp(params);
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
	* @param PymtAgcyCompExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/pymtAgcyComp/updatePymtAgcyComp.do")
	@ResponseBody
	public Map<String,Object> updatePymtAgcyComp(@Valid final PymtAgcyCompExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.pymtAgcyCompService.updatePymtAgcyComp(params);
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
	* @param PymtAgcyCompExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/pymtAgcyComp/deletePymtAgcyComp.do")
	@ResponseBody
	public Map<String,Object> deletePymtAgcyComp(@Valid final PymtAgcyCompExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.pymtAgcyCompService.deletePymtAgcyComp(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/pymtAgcyComp/getPymtAgcyCompExcelDown.do")
	@ResponseBody
	public Object getPymtAgcyCompExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		PymtAgcyCompExDto pymtAgcyCompExDto = new PymtAgcyCompExDto();
		pymtAgcyCompExDto = (PymtAgcyCompExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), pymtAgcyCompExDto);
		final List<PymtAgcyCompExDto> listData = this.pymtAgcyCompService.getPymtAgcyCompList(pymtAgcyCompExDto);
		
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
