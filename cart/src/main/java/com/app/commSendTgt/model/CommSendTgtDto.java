package com.app.commSendTgt.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.commSendTgt.model
* CommSendTgtDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class CommSendTgtDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 통신ID
	private String commSendId;
	// 멤버ID
	private String mbrId;
	// 발송완료여부
	private String sendCpltYn;

}
