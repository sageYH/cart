package com.app.company.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.company.model
* CompanyDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class CompanyDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 업체ID
	private String compId;
	// 업체명
	private String compNm;
	// 국가코드
	private String cntryCd;
	// 업체구분코드
	private String cpDivCd;
	// 사업자등록번호
	private String bizRegNo;
	// 대표자명
	private String repNm;
	// 업태
	private String bizTyp;
	// 종목
	private String bizItem;
	// 우편번호
	private String zipCd;
	// 주소
	private String addr;
	// 상세주소2
	private String dtlAddr;
	// 
	private String dtlAddr2;
	// 담당자명
	private String picNm;
	// 담당자폰번호
	private String picPhoneNo;
	// 본사여부
	private String hqYn;
	// 입점사여부
	private String storeYn;
	// 제휴사여부
	private String afCpYn;
	// 가맹점여부
	private String frchseYn;
	// 고객사여부
	private String custCpYn;
	// 기관여부
	private String orgYn;

}
