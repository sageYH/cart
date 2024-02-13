package com.app.eventCont.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.eventCont.model
* EventContDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class EventContDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 이벤트ID
	private String eventId;
	// 이벤트내용ID
	private String eventContId;
	// 이벤트내용코드[EV02]
	private String eventContCd;
	// 이벤트순서
	private int sortSeq;
	// 이벤트내용
	private String eventCont;
	// 이벤트모바일내용
	private String eventMobiCont;
	// 파일참조ID
	private String fileRefId;
	// 모바일파일참조ID
	private String mobiFileRefId;
	// 사용여부
	private String useYn;

}
