package com.app.prodOptIncOg.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.prodOptIncOg.model
* ProdOptIncOgDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class ProdOptIncOgDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 옵션ID
	private String optId;
	// 옵션입출고일시
	private String optIncOgDtm;
	// 입출고구분코드[PD05]
	private String incOgDivCd;
	// 옵션입고량
	private int optIncQty;
	// 옵션출고량
	private int optOgQty;

}
