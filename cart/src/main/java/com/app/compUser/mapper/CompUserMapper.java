package com.app.compUser.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.compUser.model.CompUserExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.compUser.mapper
* CompUserMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("compUserMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CompUserMapper  extends DaoBaseMapper {
//	public class CompUserMapper  extends DaoBaseMapper {

	public int getCompUserListCount( CompUserExDto compUserExDto ) throws Exception {
		return (Integer)selectByPk("compUserMapper.getCompUserListCount", compUserExDto);
	}
	
	public List<CompUserExDto> getCompUserList( CompUserExDto compUserExDto ) throws Exception {
		return (List<CompUserExDto>)list("compUserMapper.getCompUserList", compUserExDto);
	}
	
	public CompUserExDto getCompUser( CompUserExDto compUserExDto ) throws Exception {
		return (CompUserExDto) selectByPk("compUserMapper.getCompUser", compUserExDto);
	}

	public void insertCompUser( CompUserExDto compUserExDto ) throws Exception {
		insert("compUserMapper.insertCompUser", compUserExDto);
	}
	
	public void updateCompUser( CompUserExDto compUserExDto ) throws Exception {
		update("compUserMapper.updateCompUser", compUserExDto);
	}

	public void deleteCompUser( CompUserExDto compUserExDto ) throws Exception {
		delete("compUserMapper.deleteCompUser", compUserExDto);
	}

}
