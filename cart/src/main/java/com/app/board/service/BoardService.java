package com.app.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.board.mapper.BoardMapper;
import com.app.board.model.BoardExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;

	public int getBoardListCount(BoardExDto boardExDto) throws Exception {
		return boardMapper.getBoardListCount(boardExDto);
	}

	public List<BoardExDto> getBoardList(BoardExDto boardExDto) throws Exception {
		return boardMapper.getBoardList(boardExDto);
	}

	public BoardExDto getBoard(BoardExDto boardExDto) throws Exception {
		return boardMapper.getBoard(boardExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertBoard(BoardExDto boardExDto) throws Exception {
		boardMapper.insertBoard(boardExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateBoard(BoardExDto boardExDto) throws Exception {
		boardMapper.updateBoard(boardExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteBoard(BoardExDto boardExDto) throws Exception {
		boardMapper.deleteBoard(boardExDto);
	}
}
