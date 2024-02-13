package com.app.mbrLvl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.mbrLvl.mapper.MbrLvlMapper;
import com.app.mbrLvl.model.MbrLvlExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MbrLvlService {

	@Autowired
	private MbrLvlMapper mbrLvlMapper;

	public int getMbrLvlListCount(MbrLvlExDto mbrLvlExDto) throws Exception {
		return mbrLvlMapper.getMbrLvlListCount(mbrLvlExDto);
	}

	public List<MbrLvlExDto> getMbrLvlList(MbrLvlExDto mbrLvlExDto) throws Exception {
		return mbrLvlMapper.getMbrLvlList(mbrLvlExDto);
	}

	public MbrLvlExDto getMbrLvl(MbrLvlExDto mbrLvlExDto) throws Exception {
		return mbrLvlMapper.getMbrLvl(mbrLvlExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertMbrLvl(MbrLvlExDto mbrLvlExDto) throws Exception {
		mbrLvlMapper.insertMbrLvl(mbrLvlExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateMbrLvl(MbrLvlExDto mbrLvlExDto) throws Exception {
		mbrLvlMapper.updateMbrLvl(mbrLvlExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteMbrLvl(MbrLvlExDto mbrLvlExDto) throws Exception {
		mbrLvlMapper.deleteMbrLvl(mbrLvlExDto);
	}
}
