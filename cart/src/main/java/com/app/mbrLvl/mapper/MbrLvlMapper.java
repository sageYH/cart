package com.app.mbrLvl.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.mbrLvl.model.MbrLvlExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.mbrLvl.mapper
* MbrLvlMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("mbrLvlMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class MbrLvlMapper  extends DaoBaseMapper {
//	public class MbrLvlMapper  extends DaoBaseMapper {

	public int getMbrLvlListCount( MbrLvlExDto mbrLvlExDto ) throws Exception {
		return (Integer)selectByPk("mbrLvlMapper.getMbrLvlListCount", mbrLvlExDto);
	}
	
	public List<MbrLvlExDto> getMbrLvlList( MbrLvlExDto mbrLvlExDto ) throws Exception {
		return (List<MbrLvlExDto>)list("mbrLvlMapper.getMbrLvlList", mbrLvlExDto);
	}
	
	public MbrLvlExDto getMbrLvl( MbrLvlExDto mbrLvlExDto ) throws Exception {
		return (MbrLvlExDto) selectByPk("mbrLvlMapper.getMbrLvl", mbrLvlExDto);
	}

	public void insertMbrLvl( MbrLvlExDto mbrLvlExDto ) throws Exception {
		insert("mbrLvlMapper.insertMbrLvl", mbrLvlExDto);
	}
	
	public void updateMbrLvl( MbrLvlExDto mbrLvlExDto ) throws Exception {
		update("mbrLvlMapper.updateMbrLvl", mbrLvlExDto);
	}

	public void deleteMbrLvl( MbrLvlExDto mbrLvlExDto ) throws Exception {
		delete("mbrLvlMapper.deleteMbrLvl", mbrLvlExDto);
	}

}
