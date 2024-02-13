package com.app.board.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.board.model.BoardExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.board.mapper
* BoardMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("boardMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class BoardMapper  extends DaoBaseMapper {
//	public class BoardMapper  extends DaoBaseMapper {

	public int getBoardListCount( BoardExDto boardExDto ) throws Exception {
		return (Integer)selectByPk("boardMapper.getBoardListCount", boardExDto);
	}
	
	public List<BoardExDto> getBoardList( BoardExDto boardExDto ) throws Exception {
		return (List<BoardExDto>)list("boardMapper.getBoardList", boardExDto);
	}
	
	public BoardExDto getBoard( BoardExDto boardExDto ) throws Exception {
		return (BoardExDto) selectByPk("boardMapper.getBoard", boardExDto);
	}

	public void insertBoard( BoardExDto boardExDto ) throws Exception {
		insert("boardMapper.insertBoard", boardExDto);
	}
	
	public void updateBoard( BoardExDto boardExDto ) throws Exception {
		update("boardMapper.updateBoard", boardExDto);
	}

	public void deleteBoard( BoardExDto boardExDto ) throws Exception {
		delete("boardMapper.deleteBoard", boardExDto);
	}

}
