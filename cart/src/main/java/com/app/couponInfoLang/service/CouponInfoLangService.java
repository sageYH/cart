package com.app.couponInfoLang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.couponInfoLang.mapper.CouponInfoLangMapper;
import com.app.couponInfoLang.model.CouponInfoLangExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CouponInfoLangService {

	@Autowired
	private CouponInfoLangMapper couponInfoLangMapper;

	public int getCouponInfoLangListCount(CouponInfoLangExDto couponInfoLangExDto) throws Exception {
		return couponInfoLangMapper.getCouponInfoLangListCount(couponInfoLangExDto);
	}

	public List<CouponInfoLangExDto> getCouponInfoLangList(CouponInfoLangExDto couponInfoLangExDto) throws Exception {
		return couponInfoLangMapper.getCouponInfoLangList(couponInfoLangExDto);
	}

	public CouponInfoLangExDto getCouponInfoLang(CouponInfoLangExDto couponInfoLangExDto) throws Exception {
		return couponInfoLangMapper.getCouponInfoLang(couponInfoLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCouponInfoLang(CouponInfoLangExDto couponInfoLangExDto) throws Exception {
		couponInfoLangMapper.insertCouponInfoLang(couponInfoLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCouponInfoLang(CouponInfoLangExDto couponInfoLangExDto) throws Exception {
		couponInfoLangMapper.updateCouponInfoLang(couponInfoLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCouponInfoLang(CouponInfoLangExDto couponInfoLangExDto) throws Exception {
		couponInfoLangMapper.deleteCouponInfoLang(couponInfoLangExDto);
	}
}
