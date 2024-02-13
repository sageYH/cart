package com.app.compProd.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.compProd.model.CompProdExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.compProd.mapper
* CompProdMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("compProdMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CompProdMapper  extends DaoBaseMapper {
//	public class CompProdMapper  extends DaoBaseMapper {

	public int getCompProdListCount( CompProdExDto compProdExDto ) throws Exception {
		return (Integer)selectByPk("compProdMapper.getCompProdListCount", compProdExDto);
	}
	
	public List<CompProdExDto> getCompProdList( CompProdExDto compProdExDto ) throws Exception {
		return (List<CompProdExDto>)list("compProdMapper.getCompProdList", compProdExDto);
	}
	
	public CompProdExDto getCompProd( CompProdExDto compProdExDto ) throws Exception {
		return (CompProdExDto) selectByPk("compProdMapper.getCompProd", compProdExDto);
	}

	public void insertCompProd( CompProdExDto compProdExDto ) throws Exception {
		insert("compProdMapper.insertCompProd", compProdExDto);
	}
	
	public void updateCompProd( CompProdExDto compProdExDto ) throws Exception {
		update("compProdMapper.updateCompProd", compProdExDto);
	}

	public void deleteCompProd( CompProdExDto compProdExDto ) throws Exception {
		delete("compProdMapper.deleteCompProd", compProdExDto);
	}

}
