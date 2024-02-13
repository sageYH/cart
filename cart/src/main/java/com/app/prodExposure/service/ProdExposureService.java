package com.app.prodExposure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.prodExposure.mapper.ProdExposureMapper;
import com.app.prodExposure.model.ProdExposureExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProdExposureService {

	@Autowired
	private ProdExposureMapper prodExposureMapper;

	public int getProdExposureListCount(ProdExposureExDto prodExposureExDto) throws Exception {
		return prodExposureMapper.getProdExposureListCount(prodExposureExDto);
	}

	public List<ProdExposureExDto> getProdExposureList(ProdExposureExDto prodExposureExDto) throws Exception {
		return prodExposureMapper.getProdExposureList(prodExposureExDto);
	}

	public ProdExposureExDto getProdExposure(ProdExposureExDto prodExposureExDto) throws Exception {
		return prodExposureMapper.getProdExposure(prodExposureExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertProdExposure(ProdExposureExDto prodExposureExDto) throws Exception {
		prodExposureMapper.insertProdExposure(prodExposureExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateProdExposure(ProdExposureExDto prodExposureExDto) throws Exception {
		prodExposureMapper.updateProdExposure(prodExposureExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteProdExposure(ProdExposureExDto prodExposureExDto) throws Exception {
		prodExposureMapper.deleteProdExposure(prodExposureExDto);
	}
}
