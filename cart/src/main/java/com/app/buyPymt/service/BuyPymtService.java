package com.app.buyPymt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.buyPymt.mapper.BuyPymtMapper;
import com.app.buyPymt.model.BuyPymtExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BuyPymtService {

	@Autowired
	private BuyPymtMapper buyPymtMapper;

	public int getBuyPymtListCount(BuyPymtExDto buyPymtExDto) throws Exception {
		return buyPymtMapper.getBuyPymtListCount(buyPymtExDto);
	}

	public List<BuyPymtExDto> getBuyPymtList(BuyPymtExDto buyPymtExDto) throws Exception {
		return buyPymtMapper.getBuyPymtList(buyPymtExDto);
	}

	public BuyPymtExDto getBuyPymt(BuyPymtExDto buyPymtExDto) throws Exception {
		return buyPymtMapper.getBuyPymt(buyPymtExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertBuyPymt(BuyPymtExDto buyPymtExDto) throws Exception {
		buyPymtMapper.insertBuyPymt(buyPymtExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateBuyPymt(BuyPymtExDto buyPymtExDto) throws Exception {
		buyPymtMapper.updateBuyPymt(buyPymtExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteBuyPymt(BuyPymtExDto buyPymtExDto) throws Exception {
		buyPymtMapper.deleteBuyPymt(buyPymtExDto);
	}
}
