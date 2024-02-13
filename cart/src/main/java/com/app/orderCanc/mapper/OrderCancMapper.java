package com.app.orderCanc.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.orderCanc.model.OrderCancExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.orderCanc.mapper
* OrderCancMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("orderCancMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class OrderCancMapper  extends DaoBaseMapper {
//	public class OrderCancMapper  extends DaoBaseMapper {

	public int getOrderCancListCount( OrderCancExDto orderCancExDto ) throws Exception {
		return (Integer)selectByPk("orderCancMapper.getOrderCancListCount", orderCancExDto);
	}
	
	public List<OrderCancExDto> getOrderCancList( OrderCancExDto orderCancExDto ) throws Exception {
		return (List<OrderCancExDto>)list("orderCancMapper.getOrderCancList", orderCancExDto);
	}
	
	public OrderCancExDto getOrderCanc( OrderCancExDto orderCancExDto ) throws Exception {
		return (OrderCancExDto) selectByPk("orderCancMapper.getOrderCanc", orderCancExDto);
	}

	public void insertOrderCanc( OrderCancExDto orderCancExDto ) throws Exception {
		insert("orderCancMapper.insertOrderCanc", orderCancExDto);
	}
	
	public void updateOrderCanc( OrderCancExDto orderCancExDto ) throws Exception {
		update("orderCancMapper.updateOrderCanc", orderCancExDto);
	}

	public void deleteOrderCanc( OrderCancExDto orderCancExDto ) throws Exception {
		delete("orderCancMapper.deleteOrderCanc", orderCancExDto);
	}

}
