package com.app.pointInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.pointInfo.mapper.PointInfoMapper;
import com.app.pointInfo.model.PointInfoExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PointInfoService {

	@Autowired
	private PointInfoMapper pointInfoMapper;

	public int getPointInfoListCount(PointInfoExDto pointInfoExDto) throws Exception {
		return pointInfoMapper.getPointInfoListCount(pointInfoExDto);
	}

	public List<PointInfoExDto> getPointInfoList(PointInfoExDto pointInfoExDto) throws Exception {
		return pointInfoMapper.getPointInfoList(pointInfoExDto);
	}

	public PointInfoExDto getPointInfo(PointInfoExDto pointInfoExDto) throws Exception {
		return pointInfoMapper.getPointInfo(pointInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertPointInfo(PointInfoExDto pointInfoExDto) throws Exception {
		pointInfoMapper.insertPointInfo(pointInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updatePointInfo(PointInfoExDto pointInfoExDto) throws Exception {
		pointInfoMapper.updatePointInfo(pointInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deletePointInfo(PointInfoExDto pointInfoExDto) throws Exception {
		pointInfoMapper.deletePointInfo(pointInfoExDto);
	}
}
