package com.app.orderDeliv.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.orderDeliv.model.OrderDelivExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.orderDeliv.mapper
* OrderDelivMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("orderDelivMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class OrderDelivMapper  extends DaoBaseMapper {
//	public class OrderDelivMapper  extends DaoBaseMapper {

	public int getOrderDelivListCount( OrderDelivExDto orderDelivExDto ) throws Exception {
		return (Integer)selectByPk("orderDelivMapper.getOrderDelivListCount", orderDelivExDto);
	}
	
	public List<OrderDelivExDto> getOrderDelivList( OrderDelivExDto orderDelivExDto ) throws Exception {
		return (List<OrderDelivExDto>)list("orderDelivMapper.getOrderDelivList", orderDelivExDto);
	}
	
	public OrderDelivExDto getOrderDeliv( OrderDelivExDto orderDelivExDto ) throws Exception {
		return (OrderDelivExDto) selectByPk("orderDelivMapper.getOrderDeliv", orderDelivExDto);
	}

	public void insertOrderDeliv( OrderDelivExDto orderDelivExDto ) throws Exception {
		insert("orderDelivMapper.insertOrderDeliv", orderDelivExDto);
	}
	
	public void updateOrderDeliv( OrderDelivExDto orderDelivExDto ) throws Exception {
		update("orderDelivMapper.updateOrderDeliv", orderDelivExDto);
	}

	public void deleteOrderDeliv( OrderDelivExDto orderDelivExDto ) throws Exception {
		delete("orderDelivMapper.deleteOrderDeliv", orderDelivExDto);
	}

}
