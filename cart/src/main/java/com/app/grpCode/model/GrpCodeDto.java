package com.app.grpCode.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.grpCode.model
* GrpCodeDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class GrpCodeDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 그룹코드
	private String grpCd;
	// 그룹명
	private String grpNm;
	// 사용여부
	private String useYn;

}
