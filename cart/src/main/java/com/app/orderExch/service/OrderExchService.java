package com.app.orderExch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.orderExch.mapper.OrderExchMapper;
import com.app.orderExch.model.OrderExchExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrderExchService {

	@Autowired
	private OrderExchMapper orderExchMapper;

	public int getOrderExchListCount(OrderExchExDto orderExchExDto) throws Exception {
		return orderExchMapper.getOrderExchListCount(orderExchExDto);
	}

	public List<OrderExchExDto> getOrderExchList(OrderExchExDto orderExchExDto) throws Exception {
		return orderExchMapper.getOrderExchList(orderExchExDto);
	}

	public OrderExchExDto getOrderExch(OrderExchExDto orderExchExDto) throws Exception {
		return orderExchMapper.getOrderExch(orderExchExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertOrderExch(OrderExchExDto orderExchExDto) throws Exception {
		orderExchMapper.insertOrderExch(orderExchExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateOrderExch(OrderExchExDto orderExchExDto) throws Exception {
		orderExchMapper.updateOrderExch(orderExchExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteOrderExch(OrderExchExDto orderExchExDto) throws Exception {
		orderExchMapper.deleteOrderExch(orderExchExDto);
	}
}
