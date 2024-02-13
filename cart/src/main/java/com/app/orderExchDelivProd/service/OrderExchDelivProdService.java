package com.app.orderExchDelivProd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.orderExchDelivProd.mapper.OrderExchDelivProdMapper;
import com.app.orderExchDelivProd.model.OrderExchDelivProdExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrderExchDelivProdService {

	@Autowired
	private OrderExchDelivProdMapper orderExchDelivProdMapper;

	public int getOrderExchDelivProdListCount(OrderExchDelivProdExDto orderExchDelivProdExDto) throws Exception {
		return orderExchDelivProdMapper.getOrderExchDelivProdListCount(orderExchDelivProdExDto);
	}

	public List<OrderExchDelivProdExDto> getOrderExchDelivProdList(OrderExchDelivProdExDto orderExchDelivProdExDto) throws Exception {
		return orderExchDelivProdMapper.getOrderExchDelivProdList(orderExchDelivProdExDto);
	}

	public OrderExchDelivProdExDto getOrderExchDelivProd(OrderExchDelivProdExDto orderExchDelivProdExDto) throws Exception {
		return orderExchDelivProdMapper.getOrderExchDelivProd(orderExchDelivProdExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertOrderExchDelivProd(OrderExchDelivProdExDto orderExchDelivProdExDto) throws Exception {
		orderExchDelivProdMapper.insertOrderExchDelivProd(orderExchDelivProdExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateOrderExchDelivProd(OrderExchDelivProdExDto orderExchDelivProdExDto) throws Exception {
		orderExchDelivProdMapper.updateOrderExchDelivProd(orderExchDelivProdExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteOrderExchDelivProd(OrderExchDelivProdExDto orderExchDelivProdExDto) throws Exception {
		orderExchDelivProdMapper.deleteOrderExchDelivProd(orderExchDelivProdExDto);
	}
}
