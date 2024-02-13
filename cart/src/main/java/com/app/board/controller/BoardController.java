package com.app.board.controller;

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

import com.app.board.model.BoardExDto;
import com.app.board.service.BoardService;
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
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("app/board/boardList.do")
	public String boardList() throws Exception {
		return "app/board/boardList";
	}

	@RequestMapping("app/popup/board/boardPop.do")
	public String boardPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/board/boardPop";
	}

	@RequestMapping("app/popup/board/boardInfoPop.do")
	public String boardInfoPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/board/boardInfoPop";
	}

	@RequestMapping("app/popup/board/boardSearchPop.do")
	public String boardSearchPop(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/popup/board/boardSearchPop";
	}

	/**
	*  List Page
	*
	* @param BoardExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/board/getBoardPageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getBoardPageList(HttpServletRequest request, @Valid final BoardExDto params) throws Exception {
		final int totalRows = this.boardService.getBoardListCount(params);
		final List<BoardExDto> boardList = this.boardService.getBoardList(params);
		
		Map<String,Object> mapData = (new ResponseList(boardList, totalRows)).getResultMap();
		
		return mapData;
	}

	/**
	*  List Inquiry
	*
	* @param BoardExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "app/board/getBoardList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<BoardExDto> getBoardList(@Valid final BoardExDto params) throws Exception {
		final List<BoardExDto> listData = this.boardService.getBoardList(params);
		
		return listData;
	}

	/**
	* <pre>
	* 1. 개요  detail infomation Inquiry
	* </pre>
	*
	* @param BoardExDto
	* @return : Object
	* @throws Exception
	* @date : 
	* @author : 
	*/
	@RequestMapping("app/board/getBoard.do")
	@ResponseBody
	public Object getBoard(@Valid final BoardExDto params) throws Exception {
		final BoardExDto mapData = this.boardService.getBoard(params);
		return mapData;
	}

	/**
	*  insert
	*
	* @param BoardExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/board/insertBoard.do")
	@ResponseBody
	public Map<String,Object> insertBoard(@Valid final BoardExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.boardService.insertBoard(params);
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
	* @param BoardExDto
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/board/updateBoard.do")
	@ResponseBody
	public Map<String,Object> updateBoard(@Valid final BoardExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.boardService.updateBoard(params);
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
	* @param BoardExDto
	* @return ResponseEntity<String>
	* @throws Exception
	*/
	@RequestMapping(method = RequestMethod.POST, value = "app/board/deleteBoard.do")
	@ResponseBody
	public Map<String,Object> deleteBoard(@Valid final BoardExDto params) throws Exception {
		ResponseMap responseMap = new ResponseMap();
		try {
			this.boardService.deleteBoard(params);
			responseMap = (new ResponseMap("Y",""));
		}
		catch(Exception e) {
			responseMap = (new ResponseMap("N","fail"));
		}
		Map<String,Object> mapData = responseMap.getResultMap();
		
		return mapData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/board/getBoardExcelDown.do")
	@ResponseBody
	public Object getBoardExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		BoardExDto boardExDto = new BoardExDto();
		boardExDto = (BoardExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), boardExDto);
		final List<BoardExDto> listData = this.boardService.getBoardList(boardExDto);
		
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
