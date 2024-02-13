package com.app.delivInfo.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.delivInfo.model
* DelivInfoDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class DelivInfoDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 배송정보ID
	private String delivInfoId;
	// 멤버ID
	private String mbrId;
	// 배송지명
	private String delivAddrNm;
	// 기본주소여부
	private String baseAddrYn;
	// 수령인명
	private String rcptNm;
	// 수령인전화
	private String rcptTelNo;
	// 수령인폰번호
	private String rcptPhoneNo;
	// 배송우편번호
	private String delivZipCd;
	// 배송주소
	private String delivAddr;
	// 배송상세주소
	private String delivDtlAddr;
	// 배송상세주소2
	private String delivDtlAddr2;
	// 배송참조내용
	private String delivRefCont;
	// 정렬순서
	private int sortSeq;
	// 사용여부
	private String useYn;

}
