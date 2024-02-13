package com.app.userGrp.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.userGrp.model.UserGrpExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.userGrp.mapper
* UserGrpMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("userGrpMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class UserGrpMapper  extends DaoBaseMapper {
//	public class UserGrpMapper  extends DaoBaseMapper {

	public int getUserGrpListCount( UserGrpExDto userGrpExDto ) throws Exception {
		return (Integer)selectByPk("userGrpMapper.getUserGrpListCount", userGrpExDto);
	}
	
	public List<UserGrpExDto> getUserGrpList( UserGrpExDto userGrpExDto ) throws Exception {
		return (List<UserGrpExDto>)list("userGrpMapper.getUserGrpList", userGrpExDto);
	}
	
	public UserGrpExDto getUserGrp( UserGrpExDto userGrpExDto ) throws Exception {
		return (UserGrpExDto) selectByPk("userGrpMapper.getUserGrp", userGrpExDto);
	}

	public void insertUserGrp( UserGrpExDto userGrpExDto ) throws Exception {
		insert("userGrpMapper.insertUserGrp", userGrpExDto);
	}
	
	public void updateUserGrp( UserGrpExDto userGrpExDto ) throws Exception {
		update("userGrpMapper.updateUserGrp", userGrpExDto);
	}

	public void deleteUserGrp( UserGrpExDto userGrpExDto ) throws Exception {
		delete("userGrpMapper.deleteUserGrp", userGrpExDto);
	}

}
