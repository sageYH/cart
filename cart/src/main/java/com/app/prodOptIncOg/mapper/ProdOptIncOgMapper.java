package com.app.prodOptIncOg.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.prodOptIncOg.model.ProdOptIncOgExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.prodOptIncOg.mapper
* ProdOptIncOgMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("prodOptIncOgMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class ProdOptIncOgMapper  extends DaoBaseMapper {
//	public class ProdOptIncOgMapper  extends DaoBaseMapper {

	public int getProdOptIncOgListCount( ProdOptIncOgExDto prodOptIncOgExDto ) throws Exception {
		return (Integer)selectByPk("prodOptIncOgMapper.getProdOptIncOgListCount", prodOptIncOgExDto);
	}
	
	public List<ProdOptIncOgExDto> getProdOptIncOgList( ProdOptIncOgExDto prodOptIncOgExDto ) throws Exception {
		return (List<ProdOptIncOgExDto>)list("prodOptIncOgMapper.getProdOptIncOgList", prodOptIncOgExDto);
	}
	
	public ProdOptIncOgExDto getProdOptIncOg( ProdOptIncOgExDto prodOptIncOgExDto ) throws Exception {
		return (ProdOptIncOgExDto) selectByPk("prodOptIncOgMapper.getProdOptIncOg", prodOptIncOgExDto);
	}

	public void insertProdOptIncOg( ProdOptIncOgExDto prodOptIncOgExDto ) throws Exception {
		insert("prodOptIncOgMapper.insertProdOptIncOg", prodOptIncOgExDto);
	}
	
	public void updateProdOptIncOg( ProdOptIncOgExDto prodOptIncOgExDto ) throws Exception {
		update("prodOptIncOgMapper.updateProdOptIncOg", prodOptIncOgExDto);
	}

	public void deleteProdOptIncOg( ProdOptIncOgExDto prodOptIncOgExDto ) throws Exception {
		delete("prodOptIncOgMapper.deleteProdOptIncOg", prodOptIncOgExDto);
	}

}
