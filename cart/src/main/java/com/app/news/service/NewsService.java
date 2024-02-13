package com.app.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.news.mapper.NewsMapper;
import com.app.news.model.NewsExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class NewsService {

	@Autowired
	private NewsMapper newsMapper;

	public int getNewsListCount(NewsExDto newsExDto) throws Exception {
		return newsMapper.getNewsListCount(newsExDto);
	}

	public List<NewsExDto> getNewsList(NewsExDto newsExDto) throws Exception {
		return newsMapper.getNewsList(newsExDto);
	}

	public NewsExDto getNews(NewsExDto newsExDto) throws Exception {
		return newsMapper.getNews(newsExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertNews(NewsExDto newsExDto) throws Exception {
		newsMapper.insertNews(newsExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateNews(NewsExDto newsExDto) throws Exception {
		newsMapper.updateNews(newsExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteNews(NewsExDto newsExDto) throws Exception {
		newsMapper.deleteNews(newsExDto);
	}
}
