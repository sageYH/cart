package com.app.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.cart.mapper.CartMapper;
import com.app.cart.model.CartExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CartService {

	@Autowired
	private CartMapper cartMapper;

	public int getCartListCount(CartExDto cartExDto) throws Exception {
		return cartMapper.getCartListCount(cartExDto);
	}

	public List<CartExDto> getCartList(CartExDto cartExDto) throws Exception {
		return cartMapper.getCartList(cartExDto);
	}

	public CartExDto getCart(CartExDto cartExDto) throws Exception {
		return cartMapper.getCart(cartExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCart(CartExDto cartExDto) throws Exception {
		cartMapper.insertCart(cartExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCart(CartExDto cartExDto) throws Exception {
		cartMapper.updateCart(cartExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCart(CartExDto cartExDto) throws Exception {
		cartMapper.deleteCart(cartExDto);
	}
}
