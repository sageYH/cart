package com.app.couponIssueMbr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.couponIssueMbr.mapper.CouponIssueMbrMapper;
import com.app.couponIssueMbr.model.CouponIssueMbrExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CouponIssueMbrService {

	@Autowired
	private CouponIssueMbrMapper couponIssueMbrMapper;

	public int getCouponIssueMbrListCount(CouponIssueMbrExDto couponIssueMbrExDto) throws Exception {
		return couponIssueMbrMapper.getCouponIssueMbrListCount(couponIssueMbrExDto);
	}

	public List<CouponIssueMbrExDto> getCouponIssueMbrList(CouponIssueMbrExDto couponIssueMbrExDto) throws Exception {
		return couponIssueMbrMapper.getCouponIssueMbrList(couponIssueMbrExDto);
	}

	public CouponIssueMbrExDto getCouponIssueMbr(CouponIssueMbrExDto couponIssueMbrExDto) throws Exception {
		return couponIssueMbrMapper.getCouponIssueMbr(couponIssueMbrExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCouponIssueMbr(CouponIssueMbrExDto couponIssueMbrExDto) throws Exception {
		couponIssueMbrMapper.insertCouponIssueMbr(couponIssueMbrExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCouponIssueMbr(CouponIssueMbrExDto couponIssueMbrExDto) throws Exception {
		couponIssueMbrMapper.updateCouponIssueMbr(couponIssueMbrExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCouponIssueMbr(CouponIssueMbrExDto couponIssueMbrExDto) throws Exception {
		couponIssueMbrMapper.deleteCouponIssueMbr(couponIssueMbrExDto);
	}
}
