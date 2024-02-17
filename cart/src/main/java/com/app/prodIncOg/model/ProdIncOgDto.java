package com.app.prodIncOg.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.prodIncOg.model
* ProdIncOgDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class ProdIncOgDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 상품입출고ID
	private String prodIncOgId;
	// 상품ID
	private String prodId;
	// 업체ID
	private String compId;
	// 상품입출고일시
	private String prodIncOgDtm;
	// 입출고구분코드[PD05]
	private String incOgDivCd;
	// 상품입고량
	private int prodIncQty;
	// 상품출고량
	private int prodOgQty;

}
