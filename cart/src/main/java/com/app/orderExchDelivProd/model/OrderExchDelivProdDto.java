package com.app.orderExchDelivProd.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.orderExchDelivProd.model
* OrderExchDelivProdDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class OrderExchDelivProdDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 주문교환배송ID
	private String ordExchDelivId;
	// 주문ID
	private String ordId;
	// 상품ID
	private String prodId;

}
