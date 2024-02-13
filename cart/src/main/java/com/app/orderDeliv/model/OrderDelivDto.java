package com.app.orderDeliv.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.orderDeliv.model
* OrderDelivDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class OrderDelivDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 주문배송ID
	private String ordDelivId;
	// 주문ID
	private String ordId;
	// 배송업체ID
	private String delivCompId;
	// 배송비
	private Long delivFee;
	// 지급사ID
	private String pymtCompId;
	// 배송여부
	private String delivYn;
	// 배송예정일
	private String delivExpectedYmd;
	// 배송일
	private String delivYmd;

}
