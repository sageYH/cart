package com.app.userCert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.userCert.mapper.UserCertMapper;
import com.app.userCert.model.UserCertExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserCertService {

	@Autowired
	private UserCertMapper userCertMapper;

	public int getUserCertListCount(UserCertExDto userCertExDto) throws Exception {
		return userCertMapper.getUserCertListCount(userCertExDto);
	}

	public List<UserCertExDto> getUserCertList(UserCertExDto userCertExDto) throws Exception {
		return userCertMapper.getUserCertList(userCertExDto);
	}

	public UserCertExDto getUserCert(UserCertExDto userCertExDto) throws Exception {
		return userCertMapper.getUserCert(userCertExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertUserCert(UserCertExDto userCertExDto) throws Exception {
		userCertMapper.insertUserCert(userCertExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateUserCert(UserCertExDto userCertExDto) throws Exception {
		userCertMapper.updateUserCert(userCertExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteUserCert(UserCertExDto userCertExDto) throws Exception {
		userCertMapper.deleteUserCert(userCertExDto);
	}
}
