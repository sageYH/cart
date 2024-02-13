package com.app.orderExchDeliv.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.orderExchDeliv.model
* OrderExchDelivDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class OrderExchDelivDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 주문교환배송ID
	private String ordExchDelivId;
	// 주문ID
	private String ordId;
	// 수거배송업체ID
	private String collDelivCompId;
	// 수거배송비
	private Long collDelivFee;
	// 수거지급사ID
	private String collPymtCompId;
	// 수거배송여부
	private String collDelivYn;
	// 수거배송예정일
	private String collDelivExpectedYmd;
	// 수거배송일
	private String collDelivYmd;
	// 교환배송업체ID
	private String exchDelivCompId;
	// 교환배송비
	private Long exchDelivFee;
	// 교환지급사ID
	private String exchPymtCompId;
	// 교환배송여부
	private String exchDelivYn;
	// 교환배송예정일
	private String exchDelivExpectedYmd;
	// 교환배송일
	private String exchDelivYmd;

}
