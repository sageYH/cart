package com.app.notice.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.notice.model
* NoticeDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class NoticeDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 공지ID
	private String noticeId;
	// 공지유형코드
	private String noticeTypCd;
	// 공지제목
	private String noticeTitle;
	// 공지내용
	private String noticeCont;
	// 공지조회수
	private int noticeViews;
	// 상위공지ID
	private Long prntNoticeId;
	// 그룹공지ID
	private Long grpNoticeId;
	// 공지시작일
	private String noticeFrYmd;
	// 공지종료일
	private String noticeToYmd;
	// 파일참조ID
	private String fileRefId;
	// 팝업여부
	private String popupYn;
	// 사용여부
	private String useYn;

}
