package com.app.comapny.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.comapny.model.ComapnyExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.comapny.mapper
* ComapnyMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("comapnyMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class ComapnyMapper  extends DaoBaseMapper {
//	public class ComapnyMapper  extends DaoBaseMapper {

	public int getComapnyListCount( ComapnyExDto comapnyExDto ) throws Exception {
		return (Integer)selectByPk("comapnyMapper.getComapnyListCount", comapnyExDto);
	}
	
	public List<ComapnyExDto> getComapnyList( ComapnyExDto comapnyExDto ) throws Exception {
		return (List<ComapnyExDto>)list("comapnyMapper.getComapnyList", comapnyExDto);
	}
	
	public ComapnyExDto getComapny( ComapnyExDto comapnyExDto ) throws Exception {
		return (ComapnyExDto) selectByPk("comapnyMapper.getComapny", comapnyExDto);
	}

	public void insertComapny( ComapnyExDto comapnyExDto ) throws Exception {
		insert("comapnyMapper.insertComapny", comapnyExDto);
	}
	
	public void updateComapny( ComapnyExDto comapnyExDto ) throws Exception {
		update("comapnyMapper.updateComapny", comapnyExDto);
	}

	public void deleteComapny( ComapnyExDto comapnyExDto ) throws Exception {
		delete("comapnyMapper.deleteComapny", comapnyExDto);
	}

}
