package com.app.prodClassif.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.prodClassif.model
* ProdClassifDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class ProdClassifDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 상품분류ID
	private String prodClassifId;
	// 상품분류명
	private String prodClassifNm;
	// 상위상품분류ID
	private String prntProdClassifId;
	// 상품레벨
	private int prodLvl;
	// 하위여부
	private String chdYn;
	// 파일참조ID
	private String fileRefId;
	// 모바일파일참조ID
	private String mobiFileRefId;
	// 사용여부
	private String useYn;

}
