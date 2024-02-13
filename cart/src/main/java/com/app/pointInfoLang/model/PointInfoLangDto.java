package com.app.pointInfoLang.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.pointInfoLang.model
* PointInfoLangDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class PointInfoLangDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 포인트ID
	private String pointId;
	// 언어코드
	private String langCd;
	// 포인트명
	private String pointNm;
	// 사용여부
	private String useYn;

}
