package com.app.prodContLang.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.prodContLang.model
* ProdContLangDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class ProdContLangDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 상품ID
	private String prodId;
	// 상품내용ID
	private String prodContId;
	// 언어코드
	private String langCd;
	// 상품내용
	private String prodCont;
	// 모바일상품내용
	private String mobiProdCont;
	// 사용여부
	private String useYn;

}
