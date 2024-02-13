package com.app.orderDelivProd.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.orderDelivProd.model
* OrderDelivProdDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class OrderDelivProdDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 주문배송ID
	private String ordDelivId;
	// 주문ID
	private String ordId;
	// 상품ID
	private String prodId;

}
