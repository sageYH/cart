package com.app.mbrPymtMeth.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.mbrPymtMeth.model.MbrPymtMethExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.mbrPymtMeth.mapper
* MbrPymtMethMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("mbrPymtMethMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class MbrPymtMethMapper  extends DaoBaseMapper {
//	public class MbrPymtMethMapper  extends DaoBaseMapper {

	public int getMbrPymtMethListCount( MbrPymtMethExDto mbrPymtMethExDto ) throws Exception {
		return (Integer)selectByPk("mbrPymtMethMapper.getMbrPymtMethListCount", mbrPymtMethExDto);
	}
	
	public List<MbrPymtMethExDto> getMbrPymtMethList( MbrPymtMethExDto mbrPymtMethExDto ) throws Exception {
		return (List<MbrPymtMethExDto>)list("mbrPymtMethMapper.getMbrPymtMethList", mbrPymtMethExDto);
	}
	
	public MbrPymtMethExDto getMbrPymtMeth( MbrPymtMethExDto mbrPymtMethExDto ) throws Exception {
		return (MbrPymtMethExDto) selectByPk("mbrPymtMethMapper.getMbrPymtMeth", mbrPymtMethExDto);
	}

	public void insertMbrPymtMeth( MbrPymtMethExDto mbrPymtMethExDto ) throws Exception {
		insert("mbrPymtMethMapper.insertMbrPymtMeth", mbrPymtMethExDto);
	}
	
	public void updateMbrPymtMeth( MbrPymtMethExDto mbrPymtMethExDto ) throws Exception {
		update("mbrPymtMethMapper.updateMbrPymtMeth", mbrPymtMethExDto);
	}

	public void deleteMbrPymtMeth( MbrPymtMethExDto mbrPymtMethExDto ) throws Exception {
		delete("mbrPymtMethMapper.deleteMbrPymtMeth", mbrPymtMethExDto);
	}

}
