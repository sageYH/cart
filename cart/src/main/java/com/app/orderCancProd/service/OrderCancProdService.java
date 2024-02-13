package com.app.orderCancProd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.orderCancProd.mapper.OrderCancProdMapper;
import com.app.orderCancProd.model.OrderCancProdExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrderCancProdService {

	@Autowired
	private OrderCancProdMapper orderCancProdMapper;

	public int getOrderCancProdListCount(OrderCancProdExDto orderCancProdExDto) throws Exception {
		return orderCancProdMapper.getOrderCancProdListCount(orderCancProdExDto);
	}

	public List<OrderCancProdExDto> getOrderCancProdList(OrderCancProdExDto orderCancProdExDto) throws Exception {
		return orderCancProdMapper.getOrderCancProdList(orderCancProdExDto);
	}

	public OrderCancProdExDto getOrderCancProd(OrderCancProdExDto orderCancProdExDto) throws Exception {
		return orderCancProdMapper.getOrderCancProd(orderCancProdExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertOrderCancProd(OrderCancProdExDto orderCancProdExDto) throws Exception {
		orderCancProdMapper.insertOrderCancProd(orderCancProdExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateOrderCancProd(OrderCancProdExDto orderCancProdExDto) throws Exception {
		orderCancProdMapper.updateOrderCancProd(orderCancProdExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteOrderCancProd(OrderCancProdExDto orderCancProdExDto) throws Exception {
		orderCancProdMapper.deleteOrderCancProd(orderCancProdExDto);
	}
}
