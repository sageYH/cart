package com.app.orderCnclDelivProd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.orderCnclDelivProd.mapper.OrderCnclDelivProdMapper;
import com.app.orderCnclDelivProd.model.OrderCnclDelivProdExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrderCnclDelivProdService {

	@Autowired
	private OrderCnclDelivProdMapper orderCnclDelivProdMapper;

	public int getOrderCnclDelivProdListCount(OrderCnclDelivProdExDto orderCnclDelivProdExDto) throws Exception {
		return orderCnclDelivProdMapper.getOrderCnclDelivProdListCount(orderCnclDelivProdExDto);
	}

	public List<OrderCnclDelivProdExDto> getOrderCnclDelivProdList(OrderCnclDelivProdExDto orderCnclDelivProdExDto) throws Exception {
		return orderCnclDelivProdMapper.getOrderCnclDelivProdList(orderCnclDelivProdExDto);
	}

	public OrderCnclDelivProdExDto getOrderCnclDelivProd(OrderCnclDelivProdExDto orderCnclDelivProdExDto) throws Exception {
		return orderCnclDelivProdMapper.getOrderCnclDelivProd(orderCnclDelivProdExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertOrderCnclDelivProd(OrderCnclDelivProdExDto orderCnclDelivProdExDto) throws Exception {
		orderCnclDelivProdMapper.insertOrderCnclDelivProd(orderCnclDelivProdExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateOrderCnclDelivProd(OrderCnclDelivProdExDto orderCnclDelivProdExDto) throws Exception {
		orderCnclDelivProdMapper.updateOrderCnclDelivProd(orderCnclDelivProdExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteOrderCnclDelivProd(OrderCnclDelivProdExDto orderCnclDelivProdExDto) throws Exception {
		orderCnclDelivProdMapper.deleteOrderCnclDelivProd(orderCnclDelivProdExDto);
	}
}
