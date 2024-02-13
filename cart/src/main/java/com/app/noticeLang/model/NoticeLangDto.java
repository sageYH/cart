package com.app.noticeLang.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.noticeLang.model
* NoticeLangDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class NoticeLangDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 공지ID
	private String noticeId;
	// 언어코드
	private String langCd;
	// 공지제목
	private String noticeTitle;
	// 공지내용
	private String noticeCont;
	// 사용여부
	private String useYn;

}
