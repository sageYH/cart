package com.app.products.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.products.mapper.ProductsMapper;
import com.app.products.model.ProductsExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductsService {

	@Autowired
	private ProductsMapper productsMapper;

	public int getProductsListCount(ProductsExDto productsExDto) throws Exception {
		return productsMapper.getProductsListCount(productsExDto);
	}

	public List<ProductsExDto> getProductsList(ProductsExDto productsExDto) throws Exception {
		return productsMapper.getProductsList(productsExDto);
	}

	public ProductsExDto getProducts(ProductsExDto productsExDto) throws Exception {
		return productsMapper.getProducts(productsExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertProducts(ProductsExDto productsExDto) throws Exception {
		productsMapper.insertProducts(productsExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateProducts(ProductsExDto productsExDto) throws Exception {
		productsMapper.updateProducts(productsExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteProducts(ProductsExDto productsExDto) throws Exception {
		productsMapper.deleteProducts(productsExDto);
	}
}
