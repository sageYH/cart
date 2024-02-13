package com.app.mbrLvlBase.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.mbrLvlBase.model.MbrLvlBaseExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.mbrLvlBase.mapper
* MbrLvlBaseMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("mbrLvlBaseMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class MbrLvlBaseMapper  extends DaoBaseMapper {
//	public class MbrLvlBaseMapper  extends DaoBaseMapper {

	public int getMbrLvlBaseListCount( MbrLvlBaseExDto mbrLvlBaseExDto ) throws Exception {
		return (Integer)selectByPk("mbrLvlBaseMapper.getMbrLvlBaseListCount", mbrLvlBaseExDto);
	}
	
	public List<MbrLvlBaseExDto> getMbrLvlBaseList( MbrLvlBaseExDto mbrLvlBaseExDto ) throws Exception {
		return (List<MbrLvlBaseExDto>)list("mbrLvlBaseMapper.getMbrLvlBaseList", mbrLvlBaseExDto);
	}
	
	public MbrLvlBaseExDto getMbrLvlBase( MbrLvlBaseExDto mbrLvlBaseExDto ) throws Exception {
		return (MbrLvlBaseExDto) selectByPk("mbrLvlBaseMapper.getMbrLvlBase", mbrLvlBaseExDto);
	}

	public void insertMbrLvlBase( MbrLvlBaseExDto mbrLvlBaseExDto ) throws Exception {
		insert("mbrLvlBaseMapper.insertMbrLvlBase", mbrLvlBaseExDto);
	}
	
	public void updateMbrLvlBase( MbrLvlBaseExDto mbrLvlBaseExDto ) throws Exception {
		update("mbrLvlBaseMapper.updateMbrLvlBase", mbrLvlBaseExDto);
	}

	public void deleteMbrLvlBase( MbrLvlBaseExDto mbrLvlBaseExDto ) throws Exception {
		delete("mbrLvlBaseMapper.deleteMbrLvlBase", mbrLvlBaseExDto);
	}

}
