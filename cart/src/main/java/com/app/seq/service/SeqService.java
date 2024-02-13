package com.app.seq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.seq.mapper.SeqMapper;
import com.app.seq.model.SeqExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SeqService {

	@Autowired
	private SeqMapper seqMapper;

	public int getSeqListCount(SeqExDto seqExDto) throws Exception {
		return seqMapper.getSeqListCount(seqExDto);
	}

	public List<SeqExDto> getSeqList(SeqExDto seqExDto) throws Exception {
		return seqMapper.getSeqList(seqExDto);
	}

	public SeqExDto getSeq(SeqExDto seqExDto) throws Exception {
		return seqMapper.getSeq(seqExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertSeq(SeqExDto seqExDto) throws Exception {
		seqMapper.insertSeq(seqExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateSeq(SeqExDto seqExDto) throws Exception {
		seqMapper.updateSeq(seqExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteSeq(SeqExDto seqExDto) throws Exception {
		seqMapper.deleteSeq(seqExDto);
	}
}
