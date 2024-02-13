package com.app.cmnCode.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.cmnCode.model.CmnCodeExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.cmnCode.mapper
* CmnCodeMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("cmnCodeMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CmnCodeMapper  extends DaoBaseMapper {
//	public class CmnCodeMapper  extends DaoBaseMapper {

	public int getCmnCodeListCount( CmnCodeExDto cmnCodeExDto ) throws Exception {
		return (Integer)selectByPk("cmnCodeMapper.getCmnCodeListCount", cmnCodeExDto);
	}
	
	public List<CmnCodeExDto> getCmnCodeList( CmnCodeExDto cmnCodeExDto ) throws Exception {
		return (List<CmnCodeExDto>)list("cmnCodeMapper.getCmnCodeList", cmnCodeExDto);
	}
	
	public CmnCodeExDto getCmnCode( CmnCodeExDto cmnCodeExDto ) throws Exception {
		return (CmnCodeExDto) selectByPk("cmnCodeMapper.getCmnCode", cmnCodeExDto);
	}

	public void insertCmnCode( CmnCodeExDto cmnCodeExDto ) throws Exception {
		insert("cmnCodeMapper.insertCmnCode", cmnCodeExDto);
	}
	
	public void updateCmnCode( CmnCodeExDto cmnCodeExDto ) throws Exception {
		update("cmnCodeMapper.updateCmnCode", cmnCodeExDto);
	}

	public void deleteCmnCode( CmnCodeExDto cmnCodeExDto ) throws Exception {
		delete("cmnCodeMapper.deleteCmnCode", cmnCodeExDto);
	}

}
