package com.app.userAuth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.userAuth.mapper.UserAuthMapper;
import com.app.userAuth.model.UserAuthExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserAuthService {

	@Autowired
	private UserAuthMapper userAuthMapper;

	public int getUserAuthListCount(UserAuthExDto userAuthExDto) throws Exception {
		return userAuthMapper.getUserAuthListCount(userAuthExDto);
	}

	public List<UserAuthExDto> getUserAuthList(UserAuthExDto userAuthExDto) throws Exception {
		return userAuthMapper.getUserAuthList(userAuthExDto);
	}

	public UserAuthExDto getUserAuth(UserAuthExDto userAuthExDto) throws Exception {
		return userAuthMapper.getUserAuth(userAuthExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertUserAuth(UserAuthExDto userAuthExDto) throws Exception {
		userAuthMapper.insertUserAuth(userAuthExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateUserAuth(UserAuthExDto userAuthExDto) throws Exception {
		userAuthMapper.updateUserAuth(userAuthExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteUserAuth(UserAuthExDto userAuthExDto) throws Exception {
		userAuthMapper.deleteUserAuth(userAuthExDto);
	}
}
