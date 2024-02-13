package com.app.mbrLoginHist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.mbrLoginHist.mapper.MbrLoginHistMapper;
import com.app.mbrLoginHist.model.MbrLoginHistExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MbrLoginHistService {

	@Autowired
	private MbrLoginHistMapper mbrLoginHistMapper;

	public int getMbrLoginHistListCount(MbrLoginHistExDto mbrLoginHistExDto) throws Exception {
		return mbrLoginHistMapper.getMbrLoginHistListCount(mbrLoginHistExDto);
	}

	public List<MbrLoginHistExDto> getMbrLoginHistList(MbrLoginHistExDto mbrLoginHistExDto) throws Exception {
		return mbrLoginHistMapper.getMbrLoginHistList(mbrLoginHistExDto);
	}

	public MbrLoginHistExDto getMbrLoginHist(MbrLoginHistExDto mbrLoginHistExDto) throws Exception {
		return mbrLoginHistMapper.getMbrLoginHist(mbrLoginHistExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertMbrLoginHist(MbrLoginHistExDto mbrLoginHistExDto) throws Exception {
		mbrLoginHistMapper.insertMbrLoginHist(mbrLoginHistExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateMbrLoginHist(MbrLoginHistExDto mbrLoginHistExDto) throws Exception {
		mbrLoginHistMapper.updateMbrLoginHist(mbrLoginHistExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteMbrLoginHist(MbrLoginHistExDto mbrLoginHistExDto) throws Exception {
		mbrLoginHistMapper.deleteMbrLoginHist(mbrLoginHistExDto);
	}
}
