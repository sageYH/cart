package com.app.prodOpt.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.prodOpt.model
* ProdOptDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class ProdOptDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 옵션ID
	private String optId;
	// 상품ID
	private String prodId;
	// 옵션명
	private String optNm;
	// 옵션유형코드[PD03]
	private String optTypCd;
	// 옵션내용
	private String optCont;
	// 모바일옵션내용
	private String mobiOptCont;
	// 파일참조ID
	private String fileRefId;
	// 모바일파일참조ID
	private String mobiFileRefId;
	// 옵션금액
	private Long optAmt;
	// 옵션재고수량
	private int optInvQty;
	// 옵션사용수량
	private int optUseQty;
	// 옵션입고수량
	private int optInQty;
	// 상위옵션ID
	private String prntOptId;
	// 하위옵션ID
	private String chdOptId;
	// 옵션레벨
	private int optLvl;
	// 하위여부
	private String chdYn;
	// 사용여부
	private String useYn;

}
