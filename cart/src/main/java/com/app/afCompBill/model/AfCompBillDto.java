package com.app.afCompBill.model;

import com.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

/**
* <pre>
* com.app.afCompBill.model
* AfCompBillDto.java
* 
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Getter
@Setter
public class AfCompBillDto extends BaseDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2665419922181909329L;
	// 제휴업체정산ID
	private String afCompBillId;
	// 제휴업체정산년월일
	private String afCompBillYmd;
	// 제휴업체ID
	private String afCompId;
	// 제휴구분코드[CP02]
	private String afDivCd;
	// 제휴정산건수
	private int afBillQty;
	// 제휴정산금액
	private Long afBillAmt;
	// 정산확정여부
	private String billCfmYn;
	// 정산확정년월일
	private String billCfmYmd;

}
