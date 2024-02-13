package com.app.qna.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.qna.model
* QnaDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class QnaDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// QNA_ID
	private String qnaId;
	// QNA유형코드
	private String qnaTypCd;
	// 제목
	private String qnaTitle;
	// QNA내용
	private String qnaCont;
	// QNA조회수
	private int qnaViews;
	// 상위QNA_ID
	private Long prntQnaId;
	// 그룹QNA_ID
	private Long grpQnaId;
	// QNA시작일
	private String qnaFrYmd;
	// QNA종료일
	private String qnaToYmd;
	// 파일참조ID
	private String fileRefId;
	// 팝업여부
	private String popupYn;
	// 사용여부
	private String useYn;

}
