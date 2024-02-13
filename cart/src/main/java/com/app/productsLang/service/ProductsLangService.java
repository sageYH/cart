package com.app.productsLang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.productsLang.mapper.ProductsLangMapper;
import com.app.productsLang.model.ProductsLangExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductsLangService {

	@Autowired
	private ProductsLangMapper productsLangMapper;

	public int getProductsLangListCount(ProductsLangExDto productsLangExDto) throws Exception {
		return productsLangMapper.getProductsLangListCount(productsLangExDto);
	}

	public List<ProductsLangExDto> getProductsLangList(ProductsLangExDto productsLangExDto) throws Exception {
		return productsLangMapper.getProductsLangList(productsLangExDto);
	}

	public ProductsLangExDto getProductsLang(ProductsLangExDto productsLangExDto) throws Exception {
		return productsLangMapper.getProductsLang(productsLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertProductsLang(ProductsLangExDto productsLangExDto) throws Exception {
		productsLangMapper.insertProductsLang(productsLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateProductsLang(ProductsLangExDto productsLangExDto) throws Exception {
		productsLangMapper.updateProductsLang(productsLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteProductsLang(ProductsLangExDto productsLangExDto) throws Exception {
		productsLangMapper.deleteProductsLang(productsLangExDto);
	}
}
