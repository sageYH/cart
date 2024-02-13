package com.app.prodCont.controller;

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

import com.app.prodCont.model.ProdContExDto;
import com.app.prodCont.service.ProdContService;
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
public class ProdContController {

	@Autowired
	private ProdContService prodContService;

	@RequestMapping("app/prodCont/prodContList.do")
	public String prodContList() throws Exception {
		return "app/prodCont/prodContList";
	}

	@RequestMapping("app/popup/prodCont/prodContPop.do")
	public String prodContPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodCont/prodContPop";
	}

	@RequestMapping("app/popup/prodCont/prodContInfoPop.do")
	public String prodContInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodCont/prodContInfoPop";
	}

	@RequestMapping("app/popup/prodCont/prodContSearchPop.do")
	public String prodContSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/prodCont/prodContSearchPop";
	}

	/**
	*  List Page
	*
	* @param ProdContExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/prodCont/getProdContPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getProdContPageList(HttpServletRequest request, @Valid final ProdContExDto params) throws Exception {
		final int totalRows = this.prodContService.getProdContListCount(params);
		final List<ProdContExDto> prodContList = this.prodContService.getProdContList(params);
		
		Map<String,Object> mapData = (new ResponseList(prodContList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param ProdContExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/prodCont/getProdContList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<ProdContExDto> getProdContList(@Valid final ProdContExDto params) throws Exception {
		final List<ProdContExDto> listData = this.prodContService.getProdContList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param ProdContExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/prodCont/getProdCont.do")
	@ResponseBody
	public Object getProdCont(@Valid final ProdContExDto params) throws Exception {
		final ProdContExDto mapData = this.prodContService.getProdCont(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param ProdContExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodCont/insertProdCont.do")
	@ResponseBody
	public Map<String,Object> insertProdCont(@Valid final ProdContExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodContService.insertProdCont(params);
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
	* @param ProdContExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodCont/updateProdCont.do")
	@ResponseBody
	public Map<String,Object> updateProdCont(@Valid final ProdContExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodContService.updateProdCont(params);
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
	* @param ProdContExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/prodCont/deleteProdCont.do")
	@ResponseBody
	public Map<String,Object> deleteProdCont(@Valid final ProdContExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.prodContService.deleteProdCont(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/prodCont/getProdContExcelDown.do")
	@ResponseBody
	public Object getProdContExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		ProdContExDto prodContExDto = new ProdContExDto();
		prodContExDto = (ProdContExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), prodContExDto);
		final List<ProdContExDto> listData = this.prodContService.getProdContList(prodContExDto);
		
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
