package com.app.grpCode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.grpCode.mapper.GrpCodeMapper;
import com.app.grpCode.model.GrpCodeExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GrpCodeService {

	@Autowired
	private GrpCodeMapper grpCodeMapper;

	public int getGrpCodeListCount(GrpCodeExDto grpCodeExDto) throws Exception {
		return grpCodeMapper.getGrpCodeListCount(grpCodeExDto);
	}

	public List<GrpCodeExDto> getGrpCodeList(GrpCodeExDto grpCodeExDto) throws Exception {
		return grpCodeMapper.getGrpCodeList(grpCodeExDto);
	}

	public GrpCodeExDto getGrpCode(GrpCodeExDto grpCodeExDto) throws Exception {
		return grpCodeMapper.getGrpCode(grpCodeExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertGrpCode(GrpCodeExDto grpCodeExDto) throws Exception {
		grpCodeMapper.insertGrpCode(grpCodeExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateGrpCode(GrpCodeExDto grpCodeExDto) throws Exception {
		grpCodeMapper.updateGrpCode(grpCodeExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteGrpCode(GrpCodeExDto grpCodeExDto) throws Exception {
		grpCodeMapper.deleteGrpCode(grpCodeExDto);
	}
}
