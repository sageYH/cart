package com.app.couponPymt.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.couponPymt.model
* CouponPymtDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class CouponPymtDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 쿠폰지급ID
	private String cpnPymtId;
	// 쿠폰발급ID
	private String cpnIssueId;
	// 쿠폰ID
	private String cpnId;
	// 쿠폰금액
	private Long cpnAmt;
	// 쿠폰취소금액
	private Long cpnCancAmt;
	// 주문ID
	private String ordId;
	// 이벤트ID
	private String eventId;
	// 제휴쿠폰ID
	private String afCpnId;
	// 지급업체ID
	private String pymtCompId;
	// 사용여부
	private String useYn;

}
