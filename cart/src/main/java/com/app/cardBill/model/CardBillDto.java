package com.app.cardBill.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.cardBill.model
* CardBillDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class CardBillDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 카드정산ID
	private String cardBillId;
	// 카드정산년월일
	private String cardBillYmd;
	// 결제대행사코드
	private String pymtAgcyCompCd;
	// 카드사코드
	private String cardCompCd;
	// 카드건수
	private int cardQty;
	// 카드금액
	private Long cardAmt;
	// 카드수수료금액
	private Long cardFeeAmt;
	// 카드오류건수
	private int cardErrQty;
	// 카드오류금액
	private Long cardErrAmt;
	// 정산건수
	private int billQty;
	// 정산금액
	private Long billAmt;
	// 정산확정여부
	private String billCfmYn;
	// 정산확정년월일
	private String billCfmYmd;

}
