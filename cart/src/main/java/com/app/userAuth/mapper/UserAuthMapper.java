package com.app.userAuth.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.userAuth.model.UserAuthExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.userAuth.mapper
* UserAuthMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("userAuthMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class UserAuthMapper  extends DaoBaseMapper {
//	public class UserAuthMapper  extends DaoBaseMapper {

	public int getUserAuthListCount( UserAuthExDto userAuthExDto ) throws Exception {
		return (Integer)selectByPk("userAuthMapper.getUserAuthListCount", userAuthExDto);
	}
	
	public List<UserAuthExDto> getUserAuthList( UserAuthExDto userAuthExDto ) throws Exception {
		return (List<UserAuthExDto>)list("userAuthMapper.getUserAuthList", userAuthExDto);
	}
	
	public UserAuthExDto getUserAuth( UserAuthExDto userAuthExDto ) throws Exception {
		return (UserAuthExDto) selectByPk("userAuthMapper.getUserAuth", userAuthExDto);
	}

	public void insertUserAuth( UserAuthExDto userAuthExDto ) throws Exception {
		insert("userAuthMapper.insertUserAuth", userAuthExDto);
	}
	
	public void updateUserAuth( UserAuthExDto userAuthExDto ) throws Exception {
		update("userAuthMapper.updateUserAuth", userAuthExDto);
	}

	public void deleteUserAuth( UserAuthExDto userAuthExDto ) throws Exception {
		delete("userAuthMapper.deleteUserAuth", userAuthExDto);
	}

}
