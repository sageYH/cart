package com.app.faq.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.faq.model
* FaqDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class FaqDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// FAQ_ID
	private String faqId;
	// FAQ유형코드[BB02]
	private String faqTypCd;
	// 제목
	private String faqTitle;
	// FAQ내용
	private String faqCont;
	// FAQ조회수
	private int faqViews;
	// 상위FAQ_ID
	private Long prntFaqId;
	// 그룹FAQ_ID
	private Long grpFaqId;
	// FAQ시작일
	private String faqFrYmd;
	// FAQ종료일
	private String faqToYmd;
	// 파일참조ID
	private String fileRefId;
	// 팝업여부
	private String popupYn;
	// 사용여부
	private String useYn;

}
