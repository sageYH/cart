package com.app.orderDtl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.orderDtl.mapper.OrderDtlMapper;
import com.app.orderDtl.model.OrderDtlExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrderDtlService {

	@Autowired
	private OrderDtlMapper orderDtlMapper;

	public int getOrderDtlListCount(OrderDtlExDto orderDtlExDto) throws Exception {
		return orderDtlMapper.getOrderDtlListCount(orderDtlExDto);
	}

	public List<OrderDtlExDto> getOrderDtlList(OrderDtlExDto orderDtlExDto) throws Exception {
		return orderDtlMapper.getOrderDtlList(orderDtlExDto);
	}

	public OrderDtlExDto getOrderDtl(OrderDtlExDto orderDtlExDto) throws Exception {
		return orderDtlMapper.getOrderDtl(orderDtlExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertOrderDtl(OrderDtlExDto orderDtlExDto) throws Exception {
		orderDtlMapper.insertOrderDtl(orderDtlExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateOrderDtl(OrderDtlExDto orderDtlExDto) throws Exception {
		orderDtlMapper.updateOrderDtl(orderDtlExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteOrderDtl(OrderDtlExDto orderDtlExDto) throws Exception {
		orderDtlMapper.deleteOrderDtl(orderDtlExDto);
	}
}
