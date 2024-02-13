package com.app.eventContLang.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.eventContLang.model
* EventContLangDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class EventContLangDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 이벤트ID
	private String eventId;
	// 이벤트내용ID
	private String eventContId;
	// 언어코드
	private String langCd;
	// 이벤트내용
	private String eventCont;
	// 이벤트모바일내용
	private String eventMobiCont;
	// 사용여부
	private String useYn;

}
