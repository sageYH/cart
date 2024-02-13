package com.app.code.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.code.model
* CodeDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class CodeDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 코드ID
	private String cdId;
	// 코드명
	private String cdNm;
	// 그룹코드ID
	private String grpId;
	// 참조1
	private String ref1;
	// 참조2
	private String ref2;
	// 참조3
	private String ref3;
	// 정렬순서
	private int sort;
	// 사용여부
	private String useYn;
}
