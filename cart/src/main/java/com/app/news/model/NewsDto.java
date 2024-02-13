package com.app.news.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.news.model
* NewsDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class NewsDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// NEWS_ID
	private String newsId;
	// NEWS유형코드
	private String newsTypCd;
	// 제목
	private String newsTitle;
	// NEWS내용
	private String newsCont;
	// NEWS조회수
	private int newsViews;
	// 상위NEWS_ID
	private Long prntNewsId;
	// 그룹NEWS_ID
	private Long grpNewsId;
	// NEWS시작일
	private String newsFrYmd;
	// NEWS종료일
	private String newsToYmd;
	// 파일참조ID
	private String fileRefId;
	// 팝업여부
	private String popupYn;
	// 사용여부
	private String useYn;

}
