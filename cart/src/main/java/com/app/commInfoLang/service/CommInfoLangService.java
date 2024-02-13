package com.app.commInfoLang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.commInfoLang.mapper.CommInfoLangMapper;
import com.app.commInfoLang.model.CommInfoLangExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CommInfoLangService {

	@Autowired
	private CommInfoLangMapper commInfoLangMapper;

	public int getCommInfoLangListCount(CommInfoLangExDto commInfoLangExDto) throws Exception {
		return commInfoLangMapper.getCommInfoLangListCount(commInfoLangExDto);
	}

	public List<CommInfoLangExDto> getCommInfoLangList(CommInfoLangExDto commInfoLangExDto) throws Exception {
		return commInfoLangMapper.getCommInfoLangList(commInfoLangExDto);
	}

	public CommInfoLangExDto getCommInfoLang(CommInfoLangExDto commInfoLangExDto) throws Exception {
		return commInfoLangMapper.getCommInfoLang(commInfoLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCommInfoLang(CommInfoLangExDto commInfoLangExDto) throws Exception {
		commInfoLangMapper.insertCommInfoLang(commInfoLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCommInfoLang(CommInfoLangExDto commInfoLangExDto) throws Exception {
		commInfoLangMapper.updateCommInfoLang(commInfoLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCommInfoLang(CommInfoLangExDto commInfoLangExDto) throws Exception {
		commInfoLangMapper.deleteCommInfoLang(commInfoLangExDto);
	}
}
