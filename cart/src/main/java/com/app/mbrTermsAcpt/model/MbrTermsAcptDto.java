package com.app.mbrTermsAcpt.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.mbrTermsAcpt.model
* MbrTermsAcptDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class MbrTermsAcptDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 사용자약관ID
	private String mbrTermsId;
	// 멤버ID
	private String mbrId;
	// 약관ID
	private String termsId;
	// 약관상세ID
	private String termsDtlId;
	// 동의구분코드[TM03]
	private String acptDivCd;
	// 필수동의여부
	private String reqdAcptYn;
	// 선택동의여부
	private String optAcptYn;

}
