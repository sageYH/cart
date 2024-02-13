package com.app.userTermsAcpt.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.userTermsAcpt.model.UserTermsAcptExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.userTermsAcpt.mapper
* UserTermsAcptMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("userTermsAcptMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class UserTermsAcptMapper  extends DaoBaseMapper {
//	public class UserTermsAcptMapper  extends DaoBaseMapper {

	public int getUserTermsAcptListCount( UserTermsAcptExDto userTermsAcptExDto ) throws Exception {
		return (Integer)selectByPk("userTermsAcptMapper.getUserTermsAcptListCount", userTermsAcptExDto);
	}
	
	public List<UserTermsAcptExDto> getUserTermsAcptList( UserTermsAcptExDto userTermsAcptExDto ) throws Exception {
		return (List<UserTermsAcptExDto>)list("userTermsAcptMapper.getUserTermsAcptList", userTermsAcptExDto);
	}
	
	public UserTermsAcptExDto getUserTermsAcpt( UserTermsAcptExDto userTermsAcptExDto ) throws Exception {
		return (UserTermsAcptExDto) selectByPk("userTermsAcptMapper.getUserTermsAcpt", userTermsAcptExDto);
	}

	public void insertUserTermsAcpt( UserTermsAcptExDto userTermsAcptExDto ) throws Exception {
		insert("userTermsAcptMapper.insertUserTermsAcpt", userTermsAcptExDto);
	}
	
	public void updateUserTermsAcpt( UserTermsAcptExDto userTermsAcptExDto ) throws Exception {
		update("userTermsAcptMapper.updateUserTermsAcpt", userTermsAcptExDto);
	}

	public void deleteUserTermsAcpt( UserTermsAcptExDto userTermsAcptExDto ) throws Exception {
		delete("userTermsAcptMapper.deleteUserTermsAcpt", userTermsAcptExDto);
	}

}
