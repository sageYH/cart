package com.app.couponInfoLang.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.couponInfoLang.model
* CouponInfoLangDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class CouponInfoLangDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 포인트지급ID
	private String cpnId;
	// 언어코드
	private String langCd;
	// 쿠폰명
	private String cpnNm;
	// 사용여부
	private String useYn;

}
