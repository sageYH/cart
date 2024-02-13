package com.app.userCert.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.userCert.model.UserCertExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.userCert.mapper
* UserCertMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("userCertMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class UserCertMapper  extends DaoBaseMapper {
//	public class UserCertMapper  extends DaoBaseMapper {

	public int getUserCertListCount( UserCertExDto userCertExDto ) throws Exception {
		return (Integer)selectByPk("userCertMapper.getUserCertListCount", userCertExDto);
	}
	
	public List<UserCertExDto> getUserCertList( UserCertExDto userCertExDto ) throws Exception {
		return (List<UserCertExDto>)list("userCertMapper.getUserCertList", userCertExDto);
	}
	
	public UserCertExDto getUserCert( UserCertExDto userCertExDto ) throws Exception {
		return (UserCertExDto) selectByPk("userCertMapper.getUserCert", userCertExDto);
	}

	public void insertUserCert( UserCertExDto userCertExDto ) throws Exception {
		insert("userCertMapper.insertUserCert", userCertExDto);
	}
	
	public void updateUserCert( UserCertExDto userCertExDto ) throws Exception {
		update("userCertMapper.updateUserCert", userCertExDto);
	}

	public void deleteUserCert( UserCertExDto userCertExDto ) throws Exception {
		delete("userCertMapper.deleteUserCert", userCertExDto);
	}

}
