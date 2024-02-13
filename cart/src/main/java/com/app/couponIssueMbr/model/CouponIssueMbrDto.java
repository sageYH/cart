package com.app.couponIssueMbr.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.couponIssueMbr.model
* CouponIssueMbrDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class CouponIssueMbrDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 쿠폰발급ID
	private String cpnIssueId;
	// 멤버ID
	private String mbrId;
	// 쿠폰ID
	private String cpnId;
	// 쿠폰발급일시
	private String cpnIssueDtm;
	// 쿠폰사용일시
	private String cpnUseDtm;
	// 사용여부
	private String useYn;

}
