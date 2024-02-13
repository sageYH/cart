package com.app.cardBill.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.cardBill.model.CardBillExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.cardBill.mapper
* CardBillMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("cardBillMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CardBillMapper  extends DaoBaseMapper {
//	public class CardBillMapper  extends DaoBaseMapper {

	public int getCardBillListCount( CardBillExDto cardBillExDto ) throws Exception {
		return (Integer)selectByPk("cardBillMapper.getCardBillListCount", cardBillExDto);
	}
	
	public List<CardBillExDto> getCardBillList( CardBillExDto cardBillExDto ) throws Exception {
		return (List<CardBillExDto>)list("cardBillMapper.getCardBillList", cardBillExDto);
	}
	
	public CardBillExDto getCardBill( CardBillExDto cardBillExDto ) throws Exception {
		return (CardBillExDto) selectByPk("cardBillMapper.getCardBill", cardBillExDto);
	}

	public void insertCardBill( CardBillExDto cardBillExDto ) throws Exception {
		insert("cardBillMapper.insertCardBill", cardBillExDto);
	}
	
	public void updateCardBill( CardBillExDto cardBillExDto ) throws Exception {
		update("cardBillMapper.updateCardBill", cardBillExDto);
	}

	public void deleteCardBill( CardBillExDto cardBillExDto ) throws Exception {
		delete("cardBillMapper.deleteCardBill", cardBillExDto);
	}

}
