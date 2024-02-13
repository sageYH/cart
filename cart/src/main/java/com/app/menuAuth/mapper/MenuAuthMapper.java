package com.app.menuAuth.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.menuAuth.model.MenuAuthExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.menuAuth.mapper
* MenuAuthMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("menuAuthMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class MenuAuthMapper  extends DaoBaseMapper {
//	public class MenuAuthMapper  extends DaoBaseMapper {

	public int getMenuAuthListCount( MenuAuthExDto menuAuthExDto ) throws Exception {
		return (Integer)selectByPk("menuAuthMapper.getMenuAuthListCount", menuAuthExDto);
	}
	
	public List<MenuAuthExDto> getMenuAuthList( MenuAuthExDto menuAuthExDto ) throws Exception {
		return (List<MenuAuthExDto>)list("menuAuthMapper.getMenuAuthList", menuAuthExDto);
	}
	
	public MenuAuthExDto getMenuAuth( MenuAuthExDto menuAuthExDto ) throws Exception {
		return (MenuAuthExDto) selectByPk("menuAuthMapper.getMenuAuth", menuAuthExDto);
	}

	public void insertMenuAuth( MenuAuthExDto menuAuthExDto ) throws Exception {
		insert("menuAuthMapper.insertMenuAuth", menuAuthExDto);
	}
	
	public void updateMenuAuth( MenuAuthExDto menuAuthExDto ) throws Exception {
		update("menuAuthMapper.updateMenuAuth", menuAuthExDto);
	}

	public void deleteMenuAuth( MenuAuthExDto menuAuthExDto ) throws Exception {
		delete("menuAuthMapper.deleteMenuAuth", menuAuthExDto);
	}

}
