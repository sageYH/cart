package com.app.userGrp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.userGrp.mapper.UserGrpMapper;
import com.app.userGrp.model.UserGrpExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserGrpService {

	@Autowired
	private UserGrpMapper userGrpMapper;

	public int getUserGrpListCount(UserGrpExDto userGrpExDto) throws Exception {
		return userGrpMapper.getUserGrpListCount(userGrpExDto);
	}

	public List<UserGrpExDto> getUserGrpList(UserGrpExDto userGrpExDto) throws Exception {
		return userGrpMapper.getUserGrpList(userGrpExDto);
	}

	public UserGrpExDto getUserGrp(UserGrpExDto userGrpExDto) throws Exception {
		return userGrpMapper.getUserGrp(userGrpExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertUserGrp(UserGrpExDto userGrpExDto) throws Exception {
		userGrpMapper.insertUserGrp(userGrpExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateUserGrp(UserGrpExDto userGrpExDto) throws Exception {
		userGrpMapper.updateUserGrp(userGrpExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteUserGrp(UserGrpExDto userGrpExDto) throws Exception {
		userGrpMapper.deleteUserGrp(userGrpExDto);
	}
}
