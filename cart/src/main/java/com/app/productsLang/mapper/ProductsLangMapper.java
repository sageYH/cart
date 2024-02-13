package com.app.productsLang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.productsLang.model.ProductsLangExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.productsLang.mapper
* ProductsLangMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("productsLangMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class ProductsLangMapper  extends DaoBaseMapper {
//	public class ProductsLangMapper  extends DaoBaseMapper {

	public int getProductsLangListCount( ProductsLangExDto productsLangExDto ) throws Exception {
		return (Integer)selectByPk("productsLangMapper.getProductsLangListCount", productsLangExDto);
	}
	
	public List<ProductsLangExDto> getProductsLangList( ProductsLangExDto productsLangExDto ) throws Exception {
		return (List<ProductsLangExDto>)list("productsLangMapper.getProductsLangList", productsLangExDto);
	}
	
	public ProductsLangExDto getProductsLang( ProductsLangExDto productsLangExDto ) throws Exception {
		return (ProductsLangExDto) selectByPk("productsLangMapper.getProductsLang", productsLangExDto);
	}

	public void insertProductsLang( ProductsLangExDto productsLangExDto ) throws Exception {
		insert("productsLangMapper.insertProductsLang", productsLangExDto);
	}
	
	public void updateProductsLang( ProductsLangExDto productsLangExDto ) throws Exception {
		update("productsLangMapper.updateProductsLang", productsLangExDto);
	}

	public void deleteProductsLang( ProductsLangExDto productsLangExDto ) throws Exception {
		delete("productsLangMapper.deleteProductsLang", productsLangExDto);
	}

}
