package com.app.userCert.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.userCert.model
* UserCertDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class UserCertDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 사용자인증ID
	private String userCertId;
	// 사용자ID
	private String userId;
	// 인증수단코드[CM03]
	private String certMethCd;
	// 사용자인증키
	private String userCertKey;
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
