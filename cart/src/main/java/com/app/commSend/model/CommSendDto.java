package com.app.commSend.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.commSend.model
* CommSendDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class CommSendDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 통신ID
	private String commSendId;
	// 통신ID
	private String commId;
	// 통신예정일
	private String commExpectedYmd;
	// 통신완료여부
	private String commCpltYn;
	// 사용여부
	private String useYn;

}
