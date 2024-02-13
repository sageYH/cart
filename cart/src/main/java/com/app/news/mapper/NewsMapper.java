package com.app.news.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.news.model.NewsExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.news.mapper
* NewsMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("newsMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class NewsMapper  extends DaoBaseMapper {
//	public class NewsMapper  extends DaoBaseMapper {

	public int getNewsListCount( NewsExDto newsExDto ) throws Exception {
		return (Integer)selectByPk("newsMapper.getNewsListCount", newsExDto);
	}
	
	public List<NewsExDto> getNewsList( NewsExDto newsExDto ) throws Exception {
		return (List<NewsExDto>)list("newsMapper.getNewsList", newsExDto);
	}
	
	public NewsExDto getNews( NewsExDto newsExDto ) throws Exception {
		return (NewsExDto) selectByPk("newsMapper.getNews", newsExDto);
	}

	public void insertNews( NewsExDto newsExDto ) throws Exception {
		insert("newsMapper.insertNews", newsExDto);
	}
	
	public void updateNews( NewsExDto newsExDto ) throws Exception {
		update("newsMapper.updateNews", newsExDto);
	}

	public void deleteNews( NewsExDto newsExDto ) throws Exception {
		delete("newsMapper.deleteNews", newsExDto);
	}

}
