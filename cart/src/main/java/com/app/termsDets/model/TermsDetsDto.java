package com.app.termsDets.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.termsDets.model
* TermsDetsDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class TermsDetsDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 약관ID
	private String termsId;
	// 약관상세ID
	private String termsDtlId;
	// 약관상세명
	private String termsDtlNm;
	// 약관상세내용
	private String termsDtlCont;
	// 동의구분코드[TM03]
	private String acptDivCd;
	// 사용여부
	private String useYn;

}
