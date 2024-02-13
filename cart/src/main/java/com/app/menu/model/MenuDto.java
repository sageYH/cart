package com.app.menu.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.menu.model
* MenuDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class MenuDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 메뉴코드
	private String menuId;
	// 메뉴명
	private String menuNm;
	// 메뉴경로
	private String link;
	// 메뉴설명
	private String menuDesc;
	// 메뉴이미지
	private String menuImg;
	// 상위메뉴코드
	private String prntMenuId;
	// 메뉴레벨
	private int menuLvl;
	// 정렬순서
	private int sortSeq;
	// 표시여부
	private String displayYn;
	// 사용여부
	private String useYn;

}
