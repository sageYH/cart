package com.app.cardBill.controller;

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

import com.app.cardBill.model.CardBillExDto;
import com.app.cardBill.service.CardBillService;
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
public class CardBillController {

	@Autowired
	private CardBillService cardBillService;

	@RequestMapping("app/cardBill/cardBillList.do")
	public String cardBillList() throws Exception {
		return "app/cardBill/cardBillList";
	}

	@RequestMapping("app/popup/cardBill/cardBillPop.do")
	public String cardBillPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/cardBill/cardBillPop";
	}

	@RequestMapping("app/popup/cardBill/cardBillInfoPop.do")
	public String cardBillInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/cardBill/cardBillInfoPop";
	}

	@RequestMapping("app/popup/cardBill/cardBillSearchPop.do")
	public String cardBillSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/cardBill/cardBillSearchPop";
	}

	/**
	*  List Page
	*
	* @param CardBillExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/cardBill/getCardBillPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCardBillPageList(HttpServletRequest request, @Valid final CardBillExDto params) throws Exception {
		final int totalRows = this.cardBillService.getCardBillListCount(params);
		final List<CardBillExDto> cardBillList = this.cardBillService.getCardBillList(params);
		
		Map<String,Object> mapData = (new ResponseList(cardBillList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param CardBillExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/cardBill/getCardBillList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CardBillExDto> getCardBillList(@Valid final CardBillExDto params) throws Exception {
		final List<CardBillExDto> listData = this.cardBillService.getCardBillList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param CardBillExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/cardBill/getCardBill.do")
	@ResponseBody
	public Object getCardBill(@Valid final CardBillExDto params) throws Exception {
		final CardBillExDto mapData = this.cardBillService.getCardBill(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param CardBillExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/cardBill/insertCardBill.do")
	@ResponseBody
	public Map<String,Object> insertCardBill(@Valid final CardBillExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.cardBillService.insertCardBill(params);
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
	* @param CardBillExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/cardBill/updateCardBill.do")
	@ResponseBody
	public Map<String,Object> updateCardBill(@Valid final CardBillExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.cardBillService.updateCardBill(params);
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
	* @param CardBillExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/cardBill/deleteCardBill.do")
	@ResponseBody
	public Map<String,Object> deleteCardBill(@Valid final CardBillExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.cardBillService.deleteCardBill(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/cardBill/getCardBillExcelDown.do")
	@ResponseBody
	public Object getCardBillExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CardBillExDto cardBillExDto = new CardBillExDto();
		cardBillExDto = (CardBillExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), cardBillExDto);
		final List<CardBillExDto> listData = this.cardBillService.getCardBillList(cardBillExDto);
		
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
