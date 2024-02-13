package com.app.pointPaid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.pointPaid.mapper.PointPaidMapper;
import com.app.pointPaid.model.PointPaidExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PointPaidService {

	@Autowired
	private PointPaidMapper pointPaidMapper;

	public int getPointPaidListCount(PointPaidExDto pointPaidExDto) throws Exception {
		return pointPaidMapper.getPointPaidListCount(pointPaidExDto);
	}

	public List<PointPaidExDto> getPointPaidList(PointPaidExDto pointPaidExDto) throws Exception {
		return pointPaidMapper.getPointPaidList(pointPaidExDto);
	}

	public PointPaidExDto getPointPaid(PointPaidExDto pointPaidExDto) throws Exception {
		return pointPaidMapper.getPointPaid(pointPaidExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertPointPaid(PointPaidExDto pointPaidExDto) throws Exception {
		pointPaidMapper.insertPointPaid(pointPaidExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updatePointPaid(PointPaidExDto pointPaidExDto) throws Exception {
		pointPaidMapper.updatePointPaid(pointPaidExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deletePointPaid(PointPaidExDto pointPaidExDto) throws Exception {
		pointPaidMapper.deletePointPaid(pointPaidExDto);
	}
}
