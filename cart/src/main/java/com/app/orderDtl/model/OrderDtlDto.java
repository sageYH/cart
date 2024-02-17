package com.app.orderDtl.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.orderDtl.model
* OrderDtlDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class OrderDtlDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 주문상세ID
	private String ordDtlId;
	// 주문ID
	private String ordId;
	// 상품ID
	private String prodId;
	// 업체ID
	private String compId;
	// 장바구니ID
	private String cartId;
	// 주문상품수량
	private int ordProdQty;
	// 주문상품금액
	private Long ordProdAmt;
	// 주문옵션금액
	private Long ordOptAmt;
	// 옵션상세내용
	private String optDtlCont;
	// 장바구니일시
	private String cartDtm;
	// 상품경로코드[PD08]
	private String prodPathCd;

}
