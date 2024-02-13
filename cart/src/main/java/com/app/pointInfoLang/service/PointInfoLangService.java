package com.app.pointInfoLang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.pointInfoLang.mapper.PointInfoLangMapper;
import com.app.pointInfoLang.model.PointInfoLangExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PointInfoLangService {

	@Autowired
	private PointInfoLangMapper pointInfoLangMapper;

	public int getPointInfoLangListCount(PointInfoLangExDto pointInfoLangExDto) throws Exception {
		return pointInfoLangMapper.getPointInfoLangListCount(pointInfoLangExDto);
	}

	public List<PointInfoLangExDto> getPointInfoLangList(PointInfoLangExDto pointInfoLangExDto) throws Exception {
		return pointInfoLangMapper.getPointInfoLangList(pointInfoLangExDto);
	}

	public PointInfoLangExDto getPointInfoLang(PointInfoLangExDto pointInfoLangExDto) throws Exception {
		return pointInfoLangMapper.getPointInfoLang(pointInfoLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertPointInfoLang(PointInfoLangExDto pointInfoLangExDto) throws Exception {
		pointInfoLangMapper.insertPointInfoLang(pointInfoLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updatePointInfoLang(PointInfoLangExDto pointInfoLangExDto) throws Exception {
		pointInfoLangMapper.updatePointInfoLang(pointInfoLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deletePointInfoLang(PointInfoLangExDto pointInfoLangExDto) throws Exception {
		pointInfoLangMapper.deletePointInfoLang(pointInfoLangExDto);
	}
}
