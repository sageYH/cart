package com.app.couponPymt.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.couponPymt.model.CouponPymtExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.couponPymt.mapper
* CouponPymtMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("couponPymtMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CouponPymtMapper  extends DaoBaseMapper {
//	public class CouponPymtMapper  extends DaoBaseMapper {

	public int getCouponPymtListCount( CouponPymtExDto couponPymtExDto ) throws Exception {
		return (Integer)selectByPk("couponPymtMapper.getCouponPymtListCount", couponPymtExDto);
	}
	
	public List<CouponPymtExDto> getCouponPymtList( CouponPymtExDto couponPymtExDto ) throws Exception {
		return (List<CouponPymtExDto>)list("couponPymtMapper.getCouponPymtList", couponPymtExDto);
	}
	
	public CouponPymtExDto getCouponPymt( CouponPymtExDto couponPymtExDto ) throws Exception {
		return (CouponPymtExDto) selectByPk("couponPymtMapper.getCouponPymt", couponPymtExDto);
	}

	public void insertCouponPymt( CouponPymtExDto couponPymtExDto ) throws Exception {
		insert("couponPymtMapper.insertCouponPymt", couponPymtExDto);
	}
	
	public void updateCouponPymt( CouponPymtExDto couponPymtExDto ) throws Exception {
		update("couponPymtMapper.updateCouponPymt", couponPymtExDto);
	}

	public void deleteCouponPymt( CouponPymtExDto couponPymtExDto ) throws Exception {
		delete("couponPymtMapper.deleteCouponPymt", couponPymtExDto);
	}

}
