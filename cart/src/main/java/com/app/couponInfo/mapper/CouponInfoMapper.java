package com.app.couponInfo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.couponInfo.model.CouponInfoExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.couponInfo.mapper
* CouponInfoMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("couponInfoMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CouponInfoMapper  extends DaoBaseMapper {
//	public class CouponInfoMapper  extends DaoBaseMapper {

	public int getCouponInfoListCount( CouponInfoExDto couponInfoExDto ) throws Exception {
		return (Integer)selectByPk("couponInfoMapper.getCouponInfoListCount", couponInfoExDto);
	}
	
	public List<CouponInfoExDto> getCouponInfoList( CouponInfoExDto couponInfoExDto ) throws Exception {
		return (List<CouponInfoExDto>)list("couponInfoMapper.getCouponInfoList", couponInfoExDto);
	}
	
	public CouponInfoExDto getCouponInfo( CouponInfoExDto couponInfoExDto ) throws Exception {
		return (CouponInfoExDto) selectByPk("couponInfoMapper.getCouponInfo", couponInfoExDto);
	}

	public void insertCouponInfo( CouponInfoExDto couponInfoExDto ) throws Exception {
		insert("couponInfoMapper.insertCouponInfo", couponInfoExDto);
	}
	
	public void updateCouponInfo( CouponInfoExDto couponInfoExDto ) throws Exception {
		update("couponInfoMapper.updateCouponInfo", couponInfoExDto);
	}

	public void deleteCouponInfo( CouponInfoExDto couponInfoExDto ) throws Exception {
		delete("couponInfoMapper.deleteCouponInfo", couponInfoExDto);
	}

}
