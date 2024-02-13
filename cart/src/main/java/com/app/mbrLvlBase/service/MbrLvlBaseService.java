package com.app.mbrLvlBase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.mbrLvlBase.mapper.MbrLvlBaseMapper;
import com.app.mbrLvlBase.model.MbrLvlBaseExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MbrLvlBaseService {

	@Autowired
	private MbrLvlBaseMapper mbrLvlBaseMapper;

	public int getMbrLvlBaseListCount(MbrLvlBaseExDto mbrLvlBaseExDto) throws Exception {
		return mbrLvlBaseMapper.getMbrLvlBaseListCount(mbrLvlBaseExDto);
	}

	public List<MbrLvlBaseExDto> getMbrLvlBaseList(MbrLvlBaseExDto mbrLvlBaseExDto) throws Exception {
		return mbrLvlBaseMapper.getMbrLvlBaseList(mbrLvlBaseExDto);
	}

	public MbrLvlBaseExDto getMbrLvlBase(MbrLvlBaseExDto mbrLvlBaseExDto) throws Exception {
		return mbrLvlBaseMapper.getMbrLvlBase(mbrLvlBaseExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertMbrLvlBase(MbrLvlBaseExDto mbrLvlBaseExDto) throws Exception {
		mbrLvlBaseMapper.insertMbrLvlBase(mbrLvlBaseExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateMbrLvlBase(MbrLvlBaseExDto mbrLvlBaseExDto) throws Exception {
		mbrLvlBaseMapper.updateMbrLvlBase(mbrLvlBaseExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteMbrLvlBase(MbrLvlBaseExDto mbrLvlBaseExDto) throws Exception {
		mbrLvlBaseMapper.deleteMbrLvlBase(mbrLvlBaseExDto);
	}
}
