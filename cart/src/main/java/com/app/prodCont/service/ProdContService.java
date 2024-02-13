package com.app.prodCont.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.prodCont.mapper.ProdContMapper;
import com.app.prodCont.model.ProdContExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProdContService {

	@Autowired
	private ProdContMapper prodContMapper;

	public int getProdContListCount(ProdContExDto prodContExDto) throws Exception {
		return prodContMapper.getProdContListCount(prodContExDto);
	}

	public List<ProdContExDto> getProdContList(ProdContExDto prodContExDto) throws Exception {
		return prodContMapper.getProdContList(prodContExDto);
	}

	public ProdContExDto getProdCont(ProdContExDto prodContExDto) throws Exception {
		return prodContMapper.getProdCont(prodContExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertProdCont(ProdContExDto prodContExDto) throws Exception {
		prodContMapper.insertProdCont(prodContExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateProdCont(ProdContExDto prodContExDto) throws Exception {
		prodContMapper.updateProdCont(prodContExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteProdCont(ProdContExDto prodContExDto) throws Exception {
		prodContMapper.deleteProdCont(prodContExDto);
	}
}
