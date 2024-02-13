package com.app.orderCanc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.orderCanc.mapper.OrderCancMapper;
import com.app.orderCanc.model.OrderCancExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrderCancService {

	@Autowired
	private OrderCancMapper orderCancMapper;

	public int getOrderCancListCount(OrderCancExDto orderCancExDto) throws Exception {
		return orderCancMapper.getOrderCancListCount(orderCancExDto);
	}

	public List<OrderCancExDto> getOrderCancList(OrderCancExDto orderCancExDto) throws Exception {
		return orderCancMapper.getOrderCancList(orderCancExDto);
	}

	public OrderCancExDto getOrderCanc(OrderCancExDto orderCancExDto) throws Exception {
		return orderCancMapper.getOrderCanc(orderCancExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertOrderCanc(OrderCancExDto orderCancExDto) throws Exception {
		orderCancMapper.insertOrderCanc(orderCancExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateOrderCanc(OrderCancExDto orderCancExDto) throws Exception {
		orderCancMapper.updateOrderCanc(orderCancExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteOrderCanc(OrderCancExDto orderCancExDto) throws Exception {
		orderCancMapper.deleteOrderCanc(orderCancExDto);
	}
}
