package com.app.memberCert.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.memberCert.model
* MemberCertDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class MemberCertDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 멤버인증ID
	private String mbrCertId;
	// 멤버ID
	private String mbrId;
	// 인증수단코드[CM03]
	private String certMethCd;
	// 멤버인증키
	private String mbrCertKey;
	// 인증시작일시
	private String certFrDtm;
	// 인증종료일시
	private String certToDtm;
	// 인증발송일시
	private String certSendDtm;
	// 인증확정일시
	private String certCfmDtm;
	// 인증확정여부
	private String certCfmYn;

}
