package com.app.products.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.products.model
* ProductsDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class ProductsDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 상품ID
	private String prodId;
	// 상품명
	private String prodNm;
	// 상위상품분류ID
	private String prntProdId;
	// 회사코드
	private String compId;
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
	// 상품무게
	private Double prodWeight;
	// 상품포함수량
	private int prodInclQty;
	// 사용여부
	private String useYn;

}
