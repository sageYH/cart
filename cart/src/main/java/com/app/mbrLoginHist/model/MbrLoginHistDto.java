package com.app.mbrLoginHist.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.mbrLoginHist.model
* MbrLoginHistDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class MbrLoginHistDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 멤버로그인일련번호
	private Long mbrLoginSeqNo;
	// 접속년월일
	private String accYmd;
	// 접속시분초
	private String accHms;
	// 멤버ID
	private String mbrId;
	// 접근기기코드[MB04]
	private String accDevCd;
	// 접속채널코드[MB08]
	private String accChanCd;

}
