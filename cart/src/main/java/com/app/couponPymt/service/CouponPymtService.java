package com.app.couponPymt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.couponPymt.mapper.CouponPymtMapper;
import com.app.couponPymt.model.CouponPymtExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CouponPymtService {

	@Autowired
	private CouponPymtMapper couponPymtMapper;

	public int getCouponPymtListCount(CouponPymtExDto couponPymtExDto) throws Exception {
		return couponPymtMapper.getCouponPymtListCount(couponPymtExDto);
	}

	public List<CouponPymtExDto> getCouponPymtList(CouponPymtExDto couponPymtExDto) throws Exception {
		return couponPymtMapper.getCouponPymtList(couponPymtExDto);
	}

	public CouponPymtExDto getCouponPymt(CouponPymtExDto couponPymtExDto) throws Exception {
		return couponPymtMapper.getCouponPymt(couponPymtExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCouponPymt(CouponPymtExDto couponPymtExDto) throws Exception {
		couponPymtMapper.insertCouponPymt(couponPymtExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCouponPymt(CouponPymtExDto couponPymtExDto) throws Exception {
		couponPymtMapper.updateCouponPymt(couponPymtExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCouponPymt(CouponPymtExDto couponPymtExDto) throws Exception {
		couponPymtMapper.deleteCouponPymt(couponPymtExDto);
	}
}
