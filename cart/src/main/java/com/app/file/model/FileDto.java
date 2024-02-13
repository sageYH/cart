package com.app.file.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.file.model
* FileDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class FileDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 파일ID
	private String fileId;
	// 파일참조ID
	private String fileRefId;
	// 파일명
	private String fileNm;
	// 파일크기
	private int fileSize;
	// 파일경로
	private String filePath;
	// 파일순서번호
	private int fileSortSeq;

}
