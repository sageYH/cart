package com.app.couponIssueMbr.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.couponIssueMbr.model.CouponIssueMbrExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.couponIssueMbr.mapper
* CouponIssueMbrMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("couponIssueMbrMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CouponIssueMbrMapper  extends DaoBaseMapper {
//	public class CouponIssueMbrMapper  extends DaoBaseMapper {

	public int getCouponIssueMbrListCount( CouponIssueMbrExDto couponIssueMbrExDto ) throws Exception {
		return (Integer)selectByPk("couponIssueMbrMapper.getCouponIssueMbrListCount", couponIssueMbrExDto);
	}
	
	public List<CouponIssueMbrExDto> getCouponIssueMbrList( CouponIssueMbrExDto couponIssueMbrExDto ) throws Exception {
		return (List<CouponIssueMbrExDto>)list("couponIssueMbrMapper.getCouponIssueMbrList", couponIssueMbrExDto);
	}
	
	public CouponIssueMbrExDto getCouponIssueMbr( CouponIssueMbrExDto couponIssueMbrExDto ) throws Exception {
		return (CouponIssueMbrExDto) selectByPk("couponIssueMbrMapper.getCouponIssueMbr", couponIssueMbrExDto);
	}

	public void insertCouponIssueMbr( CouponIssueMbrExDto couponIssueMbrExDto ) throws Exception {
		insert("couponIssueMbrMapper.insertCouponIssueMbr", couponIssueMbrExDto);
	}
	
	public void updateCouponIssueMbr( CouponIssueMbrExDto couponIssueMbrExDto ) throws Exception {
		update("couponIssueMbrMapper.updateCouponIssueMbr", couponIssueMbrExDto);
	}

	public void deleteCouponIssueMbr( CouponIssueMbrExDto couponIssueMbrExDto ) throws Exception {
		delete("couponIssueMbrMapper.deleteCouponIssueMbr", couponIssueMbrExDto);
	}

}
