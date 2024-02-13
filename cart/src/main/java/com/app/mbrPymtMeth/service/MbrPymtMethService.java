package com.app.mbrPymtMeth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.mbrPymtMeth.mapper.MbrPymtMethMapper;
import com.app.mbrPymtMeth.model.MbrPymtMethExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MbrPymtMethService {

	@Autowired
	private MbrPymtMethMapper mbrPymtMethMapper;

	public int getMbrPymtMethListCount(MbrPymtMethExDto mbrPymtMethExDto) throws Exception {
		return mbrPymtMethMapper.getMbrPymtMethListCount(mbrPymtMethExDto);
	}

	public List<MbrPymtMethExDto> getMbrPymtMethList(MbrPymtMethExDto mbrPymtMethExDto) throws Exception {
		return mbrPymtMethMapper.getMbrPymtMethList(mbrPymtMethExDto);
	}

	public MbrPymtMethExDto getMbrPymtMeth(MbrPymtMethExDto mbrPymtMethExDto) throws Exception {
		return mbrPymtMethMapper.getMbrPymtMeth(mbrPymtMethExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertMbrPymtMeth(MbrPymtMethExDto mbrPymtMethExDto) throws Exception {
		mbrPymtMethMapper.insertMbrPymtMeth(mbrPymtMethExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateMbrPymtMeth(MbrPymtMethExDto mbrPymtMethExDto) throws Exception {
		mbrPymtMethMapper.updateMbrPymtMeth(mbrPymtMethExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteMbrPymtMeth(MbrPymtMethExDto mbrPymtMethExDto) throws Exception {
		mbrPymtMethMapper.deleteMbrPymtMeth(mbrPymtMethExDto);
	}
}
