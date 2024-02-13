package com.app.menuLang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.menuLang.mapper.MenuLangMapper;
import com.app.menuLang.model.MenuLangExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MenuLangService {

	@Autowired
	private MenuLangMapper menuLangMapper;

	public int getMenuLangListCount(MenuLangExDto menuLangExDto) throws Exception {
		return menuLangMapper.getMenuLangListCount(menuLangExDto);
	}

	public List<MenuLangExDto> getMenuLangList(MenuLangExDto menuLangExDto) throws Exception {
		return menuLangMapper.getMenuLangList(menuLangExDto);
	}

	public MenuLangExDto getMenuLang(MenuLangExDto menuLangExDto) throws Exception {
		return menuLangMapper.getMenuLang(menuLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertMenuLang(MenuLangExDto menuLangExDto) throws Exception {
		menuLangMapper.insertMenuLang(menuLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateMenuLang(MenuLangExDto menuLangExDto) throws Exception {
		menuLangMapper.updateMenuLang(menuLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteMenuLang(MenuLangExDto menuLangExDto) throws Exception {
		menuLangMapper.deleteMenuLang(menuLangExDto);
	}
}
