package com.app.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.order.mapper.OrderMapper;
import com.app.order.model.OrderExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrderService {

	@Autowired
	private OrderMapper orderMapper;

	public int getOrderListCount(OrderExDto orderExDto) throws Exception {
		return orderMapper.getOrderListCount(orderExDto);
	}

	public List<OrderExDto> getOrderList(OrderExDto orderExDto) throws Exception {
		return orderMapper.getOrderList(orderExDto);
	}

	public OrderExDto getOrder(OrderExDto orderExDto) throws Exception {
		return orderMapper.getOrder(orderExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertOrder(OrderExDto orderExDto) throws Exception {
		orderMapper.insertOrder(orderExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateOrder(OrderExDto orderExDto) throws Exception {
		orderMapper.updateOrder(orderExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteOrder(OrderExDto orderExDto) throws Exception {
		orderMapper.deleteOrder(orderExDto);
	}
}
