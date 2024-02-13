package com.app.prodContLang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.prodContLang.mapper.ProdContLangMapper;
import com.app.prodContLang.model.ProdContLangExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProdContLangService {

	@Autowired
	private ProdContLangMapper prodContLangMapper;

	public int getProdContLangListCount(ProdContLangExDto prodContLangExDto) throws Exception {
		return prodContLangMapper.getProdContLangListCount(prodContLangExDto);
	}

	public List<ProdContLangExDto> getProdContLangList(ProdContLangExDto prodContLangExDto) throws Exception {
		return prodContLangMapper.getProdContLangList(prodContLangExDto);
	}

	public ProdContLangExDto getProdContLang(ProdContLangExDto prodContLangExDto) throws Exception {
		return prodContLangMapper.getProdContLang(prodContLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertProdContLang(ProdContLangExDto prodContLangExDto) throws Exception {
		prodContLangMapper.insertProdContLang(prodContLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateProdContLang(ProdContLangExDto prodContLangExDto) throws Exception {
		prodContLangMapper.updateProdContLang(prodContLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteProdContLang(ProdContLangExDto prodContLangExDto) throws Exception {
		prodContLangMapper.deleteProdContLang(prodContLangExDto);
	}
}
