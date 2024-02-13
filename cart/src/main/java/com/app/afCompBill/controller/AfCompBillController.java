package com.app.afCompBill.controller;

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

import com.app.afCompBill.model.AfCompBillExDto;
import com.app.afCompBill.service.AfCompBillService;
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
public class AfCompBillController {

	@Autowired
	private AfCompBillService afCompBillService;

	@RequestMapping("app/afCompBill/afCompBillList.do")
	public String afCompBillList() throws Exception {
		return "app/afCompBill/afCompBillList";
	}

	@RequestMapping("app/popup/afCompBill/afCompBillPop.do")
	public String afCompBillPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/afCompBill/afCompBillPop";
	}

	@RequestMapping("app/popup/afCompBill/afCompBillInfoPop.do")
	public String afCompBillInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/afCompBill/afCompBillInfoPop";
	}

	@RequestMapping("app/popup/afCompBill/afCompBillSearchPop.do")
	public String afCompBillSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/afCompBill/afCompBillSearchPop";
	}

	/**
	*  List Page
	*
	* @param AfCompBillExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/afCompBill/getAfCompBillPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getAfCompBillPageList(HttpServletRequest request, @Valid final AfCompBillExDto params) throws Exception {
		final int totalRows = this.afCompBillService.getAfCompBillListCount(params);
		final List<AfCompBillExDto> afCompBillList = this.afCompBillService.getAfCompBillList(params);
		
		Map<String,Object> mapData = (new ResponseList(afCompBillList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param AfCompBillExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/afCompBill/getAfCompBillList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<AfCompBillExDto> getAfCompBillList(@Valid final AfCompBillExDto params) throws Exception {
		final List<AfCompBillExDto> listData = this.afCompBillService.getAfCompBillList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param AfCompBillExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/afCompBill/getAfCompBill.do")
	@ResponseBody
	public Object getAfCompBill(@Valid final AfCompBillExDto params) throws Exception {
		final AfCompBillExDto mapData = this.afCompBillService.getAfCompBill(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param AfCompBillExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/afCompBill/insertAfCompBill.do")
	@ResponseBody
	public Map<String,Object> insertAfCompBill(@Valid final AfCompBillExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.afCompBillService.insertAfCompBill(params);
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
	* @param AfCompBillExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/afCompBill/updateAfCompBill.do")
	@ResponseBody
	public Map<String,Object> updateAfCompBill(@Valid final AfCompBillExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.afCompBillService.updateAfCompBill(params);
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
	* @param AfCompBillExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/afCompBill/deleteAfCompBill.do")
	@ResponseBody
	public Map<String,Object> deleteAfCompBill(@Valid final AfCompBillExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.afCompBillService.deleteAfCompBill(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/afCompBill/getAfCompBillExcelDown.do")
	@ResponseBody
	public Object getAfCompBillExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		AfCompBillExDto afCompBillExDto = new AfCompBillExDto();
		afCompBillExDto = (AfCompBillExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), afCompBillExDto);
		final List<AfCompBillExDto> listData = this.afCompBillService.getAfCompBillList(afCompBillExDto);
		
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
