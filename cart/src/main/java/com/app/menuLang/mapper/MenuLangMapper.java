package com.app.menuLang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.menuLang.model.MenuLangExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.menuLang.mapper
* MenuLangMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("menuLangMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class MenuLangMapper  extends DaoBaseMapper {
//	public class MenuLangMapper  extends DaoBaseMapper {

	public int getMenuLangListCount( MenuLangExDto menuLangExDto ) throws Exception {
		return (Integer)selectByPk("menuLangMapper.getMenuLangListCount", menuLangExDto);
	}
	
	public List<MenuLangExDto> getMenuLangList( MenuLangExDto menuLangExDto ) throws Exception {
		return (List<MenuLangExDto>)list("menuLangMapper.getMenuLangList", menuLangExDto);
	}
	
	public MenuLangExDto getMenuLang( MenuLangExDto menuLangExDto ) throws Exception {
		return (MenuLangExDto) selectByPk("menuLangMapper.getMenuLang", menuLangExDto);
	}

	public void insertMenuLang( MenuLangExDto menuLangExDto ) throws Exception {
		insert("menuLangMapper.insertMenuLang", menuLangExDto);
	}
	
	public void updateMenuLang( MenuLangExDto menuLangExDto ) throws Exception {
		update("menuLangMapper.updateMenuLang", menuLangExDto);
	}

	public void deleteMenuLang( MenuLangExDto menuLangExDto ) throws Exception {
		delete("menuLangMapper.deleteMenuLang", menuLangExDto);
	}

}
