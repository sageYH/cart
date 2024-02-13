package com.app.prodOpt.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.prodOpt.model.ProdOptExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.prodOpt.mapper
* ProdOptMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("prodOptMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class ProdOptMapper  extends DaoBaseMapper {
//	public class ProdOptMapper  extends DaoBaseMapper {

	public int getProdOptListCount( ProdOptExDto prodOptExDto ) throws Exception {
		return (Integer)selectByPk("prodOptMapper.getProdOptListCount", prodOptExDto);
	}
	
	public List<ProdOptExDto> getProdOptList( ProdOptExDto prodOptExDto ) throws Exception {
		return (List<ProdOptExDto>)list("prodOptMapper.getProdOptList", prodOptExDto);
	}
	
	public ProdOptExDto getProdOpt( ProdOptExDto prodOptExDto ) throws Exception {
		return (ProdOptExDto) selectByPk("prodOptMapper.getProdOpt", prodOptExDto);
	}

	public void insertProdOpt( ProdOptExDto prodOptExDto ) throws Exception {
		insert("prodOptMapper.insertProdOpt", prodOptExDto);
	}
	
	public void updateProdOpt( ProdOptExDto prodOptExDto ) throws Exception {
		update("prodOptMapper.updateProdOpt", prodOptExDto);
	}

	public void deleteProdOpt( ProdOptExDto prodOptExDto ) throws Exception {
		delete("prodOptMapper.deleteProdOpt", prodOptExDto);
	}

}
