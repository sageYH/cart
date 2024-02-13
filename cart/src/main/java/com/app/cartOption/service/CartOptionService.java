package com.app.cartOption.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.cartOption.mapper.CartOptionMapper;
import com.app.cartOption.model.CartOptionExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CartOptionService {

	@Autowired
	private CartOptionMapper cartOptionMapper;

	public int getCartOptionListCount(CartOptionExDto cartOptionExDto) throws Exception {
		return cartOptionMapper.getCartOptionListCount(cartOptionExDto);
	}

	public List<CartOptionExDto> getCartOptionList(CartOptionExDto cartOptionExDto) throws Exception {
		return cartOptionMapper.getCartOptionList(cartOptionExDto);
	}

	public CartOptionExDto getCartOption(CartOptionExDto cartOptionExDto) throws Exception {
		return cartOptionMapper.getCartOption(cartOptionExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCartOption(CartOptionExDto cartOptionExDto) throws Exception {
		cartOptionMapper.insertCartOption(cartOptionExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCartOption(CartOptionExDto cartOptionExDto) throws Exception {
		cartOptionMapper.updateCartOption(cartOptionExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCartOption(CartOptionExDto cartOptionExDto) throws Exception {
		cartOptionMapper.deleteCartOption(cartOptionExDto);
	}
}
