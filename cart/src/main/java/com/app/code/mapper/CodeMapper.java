package com.app.code.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.code.model.CodeExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.code.mapper
* CodeMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("codeMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CodeMapper  extends DaoBaseMapper {
//	public class CodeMapper  extends DaoBaseMapper {

	public int getCodeListCount( CodeExDto codeExDto ) throws Exception {
		return (Integer)selectByPk("codeMapper.getCodeListCount", codeExDto);
	}
	
	public List<CodeExDto> getCodeList( CodeExDto codeExDto ) throws Exception {
		return (List<CodeExDto>)list("codeMapper.getCodeList", codeExDto);
	}
	
	public CodeExDto getCode( CodeExDto codeExDto ) throws Exception {
		return (CodeExDto) selectByPk("codeMapper.getCode", codeExDto);
	}

	public void insertCode( CodeExDto codeExDto ) throws Exception {
		insert("codeMapper.insertCode", codeExDto);
	}
	
	public void updateCode( CodeExDto codeExDto ) throws Exception {
		update("codeMapper.updateCode", codeExDto);
	}

	public void deleteCode( CodeExDto codeExDto ) throws Exception {
		delete("codeMapper.deleteCode", codeExDto);
	}

}
