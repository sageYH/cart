package com.app.prodCont.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.prodCont.model
* ProdContDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class ProdContDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 상품ID
	private String prodId;
	// 상품내용ID
	private String prodContId;
	// 상품내용코드[PD01]
	private String prodContCd;
	// 정렬순서
	private int sortSeq;
	// 상품내용
	private String prodCont;
	// 모바일상품내용
	private String mobiProdCont;
	// 파일참조ID
	private String fileRefId;
	// 모바일파일참조ID
	private String mobiFileRefId;
	// 사용여부
	private String useYn;

}
