package com.app.userAuth.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.userAuth.model
* UserAuthDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class UserAuthDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 사용자번호
	private String userId;
	// 사용자그룹코드
	private String userGrpCd;
	// 사용여부
	private String useYn;

}
