package com.app.cartOption.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.cartOption.model
* CartOptionDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class CartOptionDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 장바구니ID
	private String cartId;
	// 상품ID
	private String prodId;
	// 옵션ID
	private String optId;
	// 옵션상세내용
	private String optDtlCont;
	// 옵션수량
	private Long optQty;
	// 옵션금액
	private Long optAmt;
	// 사용여부
	private String useYn;

}
