package com.app.buyPymt.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.buyPymt.model
* BuyPymtDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class BuyPymtDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// PG구매ID
	private String pgBuyId;
	// 주문ID
	private String ordId;
	// 주문취소ID
	private String ordCnclId;
	// 멤버ID
	private String mbrId;
	// 카드번호
	private String cardNo;
	// 결제구분코드
	private String pymtDivCd;
	// 결제진행코드
	private String pymtProgCd;
	// 결제대행사코드
	private String pymtAgcyCompCd;
	// 카드사코드
	private String cardCompCd;
	// 카드할부기간
	private int cardInstalPeriod;
	// 카드결제금액
	private Long cardPymtAmt;
	// 정산키
	private String billKey;
	// 승인번호
	private String apvlNo;
	// 거래ID
	private String txnId;
	// 상점ID
	private String storeId;
	// 상점Key
	private String storeKey;
	// 상품명
	private String prodNm;
	// 구매자명
	private String consrNm;
	// 구매자이메일
	private String consrEmail;
	// 공급가액금액
	private Long splyPrice;
	// 부가가치세
	private Long vat;
	// 면세금액
	private Long taxExptAmt;
	// 무이자여부
	private String intFreeYn;
	// 할부개월수
	private int instalMos;
	// 몰IP
	private String mallIp;
	// 카드사명
	private String cardCompNm;
	// 결과코드
	private String rstlCd;
	// 결과내용
	private String rstlCont;
	// 승인일시
	private String apvlDtm;
	// 승인코드
	private String apvlCd;
	// 승인여부
	private String apvlYn;
	// 오류코드
	private String errCd;
	// 오류내용
	private String errCont;
	// 결제취소여부
	private String pymtCancYn;
	// 결제취소금액
	private Long pymtCancAmt;
	// 결제취소사유코드
	private String pymtCancRsnCd;
	// 결제취소사유
	private String pymtCancRsn;
	// 거래취소비밀번호
	private String txnCancPwd;
	// 취소ID
	private String cancId;
	// 취소IP
	private String cancIp;
	// 부분취소여부
	private String prtlCancYn;
	// 취소결과코드
	private String cancRstlCd;
	// 취소결과내용
	private String cancRstlCont;
	// 취소일시
	private String cancDtm;
	// 취소시분초
	private String cancHms;
	// 취소번호
	private String cancNo;
	// 취소상세오류코드
	private String cancDtlErrCd;
	// 취소상세오류내용
	private String cancDtlErrCont;
	// 정산년월일
	private String billYmd;

}
