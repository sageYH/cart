package com.app.orderDeliv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.orderDeliv.mapper.OrderDelivMapper;
import com.app.orderDeliv.model.OrderDelivExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrderDelivService {

	@Autowired
	private OrderDelivMapper orderDelivMapper;

	public int getOrderDelivListCount(OrderDelivExDto orderDelivExDto) throws Exception {
		return orderDelivMapper.getOrderDelivListCount(orderDelivExDto);
	}

	public List<OrderDelivExDto> getOrderDelivList(OrderDelivExDto orderDelivExDto) throws Exception {
		return orderDelivMapper.getOrderDelivList(orderDelivExDto);
	}

	public OrderDelivExDto getOrderDeliv(OrderDelivExDto orderDelivExDto) throws Exception {
		return orderDelivMapper.getOrderDeliv(orderDelivExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertOrderDeliv(OrderDelivExDto orderDelivExDto) throws Exception {
		orderDelivMapper.insertOrderDeliv(orderDelivExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateOrderDeliv(OrderDelivExDto orderDelivExDto) throws Exception {
		orderDelivMapper.updateOrderDeliv(orderDelivExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteOrderDeliv(OrderDelivExDto orderDelivExDto) throws Exception {
		orderDelivMapper.deleteOrderDeliv(orderDelivExDto);
	}
}
