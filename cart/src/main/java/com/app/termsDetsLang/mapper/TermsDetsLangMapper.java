package com.app.termsDetsLang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.termsDetsLang.model.TermsDetsLangExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.termsDetsLang.mapper
* TermsDetsLangMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("termsDetsLangMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class TermsDetsLangMapper  extends DaoBaseMapper {
//	public class TermsDetsLangMapper  extends DaoBaseMapper {

	public int getTermsDetsLangListCount( TermsDetsLangExDto termsDetsLangExDto ) throws Exception {
		return (Integer)selectByPk("termsDetsLangMapper.getTermsDetsLangListCount", termsDetsLangExDto);
	}
	
	public List<TermsDetsLangExDto> getTermsDetsLangList( TermsDetsLangExDto termsDetsLangExDto ) throws Exception {
		return (List<TermsDetsLangExDto>)list("termsDetsLangMapper.getTermsDetsLangList", termsDetsLangExDto);
	}
	
	public TermsDetsLangExDto getTermsDetsLang( TermsDetsLangExDto termsDetsLangExDto ) throws Exception {
		return (TermsDetsLangExDto) selectByPk("termsDetsLangMapper.getTermsDetsLang", termsDetsLangExDto);
	}

	public void insertTermsDetsLang( TermsDetsLangExDto termsDetsLangExDto ) throws Exception {
		insert("termsDetsLangMapper.insertTermsDetsLang", termsDetsLangExDto);
	}
	
	public void updateTermsDetsLang( TermsDetsLangExDto termsDetsLangExDto ) throws Exception {
		update("termsDetsLangMapper.updateTermsDetsLang", termsDetsLangExDto);
	}

	public void deleteTermsDetsLang( TermsDetsLangExDto termsDetsLangExDto ) throws Exception {
		delete("termsDetsLangMapper.deleteTermsDetsLang", termsDetsLangExDto);
	}

}
