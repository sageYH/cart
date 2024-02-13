package com.app.cmnCode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.cmnCode.mapper.CmnCodeMapper;
import com.app.cmnCode.model.CmnCodeExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CmnCodeService {

	@Autowired
	private CmnCodeMapper cmnCodeMapper;

	public int getCmnCodeListCount(CmnCodeExDto cmnCodeExDto) throws Exception {
		return cmnCodeMapper.getCmnCodeListCount(cmnCodeExDto);
	}

	public List<CmnCodeExDto> getCmnCodeList(CmnCodeExDto cmnCodeExDto) throws Exception {
		return cmnCodeMapper.getCmnCodeList(cmnCodeExDto);
	}

	public CmnCodeExDto getCmnCode(CmnCodeExDto cmnCodeExDto) throws Exception {
		return cmnCodeMapper.getCmnCode(cmnCodeExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCmnCode(CmnCodeExDto cmnCodeExDto) throws Exception {
		cmnCodeMapper.insertCmnCode(cmnCodeExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCmnCode(CmnCodeExDto cmnCodeExDto) throws Exception {
		cmnCodeMapper.updateCmnCode(cmnCodeExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCmnCode(CmnCodeExDto cmnCodeExDto) throws Exception {
		cmnCodeMapper.deleteCmnCode(cmnCodeExDto);
	}
}
