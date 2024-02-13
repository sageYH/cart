package com.app.newsLang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.newsLang.model.NewsLangExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.newsLang.mapper
* NewsLangMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("newsLangMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class NewsLangMapper  extends DaoBaseMapper {
//	public class NewsLangMapper  extends DaoBaseMapper {

	public int getNewsLangListCount( NewsLangExDto newsLangExDto ) throws Exception {
		return (Integer)selectByPk("newsLangMapper.getNewsLangListCount", newsLangExDto);
	}
	
	public List<NewsLangExDto> getNewsLangList( NewsLangExDto newsLangExDto ) throws Exception {
		return (List<NewsLangExDto>)list("newsLangMapper.getNewsLangList", newsLangExDto);
	}
	
	public NewsLangExDto getNewsLang( NewsLangExDto newsLangExDto ) throws Exception {
		return (NewsLangExDto) selectByPk("newsLangMapper.getNewsLang", newsLangExDto);
	}

	public void insertNewsLang( NewsLangExDto newsLangExDto ) throws Exception {
		insert("newsLangMapper.insertNewsLang", newsLangExDto);
	}
	
	public void updateNewsLang( NewsLangExDto newsLangExDto ) throws Exception {
		update("newsLangMapper.updateNewsLang", newsLangExDto);
	}

	public void deleteNewsLang( NewsLangExDto newsLangExDto ) throws Exception {
		delete("newsLangMapper.deleteNewsLang", newsLangExDto);
	}

}
