package com.app.userGrp.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.userGrp.model
* UserGrpDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class UserGrpDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 사용자그룹코드
	private String userGrpCd;
	// 사용자그룹명
	private String userGrpNm;
	// 비고
	private String userGrpDesc;
	// 사용자권한구분코드[B03]
	private String userAuthDivCd;
	// 사용여부
	private String useYn;

}
