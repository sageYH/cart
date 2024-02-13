package com.app.cartIdInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.cartIdInfo.mapper.CartIdInfoMapper;
import com.app.cartIdInfo.model.CartIdInfoExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CartIdInfoService {

	@Autowired
	private CartIdInfoMapper cartIdInfoMapper;

	public int getCartIdInfoListCount(CartIdInfoExDto cartIdInfoExDto) throws Exception {
		return cartIdInfoMapper.getCartIdInfoListCount(cartIdInfoExDto);
	}

	public List<CartIdInfoExDto> getCartIdInfoList(CartIdInfoExDto cartIdInfoExDto) throws Exception {
		return cartIdInfoMapper.getCartIdInfoList(cartIdInfoExDto);
	}

	public CartIdInfoExDto getCartIdInfo(CartIdInfoExDto cartIdInfoExDto) throws Exception {
		return cartIdInfoMapper.getCartIdInfo(cartIdInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCartIdInfo(CartIdInfoExDto cartIdInfoExDto) throws Exception {
		cartIdInfoMapper.insertCartIdInfo(cartIdInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCartIdInfo(CartIdInfoExDto cartIdInfoExDto) throws Exception {
		cartIdInfoMapper.updateCartIdInfo(cartIdInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCartIdInfo(CartIdInfoExDto cartIdInfoExDto) throws Exception {
		cartIdInfoMapper.deleteCartIdInfo(cartIdInfoExDto);
	}
}
