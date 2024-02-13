package com.app.mbrLvlBase.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.mbrLvlBase.model
* MbrLvlBaseDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class MbrLvlBaseDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 멤버등급코드
	private String mbrLvlCd;
	// 가입조건내역
	private String signupCondDets;
	// 혜택내용
	private String benfCont;
	// 사용여부
	private String useYn;

}
