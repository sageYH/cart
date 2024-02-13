package com.app.prodCont.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.prodCont.model.ProdContExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.prodCont.mapper
* ProdContMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("prodContMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class ProdContMapper  extends DaoBaseMapper {
//	public class ProdContMapper  extends DaoBaseMapper {

	public int getProdContListCount( ProdContExDto prodContExDto ) throws Exception {
		return (Integer)selectByPk("prodContMapper.getProdContListCount", prodContExDto);
	}
	
	public List<ProdContExDto> getProdContList( ProdContExDto prodContExDto ) throws Exception {
		return (List<ProdContExDto>)list("prodContMapper.getProdContList", prodContExDto);
	}
	
	public ProdContExDto getProdCont( ProdContExDto prodContExDto ) throws Exception {
		return (ProdContExDto) selectByPk("prodContMapper.getProdCont", prodContExDto);
	}

	public void insertProdCont( ProdContExDto prodContExDto ) throws Exception {
		insert("prodContMapper.insertProdCont", prodContExDto);
	}
	
	public void updateProdCont( ProdContExDto prodContExDto ) throws Exception {
		update("prodContMapper.updateProdCont", prodContExDto);
	}

	public void deleteProdCont( ProdContExDto prodContExDto ) throws Exception {
		delete("prodContMapper.deleteProdCont", prodContExDto);
	}

}
