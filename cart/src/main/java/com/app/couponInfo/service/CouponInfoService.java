package com.app.couponInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.couponInfo.mapper.CouponInfoMapper;
import com.app.couponInfo.model.CouponInfoExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CouponInfoService {

	@Autowired
	private CouponInfoMapper couponInfoMapper;

	public int getCouponInfoListCount(CouponInfoExDto couponInfoExDto) throws Exception {
		return couponInfoMapper.getCouponInfoListCount(couponInfoExDto);
	}

	public List<CouponInfoExDto> getCouponInfoList(CouponInfoExDto couponInfoExDto) throws Exception {
		return couponInfoMapper.getCouponInfoList(couponInfoExDto);
	}

	public CouponInfoExDto getCouponInfo(CouponInfoExDto couponInfoExDto) throws Exception {
		return couponInfoMapper.getCouponInfo(couponInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCouponInfo(CouponInfoExDto couponInfoExDto) throws Exception {
		couponInfoMapper.insertCouponInfo(couponInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCouponInfo(CouponInfoExDto couponInfoExDto) throws Exception {
		couponInfoMapper.updateCouponInfo(couponInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCouponInfo(CouponInfoExDto couponInfoExDto) throws Exception {
		couponInfoMapper.deleteCouponInfo(couponInfoExDto);
	}
}
