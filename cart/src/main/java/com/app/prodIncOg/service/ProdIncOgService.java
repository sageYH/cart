package com.app.prodIncOg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.prodIncOg.mapper.ProdIncOgMapper;
import com.app.prodIncOg.model.ProdIncOgExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProdIncOgService {

	@Autowired
	private ProdIncOgMapper prodIncOgMapper;

	public int getProdIncOgListCount(ProdIncOgExDto prodIncOgExDto) throws Exception {
		return prodIncOgMapper.getProdIncOgListCount(prodIncOgExDto);
	}

	public List<ProdIncOgExDto> getProdIncOgList(ProdIncOgExDto prodIncOgExDto) throws Exception {
		return prodIncOgMapper.getProdIncOgList(prodIncOgExDto);
	}

	public ProdIncOgExDto getProdIncOg(ProdIncOgExDto prodIncOgExDto) throws Exception {
		return prodIncOgMapper.getProdIncOg(prodIncOgExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertProdIncOg(ProdIncOgExDto prodIncOgExDto) throws Exception {
		prodIncOgMapper.insertProdIncOg(prodIncOgExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateProdIncOg(ProdIncOgExDto prodIncOgExDto) throws Exception {
		prodIncOgMapper.updateProdIncOg(prodIncOgExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteProdIncOg(ProdIncOgExDto prodIncOgExDto) throws Exception {
		prodIncOgMapper.deleteProdIncOg(prodIncOgExDto);
	}
}
