package com.app.termsDets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.termsDets.mapper.TermsDetsMapper;
import com.app.termsDets.model.TermsDetsExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TermsDetsService {

	@Autowired
	private TermsDetsMapper termsDetsMapper;

	public int getTermsDetsListCount(TermsDetsExDto termsDetsExDto) throws Exception {
		return termsDetsMapper.getTermsDetsListCount(termsDetsExDto);
	}

	public List<TermsDetsExDto> getTermsDetsList(TermsDetsExDto termsDetsExDto) throws Exception {
		return termsDetsMapper.getTermsDetsList(termsDetsExDto);
	}

	public TermsDetsExDto getTermsDets(TermsDetsExDto termsDetsExDto) throws Exception {
		return termsDetsMapper.getTermsDets(termsDetsExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertTermsDets(TermsDetsExDto termsDetsExDto) throws Exception {
		termsDetsMapper.insertTermsDets(termsDetsExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateTermsDets(TermsDetsExDto termsDetsExDto) throws Exception {
		termsDetsMapper.updateTermsDets(termsDetsExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteTermsDets(TermsDetsExDto termsDetsExDto) throws Exception {
		termsDetsMapper.deleteTermsDets(termsDetsExDto);
	}
}
