package com.app.products.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.products.model.ProductsExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.products.mapper
* ProductsMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("productsMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class ProductsMapper  extends DaoBaseMapper {
//	public class ProductsMapper  extends DaoBaseMapper {

	public int getProductsListCount( ProductsExDto productsExDto ) throws Exception {
		return (Integer)selectByPk("productsMapper.getProductsListCount", productsExDto);
	}
	
	public List<ProductsExDto> getProductsList( ProductsExDto productsExDto ) throws Exception {
		return (List<ProductsExDto>)list("productsMapper.getProductsList", productsExDto);
	}
	
	public ProductsExDto getProducts( ProductsExDto productsExDto ) throws Exception {
		return (ProductsExDto) selectByPk("productsMapper.getProducts", productsExDto);
	}

	public void insertProducts( ProductsExDto productsExDto ) throws Exception {
		insert("productsMapper.insertProducts", productsExDto);
	}
	
	public void updateProducts( ProductsExDto productsExDto ) throws Exception {
		update("productsMapper.updateProducts", productsExDto);
	}

	public void deleteProducts( ProductsExDto productsExDto ) throws Exception {
		delete("productsMapper.deleteProducts", productsExDto);
	}

}
