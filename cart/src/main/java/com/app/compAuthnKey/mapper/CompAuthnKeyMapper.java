package com.app.compAuthnKey.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.compAuthnKey.model.CompAuthnKeyExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.compAuthnKey.mapper
* CompAuthnKeyMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("compAuthnKeyMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CompAuthnKeyMapper  extends DaoBaseMapper {
//	public class CompAuthnKeyMapper  extends DaoBaseMapper {

	public int getCompAuthnKeyListCount( CompAuthnKeyExDto compAuthnKeyExDto ) throws Exception {
		return (Integer)selectByPk("compAuthnKeyMapper.getCompAuthnKeyListCount", compAuthnKeyExDto);
	}
	
	public List<CompAuthnKeyExDto> getCompAuthnKeyList( CompAuthnKeyExDto compAuthnKeyExDto ) throws Exception {
		return (List<CompAuthnKeyExDto>)list("compAuthnKeyMapper.getCompAuthnKeyList", compAuthnKeyExDto);
	}
	
	public CompAuthnKeyExDto getCompAuthnKey( CompAuthnKeyExDto compAuthnKeyExDto ) throws Exception {
		return (CompAuthnKeyExDto) selectByPk("compAuthnKeyMapper.getCompAuthnKey", compAuthnKeyExDto);
	}

	public void insertCompAuthnKey( CompAuthnKeyExDto compAuthnKeyExDto ) throws Exception {
		insert("compAuthnKeyMapper.insertCompAuthnKey", compAuthnKeyExDto);
	}
	
	public void updateCompAuthnKey( CompAuthnKeyExDto compAuthnKeyExDto ) throws Exception {
		update("compAuthnKeyMapper.updateCompAuthnKey", compAuthnKeyExDto);
	}

	public void deleteCompAuthnKey( CompAuthnKeyExDto compAuthnKeyExDto ) throws Exception {
		delete("compAuthnKeyMapper.deleteCompAuthnKey", compAuthnKeyExDto);
	}

}
