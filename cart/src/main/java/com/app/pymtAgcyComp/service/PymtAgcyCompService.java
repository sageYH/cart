package com.app.pymtAgcyComp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.pymtAgcyComp.mapper.PymtAgcyCompMapper;
import com.app.pymtAgcyComp.model.PymtAgcyCompExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PymtAgcyCompService {

	@Autowired
	private PymtAgcyCompMapper pymtAgcyCompMapper;

	public int getPymtAgcyCompListCount(PymtAgcyCompExDto pymtAgcyCompExDto) throws Exception {
		return pymtAgcyCompMapper.getPymtAgcyCompListCount(pymtAgcyCompExDto);
	}

	public List<PymtAgcyCompExDto> getPymtAgcyCompList(PymtAgcyCompExDto pymtAgcyCompExDto) throws Exception {
		return pymtAgcyCompMapper.getPymtAgcyCompList(pymtAgcyCompExDto);
	}

	public PymtAgcyCompExDto getPymtAgcyComp(PymtAgcyCompExDto pymtAgcyCompExDto) throws Exception {
		return pymtAgcyCompMapper.getPymtAgcyComp(pymtAgcyCompExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertPymtAgcyComp(PymtAgcyCompExDto pymtAgcyCompExDto) throws Exception {
		pymtAgcyCompMapper.insertPymtAgcyComp(pymtAgcyCompExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updatePymtAgcyComp(PymtAgcyCompExDto pymtAgcyCompExDto) throws Exception {
		pymtAgcyCompMapper.updatePymtAgcyComp(pymtAgcyCompExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deletePymtAgcyComp(PymtAgcyCompExDto pymtAgcyCompExDto) throws Exception {
		pymtAgcyCompMapper.deletePymtAgcyComp(pymtAgcyCompExDto);
	}
}
