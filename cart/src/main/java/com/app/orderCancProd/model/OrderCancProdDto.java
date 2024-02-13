package com.app.orderCancProd.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.orderCancProd.model
* OrderCancProdDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class OrderCancProdDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 주문취소ID
	private String ordCnclId;
	// 주문ID
	private String ordId;
	// 상품ID
	private String prodId;
	// 상품취소수량
	private int prodCnclQty;
	// 상품취소금액
	private Long prodCnclAmt;
	// 옵션취소금액
	private Long optCnclAmt;

}
