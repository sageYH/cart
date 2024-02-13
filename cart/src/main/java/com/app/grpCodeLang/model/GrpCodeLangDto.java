package com.app.grpCodeLang.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.grpCodeLang.model
* GrpCodeLangDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class GrpCodeLangDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 그룹코드
	private String grpCd;
	// 언어코드
	private String langCd;
	// 그룹명
	private String grpNm;
	// 사용여부
	private String useYn;

}
