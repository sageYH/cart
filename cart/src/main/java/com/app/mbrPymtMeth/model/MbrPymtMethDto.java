package com.app.mbrPymtMeth.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.mbrPymtMeth.model
* MbrPymtMethDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class MbrPymtMethDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 멤버ID
	private String mbrId;
	// 카드번호
	private String cardNo;
	// 결제대행사코드
	private String cwPymtAgcyCompCd;
	// 정산키
	private String billKey;
	// 주결제카드여부
	private String mnPymtCardYn;
	// 카드식별번호
	private String bin;
	// 카드등록일시
	private String cardRegDtm;
	// 카드유효년월
	private String cardValdYm;
	// 발급사종류코드
	private String issueCompKindCd;
	// 카드브랜드코드
	private String cardBrandCd;
	// 카드유형코드
	private String cardTypCd;
	// 카드중지일시
	private String cardStopDtm;
	// 사용여부
	private String useYn;

}
