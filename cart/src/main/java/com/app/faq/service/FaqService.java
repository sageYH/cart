package com.app.faq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.faq.mapper.FaqMapper;
import com.app.faq.model.FaqExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FaqService {

	@Autowired
	private FaqMapper faqMapper;

	public int getFaqListCount(FaqExDto faqExDto) throws Exception {
		return faqMapper.getFaqListCount(faqExDto);
	}

	public List<FaqExDto> getFaqList(FaqExDto faqExDto) throws Exception {
		return faqMapper.getFaqList(faqExDto);
	}

	public FaqExDto getFaq(FaqExDto faqExDto) throws Exception {
		return faqMapper.getFaq(faqExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertFaq(FaqExDto faqExDto) throws Exception {
		faqMapper.insertFaq(faqExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateFaq(FaqExDto faqExDto) throws Exception {
		faqMapper.updateFaq(faqExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteFaq(FaqExDto faqExDto) throws Exception {
		faqMapper.deleteFaq(faqExDto);
	}
}
