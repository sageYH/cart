package com.app.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.qna.mapper.QnaMapper;
import com.app.qna.model.QnaExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class QnaService {

	@Autowired
	private QnaMapper qnaMapper;

	public int getQnaListCount(QnaExDto qnaExDto) throws Exception {
		return qnaMapper.getQnaListCount(qnaExDto);
	}

	public List<QnaExDto> getQnaList(QnaExDto qnaExDto) throws Exception {
		return qnaMapper.getQnaList(qnaExDto);
	}

	public QnaExDto getQna(QnaExDto qnaExDto) throws Exception {
		return qnaMapper.getQna(qnaExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertQna(QnaExDto qnaExDto) throws Exception {
		qnaMapper.insertQna(qnaExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateQna(QnaExDto qnaExDto) throws Exception {
		qnaMapper.updateQna(qnaExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteQna(QnaExDto qnaExDto) throws Exception {
		qnaMapper.deleteQna(qnaExDto);
	}
}
