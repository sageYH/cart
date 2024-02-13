package com.app.cardBill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.cardBill.mapper.CardBillMapper;
import com.app.cardBill.model.CardBillExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CardBillService {

	@Autowired
	private CardBillMapper cardBillMapper;

	public int getCardBillListCount(CardBillExDto cardBillExDto) throws Exception {
		return cardBillMapper.getCardBillListCount(cardBillExDto);
	}

	public List<CardBillExDto> getCardBillList(CardBillExDto cardBillExDto) throws Exception {
		return cardBillMapper.getCardBillList(cardBillExDto);
	}

	public CardBillExDto getCardBill(CardBillExDto cardBillExDto) throws Exception {
		return cardBillMapper.getCardBill(cardBillExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCardBill(CardBillExDto cardBillExDto) throws Exception {
		cardBillMapper.insertCardBill(cardBillExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCardBill(CardBillExDto cardBillExDto) throws Exception {
		cardBillMapper.updateCardBill(cardBillExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCardBill(CardBillExDto cardBillExDto) throws Exception {
		cardBillMapper.deleteCardBill(cardBillExDto);
	}
}
