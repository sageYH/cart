package com.app.newsLang.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.newsLang.model
* NewsLangDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class NewsLangDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// NEWS_ID
	private String newsId;
	// 언어코드
	private String langCd;
	// 제목
	private String newsTitle;
	// NEWS내용
	private String newsCont;
	// 사용여부
	private String useYn;

}
