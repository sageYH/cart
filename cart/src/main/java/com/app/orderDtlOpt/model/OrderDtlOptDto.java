package com.app.orderDtlOpt.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.orderDtlOpt.model
* OrderDtlOptDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class OrderDtlOptDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 주문상세ID
	private String ordDtlId;
	// 주문ID
	private String ordId;
	// 상품ID
	private String prodId;
	// 옵션ID
	private String optId;
	// 옵션수량
	private int optQty;
	// 옵션금액
	private Long optAmt;

}
