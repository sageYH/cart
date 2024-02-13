package com.app.termsDetsLang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.termsDetsLang.mapper.TermsDetsLangMapper;
import com.app.termsDetsLang.model.TermsDetsLangExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TermsDetsLangService {

	@Autowired
	private TermsDetsLangMapper termsDetsLangMapper;

	public int getTermsDetsLangListCount(TermsDetsLangExDto termsDetsLangExDto) throws Exception {
		return termsDetsLangMapper.getTermsDetsLangListCount(termsDetsLangExDto);
	}

	public List<TermsDetsLangExDto> getTermsDetsLangList(TermsDetsLangExDto termsDetsLangExDto) throws Exception {
		return termsDetsLangMapper.getTermsDetsLangList(termsDetsLangExDto);
	}

	public TermsDetsLangExDto getTermsDetsLang(TermsDetsLangExDto termsDetsLangExDto) throws Exception {
		return termsDetsLangMapper.getTermsDetsLang(termsDetsLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertTermsDetsLang(TermsDetsLangExDto termsDetsLangExDto) throws Exception {
		termsDetsLangMapper.insertTermsDetsLang(termsDetsLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateTermsDetsLang(TermsDetsLangExDto termsDetsLangExDto) throws Exception {
		termsDetsLangMapper.updateTermsDetsLang(termsDetsLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteTermsDetsLang(TermsDetsLangExDto termsDetsLangExDto) throws Exception {
		termsDetsLangMapper.deleteTermsDetsLang(termsDetsLangExDto);
	}
}
