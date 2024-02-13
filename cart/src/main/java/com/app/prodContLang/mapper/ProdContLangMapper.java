package com.app.prodContLang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.prodContLang.model.ProdContLangExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.prodContLang.mapper
* ProdContLangMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("prodContLangMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class ProdContLangMapper  extends DaoBaseMapper {
//	public class ProdContLangMapper  extends DaoBaseMapper {

	public int getProdContLangListCount( ProdContLangExDto prodContLangExDto ) throws Exception {
		return (Integer)selectByPk("prodContLangMapper.getProdContLangListCount", prodContLangExDto);
	}
	
	public List<ProdContLangExDto> getProdContLangList( ProdContLangExDto prodContLangExDto ) throws Exception {
		return (List<ProdContLangExDto>)list("prodContLangMapper.getProdContLangList", prodContLangExDto);
	}
	
	public ProdContLangExDto getProdContLang( ProdContLangExDto prodContLangExDto ) throws Exception {
		return (ProdContLangExDto) selectByPk("prodContLangMapper.getProdContLang", prodContLangExDto);
	}

	public void insertProdContLang( ProdContLangExDto prodContLangExDto ) throws Exception {
		insert("prodContLangMapper.insertProdContLang", prodContLangExDto);
	}
	
	public void updateProdContLang( ProdContLangExDto prodContLangExDto ) throws Exception {
		update("prodContLangMapper.updateProdContLang", prodContLangExDto);
	}

	public void deleteProdContLang( ProdContLangExDto prodContLangExDto ) throws Exception {
		delete("prodContLangMapper.deleteProdContLang", prodContLangExDto);
	}

}
