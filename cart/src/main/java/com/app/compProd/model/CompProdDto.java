package com.app.compProd.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.compProd.model
* CompProdDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class CompProdDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 업체상품ID
	private String compProdId;
	// 업체ID
	private String compId;
	// 상품ID
	private String prodId;
	// 상품유형코드[PD06]
	private String prodTypCd;
	// 적용시작일
	private String applFrYmd;
	// 적용종료일
	private String applToYmd;
	// 상품금액
	private Long prodAmt;
	// 상품재고수량
	private int prodInvQty;
	// 상품사용수량
	private int prodUseQty;
	// 상품입고수량
	private int prodInQty;
	// 품절여부
	private String soldOutYn;
	// 포인트ID
	private String pointId;
	// 쿠폰ID
	private String cpnId;
	// 배송비유형코드[DV01]
	private String delivFeeTypCd;
	// 기본배송비
	private Long baseDelivFee;
	// 배송무료최소금액
	private Long delivFreeMinAmt;
	// 배송기준무게
	private Double delivBaseWeight;
	// 배송기준수량
	private int delivBaseQty;
	// 상품조회수
	private int prodViews;
	// 상품등록일시
	private String prodRegDtm;
	// 상품변경일시
	private String prodUpdDtm;
	// 등록 IP
	private String regIp;
	// 정렬순서
	private int sortSeq;
	// 판매수량
	private int salesQty;
	// 재입고SMS알림 여부
	private String incSmsNotifYn;
	// 사용후기 수
	private int reviewNum;
	// 사용후기 별점 평균
	private Double reviewRtgAvg;
	// 광고료
	private Long adFee;
	// 사용여부
	private String useYn;

}
