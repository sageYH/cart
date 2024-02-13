package com.app.notice.controller;

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

import com.app.notice.model.NoticeExDto;
import com.app.notice.service.NoticeService;
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
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@RequestMapping("app/notice/noticeList.do")
	public String noticeList() throws Exception {
		return "app/notice/noticeList";
	}

	@RequestMapping("app/popup/notice/noticePop.do")
	public String noticePop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/notice/noticePop";
	}

	@RequestMapping("app/popup/notice/noticeInfoPop.do")
	public String noticeInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/notice/noticeInfoPop";
	}

	@RequestMapping("app/popup/notice/noticeSearchPop.do")
	public String noticeSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/notice/noticeSearchPop";
	}

	/**
	*  List Page
	*
	* @param NoticeExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/notice/getNoticePageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getNoticePageList(HttpServletRequest request, @Valid final NoticeExDto params) throws Exception {
		final int totalRows = this.noticeService.getNoticeListCount(params);
		final List<NoticeExDto> noticeList = this.noticeService.getNoticeList(params);
		
		Map<String,Object> mapData = (new ResponseList(noticeList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param NoticeExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/notice/getNoticeList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<NoticeExDto> getNoticeList(@Valid final NoticeExDto params) throws Exception {
		final List<NoticeExDto> listData = this.noticeService.getNoticeList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param NoticeExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/notice/getNotice.do")
	@ResponseBody
	public Object getNotice(@Valid final NoticeExDto params) throws Exception {
		final NoticeExDto mapData = this.noticeService.getNotice(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param NoticeExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/notice/insertNotice.do")
	@ResponseBody
	public Map<String,Object> insertNotice(@Valid final NoticeExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.noticeService.insertNotice(params);
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
	* @param NoticeExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/notice/updateNotice.do")
	@ResponseBody
	public Map<String,Object> updateNotice(@Valid final NoticeExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.noticeService.updateNotice(params);
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
	* @param NoticeExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/notice/deleteNotice.do")
	@ResponseBody
	public Map<String,Object> deleteNotice(@Valid final NoticeExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.noticeService.deleteNotice(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/notice/getNoticeExcelDown.do")
	@ResponseBody
	public Object getNoticeExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		NoticeExDto noticeExDto = new NoticeExDto();
		noticeExDto = (NoticeExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), noticeExDto);
		final List<NoticeExDto> listData = this.noticeService.getNoticeList(noticeExDto);
		
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
