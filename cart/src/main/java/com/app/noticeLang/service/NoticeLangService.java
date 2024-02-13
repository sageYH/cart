package com.app.noticeLang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.noticeLang.mapper.NoticeLangMapper;
import com.app.noticeLang.model.NoticeLangExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class NoticeLangService {

	@Autowired
	private NoticeLangMapper noticeLangMapper;

	public int getNoticeLangListCount(NoticeLangExDto noticeLangExDto) throws Exception {
		return noticeLangMapper.getNoticeLangListCount(noticeLangExDto);
	}

	public List<NoticeLangExDto> getNoticeLangList(NoticeLangExDto noticeLangExDto) throws Exception {
		return noticeLangMapper.getNoticeLangList(noticeLangExDto);
	}

	public NoticeLangExDto getNoticeLang(NoticeLangExDto noticeLangExDto) throws Exception {
		return noticeLangMapper.getNoticeLang(noticeLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertNoticeLang(NoticeLangExDto noticeLangExDto) throws Exception {
		noticeLangMapper.insertNoticeLang(noticeLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateNoticeLang(NoticeLangExDto noticeLangExDto) throws Exception {
		noticeLangMapper.updateNoticeLang(noticeLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteNoticeLang(NoticeLangExDto noticeLangExDto) throws Exception {
		noticeLangMapper.deleteNoticeLang(noticeLangExDto);
	}
}
