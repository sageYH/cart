package com.app.pointInfo.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.pointInfo.model
* PointInfoDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class PointInfoDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 포인트ID
	private String pointId;
	// 포인트종류코드[PS01]
	private String pointKindCd;
	// 포인트명
	private String pointNm;
	// 상시여부
	private String alwaysYn;
	// 선착순여부
	private String firstComeYn;
	// 중복지급여부
	private String dupePymtYn;
	// 사용여부
	private String useYn;

}
