package com.app.countryInfo.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.countryInfo.model
* CountryInfoDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class CountryInfoDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 국가코드
	private String cntryCd;
	// 국가약어코드
	private String cntryAbbrCd;
	// 국가숫자코드
	private String cntryNumCd;
	// 국가명
	private String cntryNm;
	// 국가약어명
	private String cntryAbbrNm;
	// 국가명_언어
	private String cntryNmLng;
	// 국가약어_언어
	private String cntryAbbrNmLng;
	// 표준시
	private String stdTime;

}
