package com.app.orderDtlOpt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.orderDtlOpt.mapper.OrderDtlOptMapper;
import com.app.orderDtlOpt.model.OrderDtlOptExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrderDtlOptService {

	@Autowired
	private OrderDtlOptMapper orderDtlOptMapper;

	public int getOrderDtlOptListCount(OrderDtlOptExDto orderDtlOptExDto) throws Exception {
		return orderDtlOptMapper.getOrderDtlOptListCount(orderDtlOptExDto);
	}

	public List<OrderDtlOptExDto> getOrderDtlOptList(OrderDtlOptExDto orderDtlOptExDto) throws Exception {
		return orderDtlOptMapper.getOrderDtlOptList(orderDtlOptExDto);
	}

	public OrderDtlOptExDto getOrderDtlOpt(OrderDtlOptExDto orderDtlOptExDto) throws Exception {
		return orderDtlOptMapper.getOrderDtlOpt(orderDtlOptExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertOrderDtlOpt(OrderDtlOptExDto orderDtlOptExDto) throws Exception {
		orderDtlOptMapper.insertOrderDtlOpt(orderDtlOptExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateOrderDtlOpt(OrderDtlOptExDto orderDtlOptExDto) throws Exception {
		orderDtlOptMapper.updateOrderDtlOpt(orderDtlOptExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteOrderDtlOpt(OrderDtlOptExDto orderDtlOptExDto) throws Exception {
		orderDtlOptMapper.deleteOrderDtlOpt(orderDtlOptExDto);
	}
}
