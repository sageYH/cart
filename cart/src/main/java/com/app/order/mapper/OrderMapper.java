package com.app.order.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.order.model.OrderExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.order.mapper
* OrderMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("orderMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class OrderMapper  extends DaoBaseMapper {
//	public class OrderMapper  extends DaoBaseMapper {

	public int getOrderListCount( OrderExDto orderExDto ) throws Exception {
		return (Integer)selectByPk("orderMapper.getOrderListCount", orderExDto);
	}
	
	public List<OrderExDto> getOrderList( OrderExDto orderExDto ) throws Exception {
		return (List<OrderExDto>)list("orderMapper.getOrderList", orderExDto);
	}
	
	public OrderExDto getOrder( OrderExDto orderExDto ) throws Exception {
		return (OrderExDto) selectByPk("orderMapper.getOrder", orderExDto);
	}

	public void insertOrder( OrderExDto orderExDto ) throws Exception {
		insert("orderMapper.insertOrder", orderExDto);
	}
	
	public void updateOrder( OrderExDto orderExDto ) throws Exception {
		update("orderMapper.updateOrder", orderExDto);
	}

	public void deleteOrder( OrderExDto orderExDto ) throws Exception {
		delete("orderMapper.deleteOrder", orderExDto);
	}

}
