package com.app.terms.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.terms.model
* TermsDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class TermsDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 약관ID
	private String termsId;
	// 약관구분코드[TM01]
	private String termsDivCd;
	// 약관시작일
	private String termsFrYmd;
	// 약관종료일
	private String termsToYmd;
	// 약관대상코드[TM02]
	private String termsTgtCd;

}
