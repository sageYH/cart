package com.app.userInfo.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.userInfo.model
* UserInfoDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class UserInfoDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 사용자ID
	private String userId;
	// 성명
	private String userNm;
	// 비밀번호
	private String userPwd;
	// 국가코드
	private String cntryCd;
	// 업체ID
	private String compId;
	// 부서코드
	private String deptCd;
	// 부서명
	private String deptNm;
	// 전화번호
	private String telNo;
	// 폰번호
	private String phoneNo;
	// 이메일주소
	private String email;
	// 비번오류횟수
	private int pswdErrCnt;
	// 보안등급[B01]
	private String grade;
	// 비밀번호변경예정일
	private String pswdExpectedYmd;
	// 초기비밀번호
	private String initUserPswd;
	// 안내메일수신동의여부
	private String mailAgreeYn;
	// SMS수신동의여부
	private String smsAgreeYn;
	// 참조1
	private String ref1;
	// 참조2
	private String ref2;
	// LOCK여부
	private String lockYn;
	// 만료여부
	private String expectedYn;
	// 사용여부
	private String useYn;
	// CID
	private String cid;
	// 인증서DN
	private String certDn;
	// 인증서SIGN
	private String certSign;

}
