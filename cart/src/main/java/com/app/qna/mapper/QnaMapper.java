package com.app.qna.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.qna.model.QnaExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.qna.mapper
* QnaMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("qnaMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class QnaMapper  extends DaoBaseMapper {
//	public class QnaMapper  extends DaoBaseMapper {

	public int getQnaListCount( QnaExDto qnaExDto ) throws Exception {
		return (Integer)selectByPk("qnaMapper.getQnaListCount", qnaExDto);
	}
	
	public List<QnaExDto> getQnaList( QnaExDto qnaExDto ) throws Exception {
		return (List<QnaExDto>)list("qnaMapper.getQnaList", qnaExDto);
	}
	
	public QnaExDto getQna( QnaExDto qnaExDto ) throws Exception {
		return (QnaExDto) selectByPk("qnaMapper.getQna", qnaExDto);
	}

	public void insertQna( QnaExDto qnaExDto ) throws Exception {
		insert("qnaMapper.insertQna", qnaExDto);
	}
	
	public void updateQna( QnaExDto qnaExDto ) throws Exception {
		update("qnaMapper.updateQna", qnaExDto);
	}

	public void deleteQna( QnaExDto qnaExDto ) throws Exception {
		delete("qnaMapper.deleteQna", qnaExDto);
	}

}
