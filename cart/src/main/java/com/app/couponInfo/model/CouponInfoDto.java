package com.app.couponInfo.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.couponInfo.model
* CouponInfoDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class CouponInfoDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 포인트지급ID
	private String cpnId;
	// 쿠폰종류코드[PS02]
	private String cpnKindCd;
	// 쿠폰유형코드[PS03]
	private String cpnTypCd;
	// 쿠폰명
	private String cpnNm;
	// 상시여부
	private String alwaysYn;
	// 선착순여부
	private String firstComeYn;
	// 중복지급여부
	private String dupePymtYn;
	// 사용여부
	private String useYn;

}
