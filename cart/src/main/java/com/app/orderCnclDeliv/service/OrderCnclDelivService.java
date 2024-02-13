package com.app.orderCnclDeliv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.orderCnclDeliv.mapper.OrderCnclDelivMapper;
import com.app.orderCnclDeliv.model.OrderCnclDelivExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrderCnclDelivService {

	@Autowired
	private OrderCnclDelivMapper orderCnclDelivMapper;

	public int getOrderCnclDelivListCount(OrderCnclDelivExDto orderCnclDelivExDto) throws Exception {
		return orderCnclDelivMapper.getOrderCnclDelivListCount(orderCnclDelivExDto);
	}

	public List<OrderCnclDelivExDto> getOrderCnclDelivList(OrderCnclDelivExDto orderCnclDelivExDto) throws Exception {
		return orderCnclDelivMapper.getOrderCnclDelivList(orderCnclDelivExDto);
	}

	public OrderCnclDelivExDto getOrderCnclDeliv(OrderCnclDelivExDto orderCnclDelivExDto) throws Exception {
		return orderCnclDelivMapper.getOrderCnclDeliv(orderCnclDelivExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertOrderCnclDeliv(OrderCnclDelivExDto orderCnclDelivExDto) throws Exception {
		orderCnclDelivMapper.insertOrderCnclDeliv(orderCnclDelivExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateOrderCnclDeliv(OrderCnclDelivExDto orderCnclDelivExDto) throws Exception {
		orderCnclDelivMapper.updateOrderCnclDeliv(orderCnclDelivExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteOrderCnclDeliv(OrderCnclDelivExDto orderCnclDelivExDto) throws Exception {
		orderCnclDelivMapper.deleteOrderCnclDeliv(orderCnclDelivExDto);
	}
}
