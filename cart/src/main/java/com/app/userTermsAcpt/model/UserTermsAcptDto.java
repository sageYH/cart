package com.app.userTermsAcpt.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.userTermsAcpt.model
* UserTermsAcptDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class UserTermsAcptDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 사용자약관ID
	private String userTermsId;
	// 사용자ID
	private String userId;
	// 약관ID
	private String termsId;
	// 약관상세ID
	private String termsDtlId;
	// 동의구분코드[TM03]
	private String acptDivCd;
	// 필수동의여부
	private String reqdAcptYn;
	// 선택사항동의여부
	private String optAcptYn;

}
