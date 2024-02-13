package com.app.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.menu.mapper.MenuMapper;
import com.app.menu.model.MenuExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MenuService {

	@Autowired
	private MenuMapper menuMapper;

	public int getMenuListCount(MenuExDto menuExDto) throws Exception {
		return menuMapper.getMenuListCount(menuExDto);
	}

	public List<MenuExDto> getMenuList(MenuExDto menuExDto) throws Exception {
		return menuMapper.getMenuList(menuExDto);
	}

	public MenuExDto getMenu(MenuExDto menuExDto) throws Exception {
		return menuMapper.getMenu(menuExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertMenu(MenuExDto menuExDto) throws Exception {
		menuMapper.insertMenu(menuExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateMenu(MenuExDto menuExDto) throws Exception {
		menuMapper.updateMenu(menuExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteMenu(MenuExDto menuExDto) throws Exception {
		menuMapper.deleteMenu(menuExDto);
	}
}
