package com.app.orderCnclDelivProd.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.orderCnclDelivProd.model
* OrderCnclDelivProdDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class OrderCnclDelivProdDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 주문배송ID
	private String ordCnclDelivId;
	// 주문ID
	private String ordId;
	// 상품ID
	private String prodId;

}
