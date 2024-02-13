package com.app.userInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.userInfo.mapper.UserInfoMapper;
import com.app.userInfo.model.UserInfoExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	public int getUserInfoListCount(UserInfoExDto userInfoExDto) throws Exception {
		return userInfoMapper.getUserInfoListCount(userInfoExDto);
	}

	public List<UserInfoExDto> getUserInfoList(UserInfoExDto userInfoExDto) throws Exception {
		return userInfoMapper.getUserInfoList(userInfoExDto);
	}

	public UserInfoExDto getUserInfo(UserInfoExDto userInfoExDto) throws Exception {
		return userInfoMapper.getUserInfo(userInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertUserInfo(UserInfoExDto userInfoExDto) throws Exception {
		userInfoMapper.insertUserInfo(userInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateUserInfo(UserInfoExDto userInfoExDto) throws Exception {
		userInfoMapper.updateUserInfo(userInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteUserInfo(UserInfoExDto userInfoExDto) throws Exception {
		userInfoMapper.deleteUserInfo(userInfoExDto);
	}
}
