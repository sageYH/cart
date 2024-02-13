package com.app.commInfo.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.commInfo.model
* CommInfoDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class CommInfoDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 통신ID
	private String commId;
	// 통신경로코드[CM01]
	private String commPathCd;
	// 통신구분코드[CM02]
	private String commDivCd;
	// 통신내용
	private String commCont;
	// 사용여부
	private String useYn;

}
