package com.app.menu.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.app.menu.model.MenuExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.menu.mapper
* MenuMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("menuMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class MenuMapper  extends DaoBaseMapper {
//	public class MenuMapper  extends DaoBaseMapper {

	public int getMenuListCount( MenuExDto menuExDto ) throws Exception {
		return (Integer)selectByPk("menuMapper.getMenuListCount", menuExDto);
	}
	
	public List<MenuExDto> getMenuList( MenuExDto menuExDto ) throws Exception {
		return (List<MenuExDto>)list("menuMapper.getMenuList", menuExDto);
	}
	
	public MenuExDto getMenu( MenuExDto menuExDto ) throws Exception {
		return (MenuExDto) selectByPk("menuMapper.getMenu", menuExDto);
	}

	public void insertMenu( MenuExDto menuExDto ) throws Exception {
		insert("menuMapper.insertMenu", menuExDto);
	}
	
	public void updateMenu( MenuExDto menuExDto ) throws Exception {
		update("menuMapper.updateMenu", menuExDto);
	}

	public void deleteMenu( MenuExDto menuExDto ) throws Exception {
		delete("menuMapper.deleteMenu", menuExDto);
	}

	public List<Map> getGnbMenu( Map map ) throws Exception {
		return (List<Map>)list("menuMapper.getGnbMenu", map);
	}
}
