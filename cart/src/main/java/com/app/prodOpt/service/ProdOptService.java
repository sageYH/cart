package com.app.prodOpt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.prodOpt.mapper.ProdOptMapper;
import com.app.prodOpt.model.ProdOptExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProdOptService {

	@Autowired
	private ProdOptMapper prodOptMapper;

	public int getProdOptListCount(ProdOptExDto prodOptExDto) throws Exception {
		return prodOptMapper.getProdOptListCount(prodOptExDto);
	}

	public List<ProdOptExDto> getProdOptList(ProdOptExDto prodOptExDto) throws Exception {
		return prodOptMapper.getProdOptList(prodOptExDto);
	}

	public ProdOptExDto getProdOpt(ProdOptExDto prodOptExDto) throws Exception {
		return prodOptMapper.getProdOpt(prodOptExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertProdOpt(ProdOptExDto prodOptExDto) throws Exception {
		prodOptMapper.insertProdOpt(prodOptExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateProdOpt(ProdOptExDto prodOptExDto) throws Exception {
		prodOptMapper.updateProdOpt(prodOptExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteProdOpt(ProdOptExDto prodOptExDto) throws Exception {
		prodOptMapper.deleteProdOpt(prodOptExDto);
	}
}
