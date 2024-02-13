package com.app.memberCert.controller;

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

import com.app.memberCert.model.MemberCertExDto;
import com.app.memberCert.service.MemberCertService;
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
public class MemberCertController {

	@Autowired
	private MemberCertService memberCertService;

	@RequestMapping("app/memberCert/memberCertList.do")
	public String memberCertList() throws Exception {
		return "app/memberCert/memberCertList";
	}

	@RequestMapping("app/popup/memberCert/memberCertPop.do")
	public String memberCertPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/memberCert/memberCertPop";
	}

	@RequestMapping("app/popup/memberCert/memberCertInfoPop.do")
	public String memberCertInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/memberCert/memberCertInfoPop";
	}

	@RequestMapping("app/popup/memberCert/memberCertSearchPop.do")
	public String memberCertSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/memberCert/memberCertSearchPop";
	}

	/**
	*  List Page
	*
	* @param MemberCertExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/memberCert/getMemberCertPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getMemberCertPageList(HttpServletRequest request, @Valid final MemberCertExDto params) throws Exception {
		final int totalRows = this.memberCertService.getMemberCertListCount(params);
		final List<MemberCertExDto> memberCertList = this.memberCertService.getMemberCertList(params);
		
		Map<String,Object> mapData = (new ResponseList(memberCertList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param MemberCertExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/memberCert/getMemberCertList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<MemberCertExDto> getMemberCertList(@Valid final MemberCertExDto params) throws Exception {
		final List<MemberCertExDto> listData = this.memberCertService.getMemberCertList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param MemberCertExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/memberCert/getMemberCert.do")
	@ResponseBody
	public Object getMemberCert(@Valid final MemberCertExDto params) throws Exception {
		final MemberCertExDto mapData = this.memberCertService.getMemberCert(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param MemberCertExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/memberCert/insertMemberCert.do")
	@ResponseBody
	public Map<String,Object> insertMemberCert(@Valid final MemberCertExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.memberCertService.insertMemberCert(params);
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
	* @param MemberCertExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/memberCert/updateMemberCert.do")
	@ResponseBody
	public Map<String,Object> updateMemberCert(@Valid final MemberCertExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.memberCertService.updateMemberCert(params);
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
	* @param MemberCertExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/memberCert/deleteMemberCert.do")
	@ResponseBody
	public Map<String,Object> deleteMemberCert(@Valid final MemberCertExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.memberCertService.deleteMemberCert(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/memberCert/getMemberCertExcelDown.do")
	@ResponseBody
	public Object getMemberCertExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		MemberCertExDto memberCertExDto = new MemberCertExDto();
		memberCertExDto = (MemberCertExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), memberCertExDto);
		final List<MemberCertExDto> listData = this.memberCertService.getMemberCertList(memberCertExDto);
		
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
