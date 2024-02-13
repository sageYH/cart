package com.app.newsLang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.newsLang.mapper.NewsLangMapper;
import com.app.newsLang.model.NewsLangExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class NewsLangService {

	@Autowired
	private NewsLangMapper newsLangMapper;

	public int getNewsLangListCount(NewsLangExDto newsLangExDto) throws Exception {
		return newsLangMapper.getNewsLangListCount(newsLangExDto);
	}

	public List<NewsLangExDto> getNewsLangList(NewsLangExDto newsLangExDto) throws Exception {
		return newsLangMapper.getNewsLangList(newsLangExDto);
	}

	public NewsLangExDto getNewsLang(NewsLangExDto newsLangExDto) throws Exception {
		return newsLangMapper.getNewsLang(newsLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertNewsLang(NewsLangExDto newsLangExDto) throws Exception {
		newsLangMapper.insertNewsLang(newsLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateNewsLang(NewsLangExDto newsLangExDto) throws Exception {
		newsLangMapper.updateNewsLang(newsLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteNewsLang(NewsLangExDto newsLangExDto) throws Exception {
		newsLangMapper.deleteNewsLang(newsLangExDto);
	}
}
