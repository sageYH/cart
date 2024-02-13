package com.app.couponIssue.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.couponIssue.model
* CouponIssueDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class CouponIssueDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 쿠폰발급ID
	private String cpnIssueId;
	// 쿠폰ID
	private String cpnId;
	// 쿠폰내용
	private String cpnCont;
	// 지급업체ID
	private String pymtCompId;
	// 쿠폰적용시작일
	private String cpnApplFrYmd;
	// 쿠폰적용종료일
	private String cpnApplToYmd;
	// 회원지정여부
	private String mbrSpecifyYn;
	// 쿠폰발행수량
	private Long cpnIssueQty;
	// 쿠폰총액
	private Long cpnTotAmt;
	// 쿠폰금액
	private Long cpnAmt;
	// 상품최소금액
	private Long prodMinAmt;
	// 주문최소금액
	private Long ordMinAmt;
	// 쿠폰지급수량
	private Long cpnPaidQty;
	// 쿠폰지급총액
	private Long cpnPaidTotAmt;
	// 사용여부
	private String useYn;

}
