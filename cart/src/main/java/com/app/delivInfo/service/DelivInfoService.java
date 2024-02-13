package com.app.delivInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.delivInfo.mapper.DelivInfoMapper;
import com.app.delivInfo.model.DelivInfoExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DelivInfoService {

	@Autowired
	private DelivInfoMapper delivInfoMapper;

	public int getDelivInfoListCount(DelivInfoExDto delivInfoExDto) throws Exception {
		return delivInfoMapper.getDelivInfoListCount(delivInfoExDto);
	}

	public List<DelivInfoExDto> getDelivInfoList(DelivInfoExDto delivInfoExDto) throws Exception {
		return delivInfoMapper.getDelivInfoList(delivInfoExDto);
	}

	public DelivInfoExDto getDelivInfo(DelivInfoExDto delivInfoExDto) throws Exception {
		return delivInfoMapper.getDelivInfo(delivInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertDelivInfo(DelivInfoExDto delivInfoExDto) throws Exception {
		delivInfoMapper.insertDelivInfo(delivInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateDelivInfo(DelivInfoExDto delivInfoExDto) throws Exception {
		delivInfoMapper.updateDelivInfo(delivInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteDelivInfo(DelivInfoExDto delivInfoExDto) throws Exception {
		delivInfoMapper.deleteDelivInfo(delivInfoExDto);
	}
}
