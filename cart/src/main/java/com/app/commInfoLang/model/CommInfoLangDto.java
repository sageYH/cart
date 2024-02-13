package com.app.commInfoLang.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.commInfoLang.model
* CommInfoLangDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class CommInfoLangDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 통신ID
	private String commId;
	// 언어코드
	private String langCd;
	// 통신내용
	private String commCont;
	// 사용여부
	private String useYn;

}
