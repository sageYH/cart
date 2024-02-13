package com.app.prodOptLang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.prodOptLang.mapper.ProdOptLangMapper;
import com.app.prodOptLang.model.ProdOptLangExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProdOptLangService {

	@Autowired
	private ProdOptLangMapper prodOptLangMapper;

	public int getProdOptLangListCount(ProdOptLangExDto prodOptLangExDto) throws Exception {
		return prodOptLangMapper.getProdOptLangListCount(prodOptLangExDto);
	}

	public List<ProdOptLangExDto> getProdOptLangList(ProdOptLangExDto prodOptLangExDto) throws Exception {
		return prodOptLangMapper.getProdOptLangList(prodOptLangExDto);
	}

	public ProdOptLangExDto getProdOptLang(ProdOptLangExDto prodOptLangExDto) throws Exception {
		return prodOptLangMapper.getProdOptLang(prodOptLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertProdOptLang(ProdOptLangExDto prodOptLangExDto) throws Exception {
		prodOptLangMapper.insertProdOptLang(prodOptLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateProdOptLang(ProdOptLangExDto prodOptLangExDto) throws Exception {
		prodOptLangMapper.updateProdOptLang(prodOptLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteProdOptLang(ProdOptLangExDto prodOptLangExDto) throws Exception {
		prodOptLangMapper.deleteProdOptLang(prodOptLangExDto);
	}
}
