package com.app.couponIssue.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.couponIssue.model.CouponIssueExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.couponIssue.mapper
* CouponIssueMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("couponIssueMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CouponIssueMapper  extends DaoBaseMapper {
//	public class CouponIssueMapper  extends DaoBaseMapper {

	public int getCouponIssueListCount( CouponIssueExDto couponIssueExDto ) throws Exception {
		return (Integer)selectByPk("couponIssueMapper.getCouponIssueListCount", couponIssueExDto);
	}
	
	public List<CouponIssueExDto> getCouponIssueList( CouponIssueExDto couponIssueExDto ) throws Exception {
		return (List<CouponIssueExDto>)list("couponIssueMapper.getCouponIssueList", couponIssueExDto);
	}
	
	public CouponIssueExDto getCouponIssue( CouponIssueExDto couponIssueExDto ) throws Exception {
		return (CouponIssueExDto) selectByPk("couponIssueMapper.getCouponIssue", couponIssueExDto);
	}

	public void insertCouponIssue( CouponIssueExDto couponIssueExDto ) throws Exception {
		insert("couponIssueMapper.insertCouponIssue", couponIssueExDto);
	}
	
	public void updateCouponIssue( CouponIssueExDto couponIssueExDto ) throws Exception {
		update("couponIssueMapper.updateCouponIssue", couponIssueExDto);
	}

	public void deleteCouponIssue( CouponIssueExDto couponIssueExDto ) throws Exception {
		delete("couponIssueMapper.deleteCouponIssue", couponIssueExDto);
	}

}
