package com.app.prodClassifLang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.prodClassifLang.model.ProdClassifLangExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.prodClassifLang.mapper
* ProdClassifLangMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("prodClassifLangMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class ProdClassifLangMapper  extends DaoBaseMapper {
//	public class ProdClassifLangMapper  extends DaoBaseMapper {

	public int getProdClassifLangListCount( ProdClassifLangExDto prodClassifLangExDto ) throws Exception {
		return (Integer)selectByPk("prodClassifLangMapper.getProdClassifLangListCount", prodClassifLangExDto);
	}
	
	public List<ProdClassifLangExDto> getProdClassifLangList( ProdClassifLangExDto prodClassifLangExDto ) throws Exception {
		return (List<ProdClassifLangExDto>)list("prodClassifLangMapper.getProdClassifLangList", prodClassifLangExDto);
	}
	
	public ProdClassifLangExDto getProdClassifLang( ProdClassifLangExDto prodClassifLangExDto ) throws Exception {
		return (ProdClassifLangExDto) selectByPk("prodClassifLangMapper.getProdClassifLang", prodClassifLangExDto);
	}

	public void insertProdClassifLang( ProdClassifLangExDto prodClassifLangExDto ) throws Exception {
		insert("prodClassifLangMapper.insertProdClassifLang", prodClassifLangExDto);
	}
	
	public void updateProdClassifLang( ProdClassifLangExDto prodClassifLangExDto ) throws Exception {
		update("prodClassifLangMapper.updateProdClassifLang", prodClassifLangExDto);
	}

	public void deleteProdClassifLang( ProdClassifLangExDto prodClassifLangExDto ) throws Exception {
		delete("prodClassifLangMapper.deleteProdClassifLang", prodClassifLangExDto);
	}

}
