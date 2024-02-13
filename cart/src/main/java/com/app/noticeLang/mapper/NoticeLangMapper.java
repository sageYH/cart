package com.app.noticeLang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.noticeLang.model.NoticeLangExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.noticeLang.mapper
* NoticeLangMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("noticeLangMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class NoticeLangMapper  extends DaoBaseMapper {
//	public class NoticeLangMapper  extends DaoBaseMapper {

	public int getNoticeLangListCount( NoticeLangExDto noticeLangExDto ) throws Exception {
		return (Integer)selectByPk("noticeLangMapper.getNoticeLangListCount", noticeLangExDto);
	}
	
	public List<NoticeLangExDto> getNoticeLangList( NoticeLangExDto noticeLangExDto ) throws Exception {
		return (List<NoticeLangExDto>)list("noticeLangMapper.getNoticeLangList", noticeLangExDto);
	}
	
	public NoticeLangExDto getNoticeLang( NoticeLangExDto noticeLangExDto ) throws Exception {
		return (NoticeLangExDto) selectByPk("noticeLangMapper.getNoticeLang", noticeLangExDto);
	}

	public void insertNoticeLang( NoticeLangExDto noticeLangExDto ) throws Exception {
		insert("noticeLangMapper.insertNoticeLang", noticeLangExDto);
	}
	
	public void updateNoticeLang( NoticeLangExDto noticeLangExDto ) throws Exception {
		update("noticeLangMapper.updateNoticeLang", noticeLangExDto);
	}

	public void deleteNoticeLang( NoticeLangExDto noticeLangExDto ) throws Exception {
		delete("noticeLangMapper.deleteNoticeLang", noticeLangExDto);
	}

}
