package com.app.orderExchDtl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.orderExchDtl.mapper.OrderExchDtlMapper;
import com.app.orderExchDtl.model.OrderExchDtlExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrderExchDtlService {

	@Autowired
	private OrderExchDtlMapper orderExchDtlMapper;

	public int getOrderExchDtlListCount(OrderExchDtlExDto orderExchDtlExDto) throws Exception {
		return orderExchDtlMapper.getOrderExchDtlListCount(orderExchDtlExDto);
	}

	public List<OrderExchDtlExDto> getOrderExchDtlList(OrderExchDtlExDto orderExchDtlExDto) throws Exception {
		return orderExchDtlMapper.getOrderExchDtlList(orderExchDtlExDto);
	}

	public OrderExchDtlExDto getOrderExchDtl(OrderExchDtlExDto orderExchDtlExDto) throws Exception {
		return orderExchDtlMapper.getOrderExchDtl(orderExchDtlExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertOrderExchDtl(OrderExchDtlExDto orderExchDtlExDto) throws Exception {
		orderExchDtlMapper.insertOrderExchDtl(orderExchDtlExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateOrderExchDtl(OrderExchDtlExDto orderExchDtlExDto) throws Exception {
		orderExchDtlMapper.updateOrderExchDtl(orderExchDtlExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteOrderExchDtl(OrderExchDtlExDto orderExchDtlExDto) throws Exception {
		orderExchDtlMapper.deleteOrderExchDtl(orderExchDtlExDto);
	}
}
