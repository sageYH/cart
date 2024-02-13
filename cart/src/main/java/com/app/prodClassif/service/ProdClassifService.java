package com.app.prodClassif.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.prodClassif.mapper.ProdClassifMapper;
import com.app.prodClassif.model.ProdClassifExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProdClassifService {

	@Autowired
	private ProdClassifMapper prodClassifMapper;

	public int getProdClassifListCount(ProdClassifExDto prodClassifExDto) throws Exception {
		return prodClassifMapper.getProdClassifListCount(prodClassifExDto);
	}

	public List<ProdClassifExDto> getProdClassifList(ProdClassifExDto prodClassifExDto) throws Exception {
		return prodClassifMapper.getProdClassifList(prodClassifExDto);
	}

	public ProdClassifExDto getProdClassif(ProdClassifExDto prodClassifExDto) throws Exception {
		return prodClassifMapper.getProdClassif(prodClassifExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertProdClassif(ProdClassifExDto prodClassifExDto) throws Exception {
		prodClassifMapper.insertProdClassif(prodClassifExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateProdClassif(ProdClassifExDto prodClassifExDto) throws Exception {
		prodClassifMapper.updateProdClassif(prodClassifExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteProdClassif(ProdClassifExDto prodClassifExDto) throws Exception {
		prodClassifMapper.deleteProdClassif(prodClassifExDto);
	}
}
