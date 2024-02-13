package com.app.productsLang.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.productsLang.model
* ProductsLangDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class ProductsLangDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 상품ID
	private String prodId;
	// 상품ID
	private String langCd;
	// 상품명
	private String prodNm;
	// 상품스킨
	private String prodSkin;
	// 상품모바일스킨
	private String prodMobiSkin;
	// 상품제조사
	private String prodMfr;
	// 상품원산지
	private String prodOrigin;
	// 상품브랜드
	private String prodBrand;
	// 상품모델
	private String prodModel;
	// 사용여부
	private String useYn;

}
