package com.app.cmnCode.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.cmnCode.model
* CmnCodeDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class CmnCodeDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 공통코드
	private String cmnCd;
	// 공통코드명
	private String cmnCdNm;
	// 그룹코드
	private String grpCd;
	// 참조1
	private String ref1;
	// 참조2
	private String ref2;
	// 참조3
	private String ref3;
	// 정렬순서
	private int sortSeq;
	// 사용여부
	private String useYn;

}
