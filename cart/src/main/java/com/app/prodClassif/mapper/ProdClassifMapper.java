package com.app.prodClassif.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.prodClassif.model.ProdClassifExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.prodClassif.mapper
* ProdClassifMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("prodClassifMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class ProdClassifMapper  extends DaoBaseMapper {
//	public class ProdClassifMapper  extends DaoBaseMapper {

	public int getProdClassifListCount( ProdClassifExDto prodClassifExDto ) throws Exception {
		return (Integer)selectByPk("prodClassifMapper.getProdClassifListCount", prodClassifExDto);
	}
	
	public List<ProdClassifExDto> getProdClassifList( ProdClassifExDto prodClassifExDto ) throws Exception {
		return (List<ProdClassifExDto>)list("prodClassifMapper.getProdClassifList", prodClassifExDto);
	}
	
	public ProdClassifExDto getProdClassif( ProdClassifExDto prodClassifExDto ) throws Exception {
		return (ProdClassifExDto) selectByPk("prodClassifMapper.getProdClassif", prodClassifExDto);
	}

	public void insertProdClassif( ProdClassifExDto prodClassifExDto ) throws Exception {
		insert("prodClassifMapper.insertProdClassif", prodClassifExDto);
	}
	
	public void updateProdClassif( ProdClassifExDto prodClassifExDto ) throws Exception {
		update("prodClassifMapper.updateProdClassif", prodClassifExDto);
	}

	public void deleteProdClassif( ProdClassifExDto prodClassifExDto ) throws Exception {
		delete("prodClassifMapper.deleteProdClassif", prodClassifExDto);
	}

}
