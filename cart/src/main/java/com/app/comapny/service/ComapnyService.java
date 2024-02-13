package com.app.comapny.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.comapny.mapper.ComapnyMapper;
import com.app.comapny.model.ComapnyExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ComapnyService {

	@Autowired
	private ComapnyMapper comapnyMapper;

	public int getComapnyListCount(ComapnyExDto comapnyExDto) throws Exception {
		return comapnyMapper.getComapnyListCount(comapnyExDto);
	}

	public List<ComapnyExDto> getComapnyList(ComapnyExDto comapnyExDto) throws Exception {
		return comapnyMapper.getComapnyList(comapnyExDto);
	}

	public ComapnyExDto getComapny(ComapnyExDto comapnyExDto) throws Exception {
		return comapnyMapper.getComapny(comapnyExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertComapny(ComapnyExDto comapnyExDto) throws Exception {
		comapnyMapper.insertComapny(comapnyExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateComapny(ComapnyExDto comapnyExDto) throws Exception {
		comapnyMapper.updateComapny(comapnyExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteComapny(ComapnyExDto comapnyExDto) throws Exception {
		comapnyMapper.deleteComapny(comapnyExDto);
	}
}
