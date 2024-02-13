package com.app.orderDelivProd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.orderDelivProd.mapper.OrderDelivProdMapper;
import com.app.orderDelivProd.model.OrderDelivProdExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrderDelivProdService {

	@Autowired
	private OrderDelivProdMapper orderDelivProdMapper;

	public int getOrderDelivProdListCount(OrderDelivProdExDto orderDelivProdExDto) throws Exception {
		return orderDelivProdMapper.getOrderDelivProdListCount(orderDelivProdExDto);
	}

	public List<OrderDelivProdExDto> getOrderDelivProdList(OrderDelivProdExDto orderDelivProdExDto) throws Exception {
		return orderDelivProdMapper.getOrderDelivProdList(orderDelivProdExDto);
	}

	public OrderDelivProdExDto getOrderDelivProd(OrderDelivProdExDto orderDelivProdExDto) throws Exception {
		return orderDelivProdMapper.getOrderDelivProd(orderDelivProdExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertOrderDelivProd(OrderDelivProdExDto orderDelivProdExDto) throws Exception {
		orderDelivProdMapper.insertOrderDelivProd(orderDelivProdExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateOrderDelivProd(OrderDelivProdExDto orderDelivProdExDto) throws Exception {
		orderDelivProdMapper.updateOrderDelivProd(orderDelivProdExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteOrderDelivProd(OrderDelivProdExDto orderDelivProdExDto) throws Exception {
		orderDelivProdMapper.deleteOrderDelivProd(orderDelivProdExDto);
	}
}
