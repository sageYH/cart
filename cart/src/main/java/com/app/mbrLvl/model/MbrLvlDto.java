package com.app.mbrLvl.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.mbrLvl.model
* MbrLvlDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class MbrLvlDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 적용년월일
	private String applYmd;
	// 멤버ID
	private String mbrId;
	// 멤버등급코드
	private String mbrLvlCd;
	// 사용여부
	private String useYn;

}
