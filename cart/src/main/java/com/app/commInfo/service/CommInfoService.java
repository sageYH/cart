package com.app.commInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.commInfo.mapper.CommInfoMapper;
import com.app.commInfo.model.CommInfoExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CommInfoService {

	@Autowired
	private CommInfoMapper commInfoMapper;

	public int getCommInfoListCount(CommInfoExDto commInfoExDto) throws Exception {
		return commInfoMapper.getCommInfoListCount(commInfoExDto);
	}

	public List<CommInfoExDto> getCommInfoList(CommInfoExDto commInfoExDto) throws Exception {
		return commInfoMapper.getCommInfoList(commInfoExDto);
	}

	public CommInfoExDto getCommInfo(CommInfoExDto commInfoExDto) throws Exception {
		return commInfoMapper.getCommInfo(commInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCommInfo(CommInfoExDto commInfoExDto) throws Exception {
		commInfoMapper.insertCommInfo(commInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCommInfo(CommInfoExDto commInfoExDto) throws Exception {
		commInfoMapper.updateCommInfo(commInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCommInfo(CommInfoExDto commInfoExDto) throws Exception {
		commInfoMapper.deleteCommInfo(commInfoExDto);
	}
}
