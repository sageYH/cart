package com.app.mbrTermsAcpt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.mbrTermsAcpt.mapper.MbrTermsAcptMapper;
import com.app.mbrTermsAcpt.model.MbrTermsAcptExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MbrTermsAcptService {

	@Autowired
	private MbrTermsAcptMapper mbrTermsAcptMapper;

	public int getMbrTermsAcptListCount(MbrTermsAcptExDto mbrTermsAcptExDto) throws Exception {
		return mbrTermsAcptMapper.getMbrTermsAcptListCount(mbrTermsAcptExDto);
	}

	public List<MbrTermsAcptExDto> getMbrTermsAcptList(MbrTermsAcptExDto mbrTermsAcptExDto) throws Exception {
		return mbrTermsAcptMapper.getMbrTermsAcptList(mbrTermsAcptExDto);
	}

	public MbrTermsAcptExDto getMbrTermsAcpt(MbrTermsAcptExDto mbrTermsAcptExDto) throws Exception {
		return mbrTermsAcptMapper.getMbrTermsAcpt(mbrTermsAcptExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertMbrTermsAcpt(MbrTermsAcptExDto mbrTermsAcptExDto) throws Exception {
		mbrTermsAcptMapper.insertMbrTermsAcpt(mbrTermsAcptExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateMbrTermsAcpt(MbrTermsAcptExDto mbrTermsAcptExDto) throws Exception {
		mbrTermsAcptMapper.updateMbrTermsAcpt(mbrTermsAcptExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteMbrTermsAcpt(MbrTermsAcptExDto mbrTermsAcptExDto) throws Exception {
		mbrTermsAcptMapper.deleteMbrTermsAcpt(mbrTermsAcptExDto);
	}
}
