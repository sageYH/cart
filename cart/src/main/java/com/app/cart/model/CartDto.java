package com.app.cart.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.cart.model
* CartDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class CartDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 장바구니ID
	private String cartId;
	// 멤버ID
	private String mbrId;
	// 상품ID
	private String prodId;
	// 업체ID
	private String compId;
	// 상품수량
	private Long prodQty;
	// 상품금액
	private Long prodAmt;
	// 옵션총금액
	private Long optTotAmt;
	// 장바구니일시
	private String cartDtm;
	// 상품경로코드[PD08]
	private String prodPathCd;
	// 사용여부
	private String useYn;

}
