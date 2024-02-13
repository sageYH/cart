package com.app.commInfoLang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.commInfoLang.model.CommInfoLangExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.commInfoLang.mapper
* CommInfoLangMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("commInfoLangMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CommInfoLangMapper  extends DaoBaseMapper {
//	public class CommInfoLangMapper  extends DaoBaseMapper {

	public int getCommInfoLangListCount( CommInfoLangExDto commInfoLangExDto ) throws Exception {
		return (Integer)selectByPk("commInfoLangMapper.getCommInfoLangListCount", commInfoLangExDto);
	}
	
	public List<CommInfoLangExDto> getCommInfoLangList( CommInfoLangExDto commInfoLangExDto ) throws Exception {
		return (List<CommInfoLangExDto>)list("commInfoLangMapper.getCommInfoLangList", commInfoLangExDto);
	}
	
	public CommInfoLangExDto getCommInfoLang( CommInfoLangExDto commInfoLangExDto ) throws Exception {
		return (CommInfoLangExDto) selectByPk("commInfoLangMapper.getCommInfoLang", commInfoLangExDto);
	}

	public void insertCommInfoLang( CommInfoLangExDto commInfoLangExDto ) throws Exception {
		insert("commInfoLangMapper.insertCommInfoLang", commInfoLangExDto);
	}
	
	public void updateCommInfoLang( CommInfoLangExDto commInfoLangExDto ) throws Exception {
		update("commInfoLangMapper.updateCommInfoLang", commInfoLangExDto);
	}

	public void deleteCommInfoLang( CommInfoLangExDto commInfoLangExDto ) throws Exception {
		delete("commInfoLangMapper.deleteCommInfoLang", commInfoLangExDto);
	}

}
