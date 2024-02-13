package com.app.grpCodeLang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.grpCodeLang.model.GrpCodeLangExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.grpCodeLang.mapper
* GrpCodeLangMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("grpCodeLangMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class GrpCodeLangMapper  extends DaoBaseMapper {
//	public class GrpCodeLangMapper  extends DaoBaseMapper {

	public int getGrpCodeLangListCount( GrpCodeLangExDto grpCodeLangExDto ) throws Exception {
		return (Integer)selectByPk("grpCodeLangMapper.getGrpCodeLangListCount", grpCodeLangExDto);
	}
	
	public List<GrpCodeLangExDto> getGrpCodeLangList( GrpCodeLangExDto grpCodeLangExDto ) throws Exception {
		return (List<GrpCodeLangExDto>)list("grpCodeLangMapper.getGrpCodeLangList", grpCodeLangExDto);
	}
	
	public GrpCodeLangExDto getGrpCodeLang( GrpCodeLangExDto grpCodeLangExDto ) throws Exception {
		return (GrpCodeLangExDto) selectByPk("grpCodeLangMapper.getGrpCodeLang", grpCodeLangExDto);
	}

	public void insertGrpCodeLang( GrpCodeLangExDto grpCodeLangExDto ) throws Exception {
		insert("grpCodeLangMapper.insertGrpCodeLang", grpCodeLangExDto);
	}
	
	public void updateGrpCodeLang( GrpCodeLangExDto grpCodeLangExDto ) throws Exception {
		update("grpCodeLangMapper.updateGrpCodeLang", grpCodeLangExDto);
	}

	public void deleteGrpCodeLang( GrpCodeLangExDto grpCodeLangExDto ) throws Exception {
		delete("grpCodeLangMapper.deleteGrpCodeLang", grpCodeLangExDto);
	}

}
