package com.app.prodClassifLang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.prodClassifLang.mapper.ProdClassifLangMapper;
import com.app.prodClassifLang.model.ProdClassifLangExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProdClassifLangService {

	@Autowired
	private ProdClassifLangMapper prodClassifLangMapper;

	public int getProdClassifLangListCount(ProdClassifLangExDto prodClassifLangExDto) throws Exception {
		return prodClassifLangMapper.getProdClassifLangListCount(prodClassifLangExDto);
	}

	public List<ProdClassifLangExDto> getProdClassifLangList(ProdClassifLangExDto prodClassifLangExDto) throws Exception {
		return prodClassifLangMapper.getProdClassifLangList(prodClassifLangExDto);
	}

	public ProdClassifLangExDto getProdClassifLang(ProdClassifLangExDto prodClassifLangExDto) throws Exception {
		return prodClassifLangMapper.getProdClassifLang(prodClassifLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertProdClassifLang(ProdClassifLangExDto prodClassifLangExDto) throws Exception {
		prodClassifLangMapper.insertProdClassifLang(prodClassifLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateProdClassifLang(ProdClassifLangExDto prodClassifLangExDto) throws Exception {
		prodClassifLangMapper.updateProdClassifLang(prodClassifLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteProdClassifLang(ProdClassifLangExDto prodClassifLangExDto) throws Exception {
		prodClassifLangMapper.deleteProdClassifLang(prodClassifLangExDto);
	}
}
