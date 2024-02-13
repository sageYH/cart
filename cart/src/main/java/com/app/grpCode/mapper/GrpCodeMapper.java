package com.app.grpCode.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.grpCode.model.GrpCodeExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.grpCode.mapper
* GrpCodeMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("grpCodeMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class GrpCodeMapper  extends DaoBaseMapper {
//	public class GrpCodeMapper  extends DaoBaseMapper {

	public int getGrpCodeListCount( GrpCodeExDto grpCodeExDto ) throws Exception {
		return (Integer)selectByPk("grpCodeMapper.getGrpCodeListCount", grpCodeExDto);
	}
	
	public List<GrpCodeExDto> getGrpCodeList( GrpCodeExDto grpCodeExDto ) throws Exception {
		return (List<GrpCodeExDto>)list("grpCodeMapper.getGrpCodeList", grpCodeExDto);
	}
	
	public GrpCodeExDto getGrpCode( GrpCodeExDto grpCodeExDto ) throws Exception {
		return (GrpCodeExDto) selectByPk("grpCodeMapper.getGrpCode", grpCodeExDto);
	}

	public void insertGrpCode( GrpCodeExDto grpCodeExDto ) throws Exception {
		insert("grpCodeMapper.insertGrpCode", grpCodeExDto);
	}
	
	public void updateGrpCode( GrpCodeExDto grpCodeExDto ) throws Exception {
		update("grpCodeMapper.updateGrpCode", grpCodeExDto);
	}

	public void deleteGrpCode( GrpCodeExDto grpCodeExDto ) throws Exception {
		delete("grpCodeMapper.deleteGrpCode", grpCodeExDto);
	}

}
