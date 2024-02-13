package com.app.prodClassifLang.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.prodClassifLang.model
* ProdClassifLangDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class ProdClassifLangDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 상품분류ID
	private String prodClassifId;
	// 언어코드
	private String langCd;
	// 상품분류명
	private String prodClassifNm;
	// 사용여부
	private String useYn;

}
