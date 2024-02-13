package com.app.pointPaid.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.pointPaid.model
* PointPaidDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class PointPaidDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 포인트지급ID
	private String pointPaidId;
	// 포인트발급ID
	private String pointIssueId;
	// 포인트금액
	private Long pointAmt;
	// 포인트취소금액
	private Long pointCancAmt;
	// 포인트적용액
	private Long pointApplAmt;
	// 주문ID
	private String ordId;
	// 이벤트ID
	private String eventId;
	// 지급업체ID
	private String pymtCompId;

}
