package com.app.userInfo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.userInfo.model.UserInfoExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.userInfo.mapper
* UserInfoMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("userInfoMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class UserInfoMapper  extends DaoBaseMapper {
//	public class UserInfoMapper  extends DaoBaseMapper {

	public int getUserInfoListCount( UserInfoExDto userInfoExDto ) throws Exception {
		return (Integer)selectByPk("userInfoMapper.getUserInfoListCount", userInfoExDto);
	}
	
	public List<UserInfoExDto> getUserInfoList( UserInfoExDto userInfoExDto ) throws Exception {
		return (List<UserInfoExDto>)list("userInfoMapper.getUserInfoList", userInfoExDto);
	}
	
	public UserInfoExDto getUserInfo( UserInfoExDto userInfoExDto ) throws Exception {
		return (UserInfoExDto) selectByPk("userInfoMapper.getUserInfo", userInfoExDto);
	}

	public void insertUserInfo( UserInfoExDto userInfoExDto ) throws Exception {
		insert("userInfoMapper.insertUserInfo", userInfoExDto);
	}
	
	public void updateUserInfo( UserInfoExDto userInfoExDto ) throws Exception {
		update("userInfoMapper.updateUserInfo", userInfoExDto);
	}

	public void deleteUserInfo( UserInfoExDto userInfoExDto ) throws Exception {
		delete("userInfoMapper.deleteUserInfo", userInfoExDto);
	}

}
