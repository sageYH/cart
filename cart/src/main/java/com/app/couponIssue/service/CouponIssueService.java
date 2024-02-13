package com.app.couponIssue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.couponIssue.mapper.CouponIssueMapper;
import com.app.couponIssue.model.CouponIssueExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CouponIssueService {

	@Autowired
	private CouponIssueMapper couponIssueMapper;

	public int getCouponIssueListCount(CouponIssueExDto couponIssueExDto) throws Exception {
		return couponIssueMapper.getCouponIssueListCount(couponIssueExDto);
	}

	public List<CouponIssueExDto> getCouponIssueList(CouponIssueExDto couponIssueExDto) throws Exception {
		return couponIssueMapper.getCouponIssueList(couponIssueExDto);
	}

	public CouponIssueExDto getCouponIssue(CouponIssueExDto couponIssueExDto) throws Exception {
		return couponIssueMapper.getCouponIssue(couponIssueExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCouponIssue(CouponIssueExDto couponIssueExDto) throws Exception {
		couponIssueMapper.insertCouponIssue(couponIssueExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCouponIssue(CouponIssueExDto couponIssueExDto) throws Exception {
		couponIssueMapper.updateCouponIssue(couponIssueExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCouponIssue(CouponIssueExDto couponIssueExDto) throws Exception {
		couponIssueMapper.deleteCouponIssue(couponIssueExDto);
	}
}
