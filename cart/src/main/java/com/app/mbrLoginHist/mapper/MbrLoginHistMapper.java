package com.app.mbrLoginHist.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.mbrLoginHist.model.MbrLoginHistExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.mbrLoginHist.mapper
* MbrLoginHistMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("mbrLoginHistMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class MbrLoginHistMapper  extends DaoBaseMapper {
//	public class MbrLoginHistMapper  extends DaoBaseMapper {

	public int getMbrLoginHistListCount( MbrLoginHistExDto mbrLoginHistExDto ) throws Exception {
		return (Integer)selectByPk("mbrLoginHistMapper.getMbrLoginHistListCount", mbrLoginHistExDto);
	}
	
	public List<MbrLoginHistExDto> getMbrLoginHistList( MbrLoginHistExDto mbrLoginHistExDto ) throws Exception {
		return (List<MbrLoginHistExDto>)list("mbrLoginHistMapper.getMbrLoginHistList", mbrLoginHistExDto);
	}
	
	public MbrLoginHistExDto getMbrLoginHist( MbrLoginHistExDto mbrLoginHistExDto ) throws Exception {
		return (MbrLoginHistExDto) selectByPk("mbrLoginHistMapper.getMbrLoginHist", mbrLoginHistExDto);
	}

	public void insertMbrLoginHist( MbrLoginHistExDto mbrLoginHistExDto ) throws Exception {
		insert("mbrLoginHistMapper.insertMbrLoginHist", mbrLoginHistExDto);
	}
	
	public void updateMbrLoginHist( MbrLoginHistExDto mbrLoginHistExDto ) throws Exception {
		update("mbrLoginHistMapper.updateMbrLoginHist", mbrLoginHistExDto);
	}

	public void deleteMbrLoginHist( MbrLoginHistExDto mbrLoginHistExDto ) throws Exception {
		delete("mbrLoginHistMapper.deleteMbrLoginHist", mbrLoginHistExDto);
	}

}
