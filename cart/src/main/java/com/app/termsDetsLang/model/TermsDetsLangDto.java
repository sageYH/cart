package com.app.termsDetsLang.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.termsDetsLang.model
* TermsDetsLangDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class TermsDetsLangDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 약관ID
	private String termsId;
	// 약관상세ID
	private String termsDtlId;
	// 언어코드
	private String langCd;
	// 약관상세명
	private String termsDtlNm;
	// 약관상세내용
	private String termsDtlCont;
	// 사용여부
	private String useYn;

}
