package com.app.products.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.products.model
* ProductsDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class ProductsDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 상품ID
	private String prodId;
	// 상품명
	private String prodNm;
	// 상위상품분류ID
	private String prntProdId;
	// 상품레벨
	private String compId;
	// 상품스킨
	private String prodSkin;
	// 상품모바일스킨
	private String prodMobiSkin;
	// 상품제조사
	private String prodMfr;
	// 상품원산지
	private String prodOrigin;
	// 상품브랜드
	private String prodBrand;
	// 상품모델
	private String prodModel;
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
	// 상품무게
	private Double prodWeight;
	// 상품포함수량
	private int prodInclQty;
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
	// 사용여부
	private String useYn;

}
