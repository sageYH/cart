package com.app.event.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.event.model
* EventDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class EventDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 이벤트ID
	private String eventId;
	// 이벤트제목
	private String eventTtl;
	// 이벤트유형코드
	private String eventTypCd;
	// 이벤트시작일
	private String eventFrYmd;
	// 이벤트종료일
	private String eventToYmd;
	// 사용여부
	private String useYn;

}
