package com.app.couponInfoLang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.couponInfoLang.model.CouponInfoLangExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.couponInfoLang.mapper
* CouponInfoLangMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("couponInfoLangMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CouponInfoLangMapper  extends DaoBaseMapper {
//	public class CouponInfoLangMapper  extends DaoBaseMapper {

	public int getCouponInfoLangListCount( CouponInfoLangExDto couponInfoLangExDto ) throws Exception {
		return (Integer)selectByPk("couponInfoLangMapper.getCouponInfoLangListCount", couponInfoLangExDto);
	}
	
	public List<CouponInfoLangExDto> getCouponInfoLangList( CouponInfoLangExDto couponInfoLangExDto ) throws Exception {
		return (List<CouponInfoLangExDto>)list("couponInfoLangMapper.getCouponInfoLangList", couponInfoLangExDto);
	}
	
	public CouponInfoLangExDto getCouponInfoLang( CouponInfoLangExDto couponInfoLangExDto ) throws Exception {
		return (CouponInfoLangExDto) selectByPk("couponInfoLangMapper.getCouponInfoLang", couponInfoLangExDto);
	}

	public void insertCouponInfoLang( CouponInfoLangExDto couponInfoLangExDto ) throws Exception {
		insert("couponInfoLangMapper.insertCouponInfoLang", couponInfoLangExDto);
	}
	
	public void updateCouponInfoLang( CouponInfoLangExDto couponInfoLangExDto ) throws Exception {
		update("couponInfoLangMapper.updateCouponInfoLang", couponInfoLangExDto);
	}

	public void deleteCouponInfoLang( CouponInfoLangExDto couponInfoLangExDto ) throws Exception {
		delete("couponInfoLangMapper.deleteCouponInfoLang", couponInfoLangExDto);
	}

}
