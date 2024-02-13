package com.app.orderExch.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.orderExch.model.OrderExchExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.orderExch.mapper
* OrderExchMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("orderExchMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class OrderExchMapper  extends DaoBaseMapper {
//	public class OrderExchMapper  extends DaoBaseMapper {

	public int getOrderExchListCount( OrderExchExDto orderExchExDto ) throws Exception {
		return (Integer)selectByPk("orderExchMapper.getOrderExchListCount", orderExchExDto);
	}
	
	public List<OrderExchExDto> getOrderExchList( OrderExchExDto orderExchExDto ) throws Exception {
		return (List<OrderExchExDto>)list("orderExchMapper.getOrderExchList", orderExchExDto);
	}
	
	public OrderExchExDto getOrderExch( OrderExchExDto orderExchExDto ) throws Exception {
		return (OrderExchExDto) selectByPk("orderExchMapper.getOrderExch", orderExchExDto);
	}

	public void insertOrderExch( OrderExchExDto orderExchExDto ) throws Exception {
		insert("orderExchMapper.insertOrderExch", orderExchExDto);
	}
	
	public void updateOrderExch( OrderExchExDto orderExchExDto ) throws Exception {
		update("orderExchMapper.updateOrderExch", orderExchExDto);
	}

	public void deleteOrderExch( OrderExchExDto orderExchExDto ) throws Exception {
		delete("orderExchMapper.deleteOrderExch", orderExchExDto);
	}

}
