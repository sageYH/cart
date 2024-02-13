package com.app.orderExchDtl.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.orderExchDtl.model
* OrderExchDtlDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class OrderExchDtlDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 주문배송ID
	private String ordExchId;
	// 주문ID
	private String ordId;
	// 상품ID
	private String prodId;
	// 교환사유
	private String exchRsn;

}
