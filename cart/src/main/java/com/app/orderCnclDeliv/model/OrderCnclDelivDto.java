package com.app.orderCnclDeliv.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.orderCnclDeliv.model
* OrderCnclDelivDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class OrderCnclDelivDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 주문취소배송ID
	private String ordCnclDelivId;
	// 주문ID
	private String ordId;
	// 취소배송업체ID
	private String cnclDelivCompId;
	// 취소배송비
	private Long cnclDelivFee;
	// 지급사ID
	private String pymtCompId;
	// 배송여부
	private String delivYn;
	// 배송예정일
	private String delivExpectedYmd;
	// 배송일
	private String delivYmd;

}
