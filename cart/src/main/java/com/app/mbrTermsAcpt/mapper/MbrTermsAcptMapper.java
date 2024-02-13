package com.app.mbrTermsAcpt.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.mbrTermsAcpt.model.MbrTermsAcptExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.mbrTermsAcpt.mapper
* MbrTermsAcptMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("mbrTermsAcptMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class MbrTermsAcptMapper  extends DaoBaseMapper {
//	public class MbrTermsAcptMapper  extends DaoBaseMapper {

	public int getMbrTermsAcptListCount( MbrTermsAcptExDto mbrTermsAcptExDto ) throws Exception {
		return (Integer)selectByPk("mbrTermsAcptMapper.getMbrTermsAcptListCount", mbrTermsAcptExDto);
	}
	
	public List<MbrTermsAcptExDto> getMbrTermsAcptList( MbrTermsAcptExDto mbrTermsAcptExDto ) throws Exception {
		return (List<MbrTermsAcptExDto>)list("mbrTermsAcptMapper.getMbrTermsAcptList", mbrTermsAcptExDto);
	}
	
	public MbrTermsAcptExDto getMbrTermsAcpt( MbrTermsAcptExDto mbrTermsAcptExDto ) throws Exception {
		return (MbrTermsAcptExDto) selectByPk("mbrTermsAcptMapper.getMbrTermsAcpt", mbrTermsAcptExDto);
	}

	public void insertMbrTermsAcpt( MbrTermsAcptExDto mbrTermsAcptExDto ) throws Exception {
		insert("mbrTermsAcptMapper.insertMbrTermsAcpt", mbrTermsAcptExDto);
	}
	
	public void updateMbrTermsAcpt( MbrTermsAcptExDto mbrTermsAcptExDto ) throws Exception {
		update("mbrTermsAcptMapper.updateMbrTermsAcpt", mbrTermsAcptExDto);
	}

	public void deleteMbrTermsAcpt( MbrTermsAcptExDto mbrTermsAcptExDto ) throws Exception {
		delete("mbrTermsAcptMapper.deleteMbrTermsAcpt", mbrTermsAcptExDto);
	}

}
