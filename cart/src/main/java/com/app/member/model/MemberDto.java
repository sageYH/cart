package com.app.member.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.member.model
* MemberDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class MemberDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 멤버ID
	private String mbrId;
	// 멤버등급코드
	private String mbrLvlCd;
	// 멤버구분코드
	private String mbrDivCd;
	// 폰번호
	private String phoneNo;
	// 이메일
	private String email;
	// 생년월일
	private String birthYmd;
	// 성별구분코드
	private String sexDivCd;
	// 국가코드
	private String cntryCd;
	// 업체ID
	private String compId;
	// 가입일시
	private String signupDtm;
	// 우편번호
	private String zipCd;
	// 주소
	private String addr;
	// 상세주소
	private String dtlAddr;
	// 상세주소2
	private String dtlAddr2;
	// 가입경로코드
	private String signupPathCd;
	// 최근접속채널코드
	private String latestAccChanCd;
	// 최종접속일시
	private String lastAccDtm;
	// 최근접속년월일
	private String latestAccYmd;
	// SMS수신동의여부
	private String smsRecvAcptYn;
	// 이메일수신동의여부
	private String emailRecvAcptYn;
	// PUSH수신동의여부
	private String pushRecvAcptYn;

}
