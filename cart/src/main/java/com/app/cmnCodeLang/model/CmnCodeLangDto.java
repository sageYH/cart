package com.app.cmnCodeLang.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.cmnCodeLang.model
* CmnCodeLangDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class CmnCodeLangDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 공통코드
	private String cmnCd;
	// 언어코드
	private String langCd;
	// 공통코드명
	private String cmnCdNm;
	// 
	private String grpCd;
	// 참조1
	private String ref1;
	// 참조2
	private String ref2;
	// 참조3
	private String ref3;
	// 사용여부
	private String useYn;

}
