package com.app.prodOptLang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.prodOptLang.model.ProdOptLangExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.prodOptLang.mapper
* ProdOptLangMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("prodOptLangMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class ProdOptLangMapper  extends DaoBaseMapper {
//	public class ProdOptLangMapper  extends DaoBaseMapper {

	public int getProdOptLangListCount( ProdOptLangExDto prodOptLangExDto ) throws Exception {
		return (Integer)selectByPk("prodOptLangMapper.getProdOptLangListCount", prodOptLangExDto);
	}
	
	public List<ProdOptLangExDto> getProdOptLangList( ProdOptLangExDto prodOptLangExDto ) throws Exception {
		return (List<ProdOptLangExDto>)list("prodOptLangMapper.getProdOptLangList", prodOptLangExDto);
	}
	
	public ProdOptLangExDto getProdOptLang( ProdOptLangExDto prodOptLangExDto ) throws Exception {
		return (ProdOptLangExDto) selectByPk("prodOptLangMapper.getProdOptLang", prodOptLangExDto);
	}

	public void insertProdOptLang( ProdOptLangExDto prodOptLangExDto ) throws Exception {
		insert("prodOptLangMapper.insertProdOptLang", prodOptLangExDto);
	}
	
	public void updateProdOptLang( ProdOptLangExDto prodOptLangExDto ) throws Exception {
		update("prodOptLangMapper.updateProdOptLang", prodOptLangExDto);
	}

	public void deleteProdOptLang( ProdOptLangExDto prodOptLangExDto ) throws Exception {
		delete("prodOptLangMapper.deleteProdOptLang", prodOptLangExDto);
	}

}
