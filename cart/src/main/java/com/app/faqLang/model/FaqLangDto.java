package com.app.faqLang.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.faqLang.model
* FaqLangDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class FaqLangDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// FAQ_ID
	private String faqId;
	// 언어코드
	private String langCd;
	// 제목
	private String faqTitle;
	// FAQ내용
	private String faqCont;
	// 사용여부
	private String useYn;

}
