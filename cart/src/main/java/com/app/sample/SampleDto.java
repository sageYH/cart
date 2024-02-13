package com.app.sample;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleDto extends BaseDto {

	private String deptCd;
	private String deptNm;
	private String upDeptCd;

}
