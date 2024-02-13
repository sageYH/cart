package com.app.member.controller;

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

import com.app.member.model.MemberExDto;
import com.app.member.service.MemberService;
import com.base.ResponseList;
import com.base.ResponseMap;
import com.common.utils.ClassUtil;
import com.common.utils.Excel;
import com.common.utils.MapUtil;

import lombok.RequiredArgsConstructor;

/**
* 멤버 관리
*
* @author
*
*/

@Controller
@Lazy
@RequiredArgsConstructor
public class MemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("app/member/memberList.do")
	public String memberList() throws Exception {
		return "app/member/memberList";
	}

	@RequestMapping("app/popup/member/memberPop.do")
	public String memberPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/member/memberPop";
	}

	@RequestMapping("app/popup/member/memberInfoPop.do")
	public String memberInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/member/memberInfoPop";
	}

	@RequestMapping("app/popup/member/memberSearchPop.do")
	public String memberSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/member/memberSearchPop";
	}

	/**
	* 멤버 List Page
	*
	* @param MemberExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/member/getMemberPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getMemberPageList(HttpServletRequest request, @Valid final MemberExDto params) throws Exception {
		final int totalRows = this.memberService.getMemberListCount(params);
		final List<MemberExDto> memberList = this.memberService.getMemberList(params);
		
		Map<String,Object> mapData = (new ResponseList(memberList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	* 멤버 List Inquiry
	*
	* @param MemberExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/member/getMemberList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<MemberExDto> getMemberList(@Valid final MemberExDto params) throws Exception {
		final List<MemberExDto> listData = this.memberService.getMemberList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요 멤버 detail infomation Inquiry
	* </pre>
	*
	* @param MemberExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/member/getMember.do")
	@ResponseBody
	public Object getMember(@Valid final MemberExDto params) throws Exception {
		final MemberExDto mapData = this.memberService.getMember(params);
		return mapData;
	}

	/**
	* 멤버 insert
	*
	* @param MemberExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/member/insertMember.do")
	@ResponseBody
	public Map<String,Object> insertMember(@Valid final MemberExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.memberService.insertMember(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 멤버 Update
	*
	* @param MemberExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/member/updateMember.do")
	@ResponseBody
	public Map<String,Object> updateMember(@Valid final MemberExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.memberService.updateMember(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	/**
	* 멤버 delete
	*
	* @param MemberExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/member/deleteMember.do")
	@ResponseBody
	public Map<String,Object> deleteMember(@Valid final MemberExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.memberService.deleteMember(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/member/getMemberExcelDown.do")
	@ResponseBody
	public Object getMemberExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		MemberExDto memberExDto = new MemberExDto();
		memberExDto = (MemberExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), memberExDto);
		final List<MemberExDto> listData = this.memberService.getMemberList(memberExDto);
		
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
