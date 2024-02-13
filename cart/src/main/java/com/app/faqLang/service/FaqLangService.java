package com.app.faqLang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.faqLang.mapper.FaqLangMapper;
import com.app.faqLang.model.FaqLangExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FaqLangService {

	@Autowired
	private FaqLangMapper faqLangMapper;

	public int getFaqLangListCount(FaqLangExDto faqLangExDto) throws Exception {
		return faqLangMapper.getFaqLangListCount(faqLangExDto);
	}

	public List<FaqLangExDto> getFaqLangList(FaqLangExDto faqLangExDto) throws Exception {
		return faqLangMapper.getFaqLangList(faqLangExDto);
	}

	public FaqLangExDto getFaqLang(FaqLangExDto faqLangExDto) throws Exception {
		return faqLangMapper.getFaqLang(faqLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertFaqLang(FaqLangExDto faqLangExDto) throws Exception {
		faqLangMapper.insertFaqLang(faqLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateFaqLang(FaqLangExDto faqLangExDto) throws Exception {
		faqLangMapper.updateFaqLang(faqLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteFaqLang(FaqLangExDto faqLangExDto) throws Exception {
		faqLangMapper.deleteFaqLang(faqLangExDto);
	}
}
