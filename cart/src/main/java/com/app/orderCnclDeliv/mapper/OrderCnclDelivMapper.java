package com.app.orderCnclDeliv.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.orderCnclDeliv.model.OrderCnclDelivExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.orderCnclDeliv.mapper
* OrderCnclDelivMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("orderCnclDelivMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class OrderCnclDelivMapper  extends DaoBaseMapper {
//	public class OrderCnclDelivMapper  extends DaoBaseMapper {

	public int getOrderCnclDelivListCount( OrderCnclDelivExDto orderCnclDelivExDto ) throws Exception {
		return (Integer)selectByPk("orderCnclDelivMapper.getOrderCnclDelivListCount", orderCnclDelivExDto);
	}
	
	public List<OrderCnclDelivExDto> getOrderCnclDelivList( OrderCnclDelivExDto orderCnclDelivExDto ) throws Exception {
		return (List<OrderCnclDelivExDto>)list("orderCnclDelivMapper.getOrderCnclDelivList", orderCnclDelivExDto);
	}
	
	public OrderCnclDelivExDto getOrderCnclDeliv( OrderCnclDelivExDto orderCnclDelivExDto ) throws Exception {
		return (OrderCnclDelivExDto) selectByPk("orderCnclDelivMapper.getOrderCnclDeliv", orderCnclDelivExDto);
	}

	public void insertOrderCnclDeliv( OrderCnclDelivExDto orderCnclDelivExDto ) throws Exception {
		insert("orderCnclDelivMapper.insertOrderCnclDeliv", orderCnclDelivExDto);
	}
	
	public void updateOrderCnclDeliv( OrderCnclDelivExDto orderCnclDelivExDto ) throws Exception {
		update("orderCnclDelivMapper.updateOrderCnclDeliv", orderCnclDelivExDto);
	}

	public void deleteOrderCnclDeliv( OrderCnclDelivExDto orderCnclDelivExDto ) throws Exception {
		delete("orderCnclDelivMapper.deleteOrderCnclDeliv", orderCnclDelivExDto);
	}

}
