package com.app.menuAuth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.menuAuth.mapper.MenuAuthMapper;
import com.app.menuAuth.model.MenuAuthExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MenuAuthService {

	@Autowired
	private MenuAuthMapper menuAuthMapper;

	public int getMenuAuthListCount(MenuAuthExDto menuAuthExDto) throws Exception {
		return menuAuthMapper.getMenuAuthListCount(menuAuthExDto);
	}

	public List<MenuAuthExDto> getMenuAuthList(MenuAuthExDto menuAuthExDto) throws Exception {
		return menuAuthMapper.getMenuAuthList(menuAuthExDto);
	}

	public MenuAuthExDto getMenuAuth(MenuAuthExDto menuAuthExDto) throws Exception {
		return menuAuthMapper.getMenuAuth(menuAuthExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertMenuAuth(MenuAuthExDto menuAuthExDto) throws Exception {
		menuAuthMapper.insertMenuAuth(menuAuthExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateMenuAuth(MenuAuthExDto menuAuthExDto) throws Exception {
		menuAuthMapper.updateMenuAuth(menuAuthExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteMenuAuth(MenuAuthExDto menuAuthExDto) throws Exception {
		menuAuthMapper.deleteMenuAuth(menuAuthExDto);
	}
}
