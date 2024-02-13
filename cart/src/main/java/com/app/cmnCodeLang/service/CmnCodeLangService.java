package com.app.cmnCodeLang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.cmnCodeLang.mapper.CmnCodeLangMapper;
import com.app.cmnCodeLang.model.CmnCodeLangExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CmnCodeLangService {

	@Autowired
	private CmnCodeLangMapper cmnCodeLangMapper;

	public int getCmnCodeLangListCount(CmnCodeLangExDto cmnCodeLangExDto) throws Exception {
		return cmnCodeLangMapper.getCmnCodeLangListCount(cmnCodeLangExDto);
	}

	public List<CmnCodeLangExDto> getCmnCodeLangList(CmnCodeLangExDto cmnCodeLangExDto) throws Exception {
		return cmnCodeLangMapper.getCmnCodeLangList(cmnCodeLangExDto);
	}

	public CmnCodeLangExDto getCmnCodeLang(CmnCodeLangExDto cmnCodeLangExDto) throws Exception {
		return cmnCodeLangMapper.getCmnCodeLang(cmnCodeLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCmnCodeLang(CmnCodeLangExDto cmnCodeLangExDto) throws Exception {
		cmnCodeLangMapper.insertCmnCodeLang(cmnCodeLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCmnCodeLang(CmnCodeLangExDto cmnCodeLangExDto) throws Exception {
		cmnCodeLangMapper.updateCmnCodeLang(cmnCodeLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCmnCodeLang(CmnCodeLangExDto cmnCodeLangExDto) throws Exception {
		cmnCodeLangMapper.deleteCmnCodeLang(cmnCodeLangExDto);
	}
}
