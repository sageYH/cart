package com.app.mbrLvlBaseLang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.mbrLvlBaseLang.mapper.MbrLvlBaseLangMapper;
import com.app.mbrLvlBaseLang.model.MbrLvlBaseLangExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MbrLvlBaseLangService {

	@Autowired
	private MbrLvlBaseLangMapper mbrLvlBaseLangMapper;

	public int getMbrLvlBaseLangListCount(MbrLvlBaseLangExDto mbrLvlBaseLangExDto) throws Exception {
		return mbrLvlBaseLangMapper.getMbrLvlBaseLangListCount(mbrLvlBaseLangExDto);
	}

	public List<MbrLvlBaseLangExDto> getMbrLvlBaseLangList(MbrLvlBaseLangExDto mbrLvlBaseLangExDto) throws Exception {
		return mbrLvlBaseLangMapper.getMbrLvlBaseLangList(mbrLvlBaseLangExDto);
	}

	public MbrLvlBaseLangExDto getMbrLvlBaseLang(MbrLvlBaseLangExDto mbrLvlBaseLangExDto) throws Exception {
		return mbrLvlBaseLangMapper.getMbrLvlBaseLang(mbrLvlBaseLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertMbrLvlBaseLang(MbrLvlBaseLangExDto mbrLvlBaseLangExDto) throws Exception {
		mbrLvlBaseLangMapper.insertMbrLvlBaseLang(mbrLvlBaseLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateMbrLvlBaseLang(MbrLvlBaseLangExDto mbrLvlBaseLangExDto) throws Exception {
		mbrLvlBaseLangMapper.updateMbrLvlBaseLang(mbrLvlBaseLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteMbrLvlBaseLang(MbrLvlBaseLangExDto mbrLvlBaseLangExDto) throws Exception {
		mbrLvlBaseLangMapper.deleteMbrLvlBaseLang(mbrLvlBaseLangExDto);
	}
}
