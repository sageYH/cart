package com.app.order.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.order.model
* OrderDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class OrderDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 주문ID
	private String ordId;
	// 멤버ID
	private String mbrId;
	// 주문진행코드[OD01]
	private String ordProgCd;
	// 상품수량
	private int prodQty;
	// 상품총액
	private Long prodTotAmt;
	// 옵션총액
	private Long optTotAmt;
	// 배송비총액
	private Long delivTotAmt;
	// 배송비무료액
	private Long delivFreeAmt;
	// 배송비부과액
	private Long delivChrgAmt;
	// 배송부과무게
	private Long delivChrgWeight;
	// 배송부과수량
	private int delivChrgQty;
	// 주문총액
	private Long ordTotAmt;
	// 지급포인트
	private Long pymtPoint;
	// 사용포인트
	private Long usePoint;
	// 쿠폰ID
	private String cpnId;
	// 쿠폰금액
	private Long cpnAmt;
	// 결제총액
	private Long pymtTotAmt;
	// 결제수단코드[OD02]
	private String pymtMethCd;
	// 주문일시
	private String ordDtm;
	// 과세코드[OD03]
	private String taxCd;
	// 최종배송일
	private String finalDelivYmd;
	// 주문마감일
	private String ordDueYmd;
	// 고객확정일시
	private String mbrCfmDtm;
	// 교환요청일시
	private String exchReqDtm;
	// 주문취소요청일시
	private String ordCancReqDtm;
	// 주문취소액
	private Long ordCancAmt;
	// 최종결제액
	private Long finalPymtAmt;

}
