package com.app.afCompBill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.afCompBill.mapper.AfCompBillMapper;
import com.app.afCompBill.model.AfCompBillExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AfCompBillService {

	@Autowired
	private AfCompBillMapper afCompBillMapper;

	public int getAfCompBillListCount(AfCompBillExDto afCompBillExDto) throws Exception {
		return afCompBillMapper.getAfCompBillListCount(afCompBillExDto);
	}

	public List<AfCompBillExDto> getAfCompBillList(AfCompBillExDto afCompBillExDto) throws Exception {
		return afCompBillMapper.getAfCompBillList(afCompBillExDto);
	}

	public AfCompBillExDto getAfCompBill(AfCompBillExDto afCompBillExDto) throws Exception {
		return afCompBillMapper.getAfCompBill(afCompBillExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertAfCompBill(AfCompBillExDto afCompBillExDto) throws Exception {
		afCompBillMapper.insertAfCompBill(afCompBillExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateAfCompBill(AfCompBillExDto afCompBillExDto) throws Exception {
		afCompBillMapper.updateAfCompBill(afCompBillExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteAfCompBill(AfCompBillExDto afCompBillExDto) throws Exception {
		afCompBillMapper.deleteAfCompBill(afCompBillExDto);
	}
}
