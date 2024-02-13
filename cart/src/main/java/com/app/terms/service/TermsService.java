package com.app.terms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.terms.mapper.TermsMapper;
import com.app.terms.model.TermsExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TermsService {

	@Autowired
	private TermsMapper termsMapper;

	public int getTermsListCount(TermsExDto termsExDto) throws Exception {
		return termsMapper.getTermsListCount(termsExDto);
	}

	public List<TermsExDto> getTermsList(TermsExDto termsExDto) throws Exception {
		return termsMapper.getTermsList(termsExDto);
	}

	public TermsExDto getTerms(TermsExDto termsExDto) throws Exception {
		return termsMapper.getTerms(termsExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertTerms(TermsExDto termsExDto) throws Exception {
		termsMapper.insertTerms(termsExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateTerms(TermsExDto termsExDto) throws Exception {
		termsMapper.updateTerms(termsExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteTerms(TermsExDto termsExDto) throws Exception {
		termsMapper.deleteTerms(termsExDto);
	}
}
