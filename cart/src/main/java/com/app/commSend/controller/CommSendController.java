package com.app.commSend.controller;

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

import com.app.commSend.model.CommSendExDto;
import com.app.commSend.service.CommSendService;
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
public class CommSendController {

	@Autowired
	private CommSendService commSendService;

	@RequestMapping("app/commSend/commSendList.do")
	public String commSendList() throws Exception {
		return "app/commSend/commSendList";
	}

	@RequestMapping("app/popup/commSend/commSendPop.do")
	public String commSendPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/commSend/commSendPop";
	}

	@RequestMapping("app/popup/commSend/commSendInfoPop.do")
	public String commSendInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/commSend/commSendInfoPop";
	}

	@RequestMapping("app/popup/commSend/commSendSearchPop.do")
	public String commSendSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/commSend/commSendSearchPop";
	}

	/**
	*  List Page
	*
	* @param CommSendExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/commSend/getCommSendPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCommSendPageList(HttpServletRequest request, @Valid final CommSendExDto params) throws Exception {
		final int totalRows = this.commSendService.getCommSendListCount(params);
		final List<CommSendExDto> commSendList = this.commSendService.getCommSendList(params);
		
		Map<String,Object> mapData = (new ResponseList(commSendList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param CommSendExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/commSend/getCommSendList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CommSendExDto> getCommSendList(@Valid final CommSendExDto params) throws Exception {
		final List<CommSendExDto> listData = this.commSendService.getCommSendList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param CommSendExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/commSend/getCommSend.do")
	@ResponseBody
	public Object getCommSend(@Valid final CommSendExDto params) throws Exception {
		final CommSendExDto mapData = this.commSendService.getCommSend(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param CommSendExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/commSend/insertCommSend.do")
	@ResponseBody
	public Map<String,Object> insertCommSend(@Valid final CommSendExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.commSendService.insertCommSend(params);
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
	* @param CommSendExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/commSend/updateCommSend.do")
	@ResponseBody
	public Map<String,Object> updateCommSend(@Valid final CommSendExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.commSendService.updateCommSend(params);
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
	* @param CommSendExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/commSend/deleteCommSend.do")
	@ResponseBody
	public Map<String,Object> deleteCommSend(@Valid final CommSendExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.commSendService.deleteCommSend(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/commSend/getCommSendExcelDown.do")
	@ResponseBody
	public Object getCommSendExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CommSendExDto commSendExDto = new CommSendExDto();
		commSendExDto = (CommSendExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), commSendExDto);
		final List<CommSendExDto> listData = this.commSendService.getCommSendList(commSendExDto);
		
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
