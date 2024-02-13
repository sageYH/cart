package com.app.prodOptIncOg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.prodOptIncOg.mapper.ProdOptIncOgMapper;
import com.app.prodOptIncOg.model.ProdOptIncOgExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProdOptIncOgService {

	@Autowired
	private ProdOptIncOgMapper prodOptIncOgMapper;

	public int getProdOptIncOgListCount(ProdOptIncOgExDto prodOptIncOgExDto) throws Exception {
		return prodOptIncOgMapper.getProdOptIncOgListCount(prodOptIncOgExDto);
	}

	public List<ProdOptIncOgExDto> getProdOptIncOgList(ProdOptIncOgExDto prodOptIncOgExDto) throws Exception {
		return prodOptIncOgMapper.getProdOptIncOgList(prodOptIncOgExDto);
	}

	public ProdOptIncOgExDto getProdOptIncOg(ProdOptIncOgExDto prodOptIncOgExDto) throws Exception {
		return prodOptIncOgMapper.getProdOptIncOg(prodOptIncOgExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertProdOptIncOg(ProdOptIncOgExDto prodOptIncOgExDto) throws Exception {
		prodOptIncOgMapper.insertProdOptIncOg(prodOptIncOgExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateProdOptIncOg(ProdOptIncOgExDto prodOptIncOgExDto) throws Exception {
		prodOptIncOgMapper.updateProdOptIncOg(prodOptIncOgExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteProdOptIncOg(ProdOptIncOgExDto prodOptIncOgExDto) throws Exception {
		prodOptIncOgMapper.deleteProdOptIncOg(prodOptIncOgExDto);
	}
}
