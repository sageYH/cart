package com.app.grpCodeLang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.grpCodeLang.mapper.GrpCodeLangMapper;
import com.app.grpCodeLang.model.GrpCodeLangExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GrpCodeLangService {

	@Autowired
	private GrpCodeLangMapper grpCodeLangMapper;

	public int getGrpCodeLangListCount(GrpCodeLangExDto grpCodeLangExDto) throws Exception {
		return grpCodeLangMapper.getGrpCodeLangListCount(grpCodeLangExDto);
	}

	public List<GrpCodeLangExDto> getGrpCodeLangList(GrpCodeLangExDto grpCodeLangExDto) throws Exception {
		return grpCodeLangMapper.getGrpCodeLangList(grpCodeLangExDto);
	}

	public GrpCodeLangExDto getGrpCodeLang(GrpCodeLangExDto grpCodeLangExDto) throws Exception {
		return grpCodeLangMapper.getGrpCodeLang(grpCodeLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertGrpCodeLang(GrpCodeLangExDto grpCodeLangExDto) throws Exception {
		grpCodeLangMapper.insertGrpCodeLang(grpCodeLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateGrpCodeLang(GrpCodeLangExDto grpCodeLangExDto) throws Exception {
		grpCodeLangMapper.updateGrpCodeLang(grpCodeLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteGrpCodeLang(GrpCodeLangExDto grpCodeLangExDto) throws Exception {
		grpCodeLangMapper.deleteGrpCodeLang(grpCodeLangExDto);
	}
}
