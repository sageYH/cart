package com.app.pymtAgcyComp.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.pymtAgcyComp.model
* PymtAgcyCompDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class PymtAgcyCompDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 결제대행사코드
	private String cwPymtAgcyCompCd;
	// 결제대행사명
	private String cwPymtAgcyCompNm;
	// 카드사코드
	private String cardCompCd;
	// 결제유형코드
	private String pymtTypCd;
	// 상점ID
	private String storeId;
	// 상점Key
	private String storeKey;
	// 거래취소비밀번호
	private String txnCancPwd;
	// VAN인증키
	private String vanAuthnKey;
	// PG대표사여부
	private String pgRepCompYn;
	// VAN대표사여부
	private String vanRepCompYn;
	// PG사Url
	private String pgUrl;
	// PG사TestUrl
	private String pgTestUrl;

}
