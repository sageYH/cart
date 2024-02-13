package com.app.pointIssue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.pointIssue.mapper.PointIssueMapper;
import com.app.pointIssue.model.PointIssueExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PointIssueService {

	@Autowired
	private PointIssueMapper pointIssueMapper;

	public int getPointIssueListCount(PointIssueExDto pointIssueExDto) throws Exception {
		return pointIssueMapper.getPointIssueListCount(pointIssueExDto);
	}

	public List<PointIssueExDto> getPointIssueList(PointIssueExDto pointIssueExDto) throws Exception {
		return pointIssueMapper.getPointIssueList(pointIssueExDto);
	}

	public PointIssueExDto getPointIssue(PointIssueExDto pointIssueExDto) throws Exception {
		return pointIssueMapper.getPointIssue(pointIssueExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertPointIssue(PointIssueExDto pointIssueExDto) throws Exception {
		pointIssueMapper.insertPointIssue(pointIssueExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updatePointIssue(PointIssueExDto pointIssueExDto) throws Exception {
		pointIssueMapper.updatePointIssue(pointIssueExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deletePointIssue(PointIssueExDto pointIssueExDto) throws Exception {
		pointIssueMapper.deletePointIssue(pointIssueExDto);
	}
}
