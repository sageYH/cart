package com.app.afCompBill.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.afCompBill.model.AfCompBillExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.afCompBill.mapper
* AfCompBillMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("afCompBillMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class AfCompBillMapper  extends DaoBaseMapper {
//	public class AfCompBillMapper  extends DaoBaseMapper {

	public int getAfCompBillListCount( AfCompBillExDto afCompBillExDto ) throws Exception {
		return (Integer)selectByPk("afCompBillMapper.getAfCompBillListCount", afCompBillExDto);
	}
	
	public List<AfCompBillExDto> getAfCompBillList( AfCompBillExDto afCompBillExDto ) throws Exception {
		return (List<AfCompBillExDto>)list("afCompBillMapper.getAfCompBillList", afCompBillExDto);
	}
	
	public AfCompBillExDto getAfCompBill( AfCompBillExDto afCompBillExDto ) throws Exception {
		return (AfCompBillExDto) selectByPk("afCompBillMapper.getAfCompBill", afCompBillExDto);
	}

	public void insertAfCompBill( AfCompBillExDto afCompBillExDto ) throws Exception {
		insert("afCompBillMapper.insertAfCompBill", afCompBillExDto);
	}
	
	public void updateAfCompBill( AfCompBillExDto afCompBillExDto ) throws Exception {
		update("afCompBillMapper.updateAfCompBill", afCompBillExDto);
	}

	public void deleteAfCompBill( AfCompBillExDto afCompBillExDto ) throws Exception {
		delete("afCompBillMapper.deleteAfCompBill", afCompBillExDto);
	}

}
