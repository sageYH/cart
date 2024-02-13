package com.app.mbrLvlBaseLang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.mbrLvlBaseLang.model.MbrLvlBaseLangExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.mbrLvlBaseLang.mapper
* MbrLvlBaseLangMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("mbrLvlBaseLangMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class MbrLvlBaseLangMapper  extends DaoBaseMapper {
//	public class MbrLvlBaseLangMapper  extends DaoBaseMapper {

	public int getMbrLvlBaseLangListCount( MbrLvlBaseLangExDto mbrLvlBaseLangExDto ) throws Exception {
		return (Integer)selectByPk("mbrLvlBaseLangMapper.getMbrLvlBaseLangListCount", mbrLvlBaseLangExDto);
	}
	
	public List<MbrLvlBaseLangExDto> getMbrLvlBaseLangList( MbrLvlBaseLangExDto mbrLvlBaseLangExDto ) throws Exception {
		return (List<MbrLvlBaseLangExDto>)list("mbrLvlBaseLangMapper.getMbrLvlBaseLangList", mbrLvlBaseLangExDto);
	}
	
	public MbrLvlBaseLangExDto getMbrLvlBaseLang( MbrLvlBaseLangExDto mbrLvlBaseLangExDto ) throws Exception {
		return (MbrLvlBaseLangExDto) selectByPk("mbrLvlBaseLangMapper.getMbrLvlBaseLang", mbrLvlBaseLangExDto);
	}

	public void insertMbrLvlBaseLang( MbrLvlBaseLangExDto mbrLvlBaseLangExDto ) throws Exception {
		insert("mbrLvlBaseLangMapper.insertMbrLvlBaseLang", mbrLvlBaseLangExDto);
	}
	
	public void updateMbrLvlBaseLang( MbrLvlBaseLangExDto mbrLvlBaseLangExDto ) throws Exception {
		update("mbrLvlBaseLangMapper.updateMbrLvlBaseLang", mbrLvlBaseLangExDto);
	}

	public void deleteMbrLvlBaseLang( MbrLvlBaseLangExDto mbrLvlBaseLangExDto ) throws Exception {
		delete("mbrLvlBaseLangMapper.deleteMbrLvlBaseLang", mbrLvlBaseLangExDto);
	}

}
