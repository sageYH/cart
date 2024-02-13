package com.app.compAuthnKey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.compAuthnKey.mapper.CompAuthnKeyMapper;
import com.app.compAuthnKey.model.CompAuthnKeyExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CompAuthnKeyService {

	@Autowired
	private CompAuthnKeyMapper compAuthnKeyMapper;

	public int getCompAuthnKeyListCount(CompAuthnKeyExDto compAuthnKeyExDto) throws Exception {
		return compAuthnKeyMapper.getCompAuthnKeyListCount(compAuthnKeyExDto);
	}

	public List<CompAuthnKeyExDto> getCompAuthnKeyList(CompAuthnKeyExDto compAuthnKeyExDto) throws Exception {
		return compAuthnKeyMapper.getCompAuthnKeyList(compAuthnKeyExDto);
	}

	public CompAuthnKeyExDto getCompAuthnKey(CompAuthnKeyExDto compAuthnKeyExDto) throws Exception {
		return compAuthnKeyMapper.getCompAuthnKey(compAuthnKeyExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCompAuthnKey(CompAuthnKeyExDto compAuthnKeyExDto) throws Exception {
		compAuthnKeyMapper.insertCompAuthnKey(compAuthnKeyExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCompAuthnKey(CompAuthnKeyExDto compAuthnKeyExDto) throws Exception {
		compAuthnKeyMapper.updateCompAuthnKey(compAuthnKeyExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCompAuthnKey(CompAuthnKeyExDto compAuthnKeyExDto) throws Exception {
		compAuthnKeyMapper.deleteCompAuthnKey(compAuthnKeyExDto);
	}
}
