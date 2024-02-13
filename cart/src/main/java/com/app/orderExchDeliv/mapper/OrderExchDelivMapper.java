package com.app.orderExchDeliv.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.orderExchDeliv.model.OrderExchDelivExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.orderExchDeliv.mapper
* OrderExchDelivMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("orderExchDelivMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class OrderExchDelivMapper  extends DaoBaseMapper {
//	public class OrderExchDelivMapper  extends DaoBaseMapper {

	public int getOrderExchDelivListCount( OrderExchDelivExDto orderExchDelivExDto ) throws Exception {
		return (Integer)selectByPk("orderExchDelivMapper.getOrderExchDelivListCount", orderExchDelivExDto);
	}
	
	public List<OrderExchDelivExDto> getOrderExchDelivList( OrderExchDelivExDto orderExchDelivExDto ) throws Exception {
		return (List<OrderExchDelivExDto>)list("orderExchDelivMapper.getOrderExchDelivList", orderExchDelivExDto);
	}
	
	public OrderExchDelivExDto getOrderExchDeliv( OrderExchDelivExDto orderExchDelivExDto ) throws Exception {
		return (OrderExchDelivExDto) selectByPk("orderExchDelivMapper.getOrderExchDeliv", orderExchDelivExDto);
	}

	public void insertOrderExchDeliv( OrderExchDelivExDto orderExchDelivExDto ) throws Exception {
		insert("orderExchDelivMapper.insertOrderExchDeliv", orderExchDelivExDto);
	}
	
	public void updateOrderExchDeliv( OrderExchDelivExDto orderExchDelivExDto ) throws Exception {
		update("orderExchDelivMapper.updateOrderExchDeliv", orderExchDelivExDto);
	}

	public void deleteOrderExchDeliv( OrderExchDelivExDto orderExchDelivExDto ) throws Exception {
		delete("orderExchDelivMapper.deleteOrderExchDeliv", orderExchDelivExDto);
	}

}
