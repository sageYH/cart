package com.base;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDto {
//	public class BaseDto implements Serializable {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2285869383719601529L;

	private String regUserId;
	private Timestamp regDtm;
	private String regDtmStr;
	private String updUserId;
	private Timestamp updDtm;
	private String updDtmStr;

	private Integer pageRows = 20;
	private Integer pageIdx = 1;
	private Integer pageOffset = 0;
	private Integer startRow;
	private Integer endRow;

	public Integer getStartRow(){
		setStartRow(this.pageRows * (this.pageIdx - 1) + this.pageOffset);
		return this.startRow;
	}
	
	public void setStartRow(Integer startRow){
		this.startRow = startRow;
	}

	public Integer getEndRow(){
		setEndRow((this.pageRows * (this.pageIdx - 1)) + (this.pageRows - 1) + this.pageOffset);
		return this.endRow;
	}
	
	public void setEndRow(Integer endRow){
		this.endRow = endRow;
	}
}
