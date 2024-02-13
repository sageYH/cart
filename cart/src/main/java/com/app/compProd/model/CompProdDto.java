package com.app.compProd.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.compProd.model
* CompProdDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class CompProdDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 
	private String compProdId;
	// 업체ID
	private String compId;
	// 상품ID
	private String prodId;
	// 상품유형코드[PD06]
	private String prodTypCd;
	// 적용시작일
	private String applFrYmd;
	// 적용종료일
	private String applToYmd;
	// 광고료
	private Long adFee;
	// 사용여부
	private String useYn;

}
