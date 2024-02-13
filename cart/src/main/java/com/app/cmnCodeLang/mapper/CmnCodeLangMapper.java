package com.app.cmnCodeLang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.cmnCodeLang.model.CmnCodeLangExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.cmnCodeLang.mapper
* CmnCodeLangMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("cmnCodeLangMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CmnCodeLangMapper  extends DaoBaseMapper {
//	public class CmnCodeLangMapper  extends DaoBaseMapper {

	public int getCmnCodeLangListCount( CmnCodeLangExDto cmnCodeLangExDto ) throws Exception {
		return (Integer)selectByPk("cmnCodeLangMapper.getCmnCodeLangListCount", cmnCodeLangExDto);
	}
	
	public List<CmnCodeLangExDto> getCmnCodeLangList( CmnCodeLangExDto cmnCodeLangExDto ) throws Exception {
		return (List<CmnCodeLangExDto>)list("cmnCodeLangMapper.getCmnCodeLangList", cmnCodeLangExDto);
	}
	
	public CmnCodeLangExDto getCmnCodeLang( CmnCodeLangExDto cmnCodeLangExDto ) throws Exception {
		return (CmnCodeLangExDto) selectByPk("cmnCodeLangMapper.getCmnCodeLang", cmnCodeLangExDto);
	}

	public void insertCmnCodeLang( CmnCodeLangExDto cmnCodeLangExDto ) throws Exception {
		insert("cmnCodeLangMapper.insertCmnCodeLang", cmnCodeLangExDto);
	}
	
	public void updateCmnCodeLang( CmnCodeLangExDto cmnCodeLangExDto ) throws Exception {
		update("cmnCodeLangMapper.updateCmnCodeLang", cmnCodeLangExDto);
	}

	public void deleteCmnCodeLang( CmnCodeLangExDto cmnCodeLangExDto ) throws Exception {
		delete("cmnCodeLangMapper.deleteCmnCodeLang", cmnCodeLangExDto);
	}

}
