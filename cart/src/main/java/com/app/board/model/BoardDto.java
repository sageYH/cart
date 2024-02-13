package com.app.board.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.board.model
* BoardDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class BoardDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 게시ID
	private String boardId;
	// 게시유형코드
	private String boardTypCd;
	// 게시제목
	private String boardTitle;
	// 게시내용
	private String boardCont;
	// 게시조회수
	private int boardViews;
	// 상위게시ID
	private Long prntBoardId;
	// 그룹게시ID
	private Long grpBoardId;
	// 게시시작일
	private String boardFrYmd;
	// 게시종료일
	private String boardToYmd;
	// 파일참조ID
	private String fileRefId;
	// 팝업여부
	private String popupYn;
	// 사용여부
	private String useYn;

}
