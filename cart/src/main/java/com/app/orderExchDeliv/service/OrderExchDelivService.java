package com.app.orderExchDeliv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.orderExchDeliv.mapper.OrderExchDelivMapper;
import com.app.orderExchDeliv.model.OrderExchDelivExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrderExchDelivService {

	@Autowired
	private OrderExchDelivMapper orderExchDelivMapper;

	public int getOrderExchDelivListCount(OrderExchDelivExDto orderExchDelivExDto) throws Exception {
		return orderExchDelivMapper.getOrderExchDelivListCount(orderExchDelivExDto);
	}

	public List<OrderExchDelivExDto> getOrderExchDelivList(OrderExchDelivExDto orderExchDelivExDto) throws Exception {
		return orderExchDelivMapper.getOrderExchDelivList(orderExchDelivExDto);
	}

	public OrderExchDelivExDto getOrderExchDeliv(OrderExchDelivExDto orderExchDelivExDto) throws Exception {
		return orderExchDelivMapper.getOrderExchDeliv(orderExchDelivExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertOrderExchDeliv(OrderExchDelivExDto orderExchDelivExDto) throws Exception {
		orderExchDelivMapper.insertOrderExchDeliv(orderExchDelivExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateOrderExchDeliv(OrderExchDelivExDto orderExchDelivExDto) throws Exception {
		orderExchDelivMapper.updateOrderExchDeliv(orderExchDelivExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteOrderExchDeliv(OrderExchDelivExDto orderExchDelivExDto) throws Exception {
		orderExchDelivMapper.deleteOrderExchDeliv(orderExchDelivExDto);
	}
}
