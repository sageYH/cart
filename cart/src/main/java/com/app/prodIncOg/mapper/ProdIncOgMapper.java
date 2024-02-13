package com.app.prodIncOg.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.prodIncOg.model.ProdIncOgExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.prodIncOg.mapper
* ProdIncOgMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("prodIncOgMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class ProdIncOgMapper  extends DaoBaseMapper {
//	public class ProdIncOgMapper  extends DaoBaseMapper {

	public int getProdIncOgListCount( ProdIncOgExDto prodIncOgExDto ) throws Exception {
		return (Integer)selectByPk("prodIncOgMapper.getProdIncOgListCount", prodIncOgExDto);
	}
	
	public List<ProdIncOgExDto> getProdIncOgList( ProdIncOgExDto prodIncOgExDto ) throws Exception {
		return (List<ProdIncOgExDto>)list("prodIncOgMapper.getProdIncOgList", prodIncOgExDto);
	}
	
	public ProdIncOgExDto getProdIncOg( ProdIncOgExDto prodIncOgExDto ) throws Exception {
		return (ProdIncOgExDto) selectByPk("prodIncOgMapper.getProdIncOg", prodIncOgExDto);
	}

	public void insertProdIncOg( ProdIncOgExDto prodIncOgExDto ) throws Exception {
		insert("prodIncOgMapper.insertProdIncOg", prodIncOgExDto);
	}
	
	public void updateProdIncOg( ProdIncOgExDto prodIncOgExDto ) throws Exception {
		update("prodIncOgMapper.updateProdIncOg", prodIncOgExDto);
	}

	public void deleteProdIncOg( ProdIncOgExDto prodIncOgExDto ) throws Exception {
		delete("prodIncOgMapper.deleteProdIncOg", prodIncOgExDto);
	}

}
