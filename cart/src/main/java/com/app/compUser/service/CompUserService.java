package com.app.compUser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.compUser.mapper.CompUserMapper;
import com.app.compUser.model.CompUserExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CompUserService {

	@Autowired
	private CompUserMapper compUserMapper;

	public int getCompUserListCount(CompUserExDto compUserExDto) throws Exception {
		return compUserMapper.getCompUserListCount(compUserExDto);
	}

	public List<CompUserExDto> getCompUserList(CompUserExDto compUserExDto) throws Exception {
		return compUserMapper.getCompUserList(compUserExDto);
	}

	public CompUserExDto getCompUser(CompUserExDto compUserExDto) throws Exception {
		return compUserMapper.getCompUser(compUserExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCompUser(CompUserExDto compUserExDto) throws Exception {
		compUserMapper.insertCompUser(compUserExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCompUser(CompUserExDto compUserExDto) throws Exception {
		compUserMapper.updateCompUser(compUserExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCompUser(CompUserExDto compUserExDto) throws Exception {
		compUserMapper.deleteCompUser(compUserExDto);
	}
}
