package com.app.pointIssue.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.pointIssue.model
* PointIssueDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class PointIssueDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 포인트발급ID
	private String pointIssueId;
	// 포인트ID
	private String pointId;
	// 포인트내용
	private String pointCont;
	// 지급업체ID
	private String pymtCompId;
	// 포인트적용시작일
	private String pointApplFrYmd;
	// 포인트적용종료일
	private String pointApplToYmd;
	// 포인트총액
	private Long pointTotAmt;
	// 포인트금액
	private Long pointAmt;
	// 상품최소금액
	private Long prodMinAmt;
	// 주문최소금액
	private Long ordMinAmt;
	// 포인트지급총액
	private Long pointPaidTotAmt;
	// 사용여부
	private String useYn;

}
