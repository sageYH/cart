package com.app.commSendTgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.commSendTgt.mapper.CommSendTgtMapper;
import com.app.commSendTgt.model.CommSendTgtExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CommSendTgtService {

	@Autowired
	private CommSendTgtMapper commSendTgtMapper;

	public int getCommSendTgtListCount(CommSendTgtExDto commSendTgtExDto) throws Exception {
		return commSendTgtMapper.getCommSendTgtListCount(commSendTgtExDto);
	}

	public List<CommSendTgtExDto> getCommSendTgtList(CommSendTgtExDto commSendTgtExDto) throws Exception {
		return commSendTgtMapper.getCommSendTgtList(commSendTgtExDto);
	}

	public CommSendTgtExDto getCommSendTgt(CommSendTgtExDto commSendTgtExDto) throws Exception {
		return commSendTgtMapper.getCommSendTgt(commSendTgtExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCommSendTgt(CommSendTgtExDto commSendTgtExDto) throws Exception {
		commSendTgtMapper.insertCommSendTgt(commSendTgtExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCommSendTgt(CommSendTgtExDto commSendTgtExDto) throws Exception {
		commSendTgtMapper.updateCommSendTgt(commSendTgtExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCommSendTgt(CommSendTgtExDto commSendTgtExDto) throws Exception {
		commSendTgtMapper.deleteCommSendTgt(commSendTgtExDto);
	}
}
