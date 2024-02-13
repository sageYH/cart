package com.app.compAuthnKey.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.compAuthnKey.model
* CompAuthnKeyDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class CompAuthnKeyDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 파트너사ID
	private String ptnrCompId;
	// 인증구분코드
	private String authnDivCd;
	// 자사인증ID
	private String ownCompAuthnId;
	// 자사인증비밀번호
	private String ownCompAuthnPwd;
	// 파트너사인증KEY
	private String ptnrCwCompAuthnKey;
	// 인증발급일시
	private String authnIshDtm;
	// 인증갱신일시
	private String authnUpdDtm;

}
