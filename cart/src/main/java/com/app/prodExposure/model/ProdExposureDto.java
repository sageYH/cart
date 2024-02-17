package com.app.prodExposure.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.prodExposure.model
* ProdExposureDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class ProdExposureDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 상품노출ID
	private String prodExpId;
	// 메뉴ID
	private String menuId;
	// 상품ID
	private String prodId;
	// 업체ID
	private String compId;
	// 위치정보코드[PD07]
	private String locInfoCd;
	// 노출시작일
	private String expFrYmd;
	// 노출종료일
	private String expToYmd;
	// 광고료
	private Long adFee;
	// 사용여부
	private String useYn;

}
