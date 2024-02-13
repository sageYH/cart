package com.app.menuAuth.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.menuAuth.model
* MenuAuthDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class MenuAuthDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 사용자그룹코드
	private String userGrpCd;
	// 메뉴코드
	private String menuId;
	// 읽기만가능여부
	private String readOnlyYn;
	// 사용여부
	private String useYn;

}
