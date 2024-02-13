package com.app.pointInfoLang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.pointInfoLang.model.PointInfoLangExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.pointInfoLang.mapper
* PointInfoLangMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("pointInfoLangMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class PointInfoLangMapper  extends DaoBaseMapper {
//	public class PointInfoLangMapper  extends DaoBaseMapper {

	public int getPointInfoLangListCount( PointInfoLangExDto pointInfoLangExDto ) throws Exception {
		return (Integer)selectByPk("pointInfoLangMapper.getPointInfoLangListCount", pointInfoLangExDto);
	}
	
	public List<PointInfoLangExDto> getPointInfoLangList( PointInfoLangExDto pointInfoLangExDto ) throws Exception {
		return (List<PointInfoLangExDto>)list("pointInfoLangMapper.getPointInfoLangList", pointInfoLangExDto);
	}
	
	public PointInfoLangExDto getPointInfoLang( PointInfoLangExDto pointInfoLangExDto ) throws Exception {
		return (PointInfoLangExDto) selectByPk("pointInfoLangMapper.getPointInfoLang", pointInfoLangExDto);
	}

	public void insertPointInfoLang( PointInfoLangExDto pointInfoLangExDto ) throws Exception {
		insert("pointInfoLangMapper.insertPointInfoLang", pointInfoLangExDto);
	}
	
	public void updatePointInfoLang( PointInfoLangExDto pointInfoLangExDto ) throws Exception {
		update("pointInfoLangMapper.updatePointInfoLang", pointInfoLangExDto);
	}

	public void deletePointInfoLang( PointInfoLangExDto pointInfoLangExDto ) throws Exception {
		delete("pointInfoLangMapper.deletePointInfoLang", pointInfoLangExDto);
	}

}
