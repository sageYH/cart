package com.app.commSend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.commSend.mapper.CommSendMapper;
import com.app.commSend.model.CommSendExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CommSendService {

	@Autowired
	private CommSendMapper commSendMapper;

	public int getCommSendListCount(CommSendExDto commSendExDto) throws Exception {
		return commSendMapper.getCommSendListCount(commSendExDto);
	}

	public List<CommSendExDto> getCommSendList(CommSendExDto commSendExDto) throws Exception {
		return commSendMapper.getCommSendList(commSendExDto);
	}

	public CommSendExDto getCommSend(CommSendExDto commSendExDto) throws Exception {
		return commSendMapper.getCommSend(commSendExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCommSend(CommSendExDto commSendExDto) throws Exception {
		commSendMapper.insertCommSend(commSendExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCommSend(CommSendExDto commSendExDto) throws Exception {
		commSendMapper.updateCommSend(commSendExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCommSend(CommSendExDto commSendExDto) throws Exception {
		commSendMapper.deleteCommSend(commSendExDto);
	}
}
