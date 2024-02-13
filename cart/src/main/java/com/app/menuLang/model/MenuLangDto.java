package com.app.menuLang.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.menuLang.model
* MenuLangDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class MenuLangDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 메뉴코드
	private String menuId;
	// 언어코드
	private String langCd;
	// 메뉴명
	private String menuNm;
	// 메뉴설명
	private String menuDesc;
	// 사용여부
	private String useYn;

}
