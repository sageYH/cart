package com.app.orderExch.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.orderExch.model
* OrderExchDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class OrderExchDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 주문배송ID
	private String ordExchId;
	// 주문ID
	private String ordId;
	// 교환요청일시
	private String exchReqDtm;
	// 교환수거일시
	private String exchCollDtm;
	// 교환완료일시
	private String exchCpltDtm;
	// 수거배송비
	private Long collDelivFee;
	// 교환배송비
	private Long exchDelivFee;
	// 배송비대상코드[OD05]
	private String delivFeeTgtCd;
	// 지급사ID
	private String pymtCompId;

}
