package com.app.eventLang.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.eventLang.model
* EventLangDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class EventLangDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 이벤트ID
	private String eventId;
	// 언어코드
	private String langCd;
	// 이벤트제목
	private String eventTtl;
	// 사용여부
	private String useYn;

}
