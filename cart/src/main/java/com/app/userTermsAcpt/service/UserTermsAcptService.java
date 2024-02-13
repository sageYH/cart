package com.app.userTermsAcpt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.userTermsAcpt.mapper.UserTermsAcptMapper;
import com.app.userTermsAcpt.model.UserTermsAcptExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserTermsAcptService {

	@Autowired
	private UserTermsAcptMapper userTermsAcptMapper;

	public int getUserTermsAcptListCount(UserTermsAcptExDto userTermsAcptExDto) throws Exception {
		return userTermsAcptMapper.getUserTermsAcptListCount(userTermsAcptExDto);
	}

	public List<UserTermsAcptExDto> getUserTermsAcptList(UserTermsAcptExDto userTermsAcptExDto) throws Exception {
		return userTermsAcptMapper.getUserTermsAcptList(userTermsAcptExDto);
	}

	public UserTermsAcptExDto getUserTermsAcpt(UserTermsAcptExDto userTermsAcptExDto) throws Exception {
		return userTermsAcptMapper.getUserTermsAcpt(userTermsAcptExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertUserTermsAcpt(UserTermsAcptExDto userTermsAcptExDto) throws Exception {
		userTermsAcptMapper.insertUserTermsAcpt(userTermsAcptExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateUserTermsAcpt(UserTermsAcptExDto userTermsAcptExDto) throws Exception {
		userTermsAcptMapper.updateUserTermsAcpt(userTermsAcptExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteUserTermsAcpt(UserTermsAcptExDto userTermsAcptExDto) throws Exception {
		userTermsAcptMapper.deleteUserTermsAcpt(userTermsAcptExDto);
	}
}
