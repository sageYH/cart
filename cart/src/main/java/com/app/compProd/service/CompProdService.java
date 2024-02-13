package com.app.compProd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.compProd.mapper.CompProdMapper;
import com.app.compProd.model.CompProdExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CompProdService {

	@Autowired
	private CompProdMapper compProdMapper;

	public int getCompProdListCount(CompProdExDto compProdExDto) throws Exception {
		return compProdMapper.getCompProdListCount(compProdExDto);
	}

	public List<CompProdExDto> getCompProdList(CompProdExDto compProdExDto) throws Exception {
		return compProdMapper.getCompProdList(compProdExDto);
	}

	public CompProdExDto getCompProd(CompProdExDto compProdExDto) throws Exception {
		return compProdMapper.getCompProd(compProdExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCompProd(CompProdExDto compProdExDto) throws Exception {
		compProdMapper.insertCompProd(compProdExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCompProd(CompProdExDto compProdExDto) throws Exception {
		compProdMapper.updateCompProd(compProdExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCompProd(CompProdExDto compProdExDto) throws Exception {
		compProdMapper.deleteCompProd(compProdExDto);
	}
}
