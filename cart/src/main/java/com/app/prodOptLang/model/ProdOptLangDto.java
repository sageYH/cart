package com.app.prodOptLang.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.prodOptLang.model
* ProdOptLangDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class ProdOptLangDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 옵션ID
	private String optId;
	// 언어코드
	private String langCd;
	// 옵션명
	private String optNm;
	// 옵션내용
	private String optCont;
	// 모바일옵션내용
	private String mobiOptCont;
	// 사용여부
	private String useYn;

}
