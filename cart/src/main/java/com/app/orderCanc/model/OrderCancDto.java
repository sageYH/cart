package com.app.orderCanc.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.orderCanc.model
* OrderCancDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class OrderCancDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 주문취소ID
	private String ordCnclId;
	// 주문ID
	private String ordId;
	// 주문취소일시
	private String ordCnclDtm;
	// 주문취소구분[OD04]
	private String ordCnclDivCd;
	// 주문취소총액
	private Long ordCnclAmt;
	// 지급취소포인트ID
	private String pymtCnclPointId;
	// 지급취소쿠폰ID
	private String pymtCnclCpnId;
	// 취소배송비
	private Long cnclDelivFee;
	// 배송비대상코드[OD05]
	private String delivFeeTgtCd;
	// 지급사ID
	private String pymtCompId;

}
