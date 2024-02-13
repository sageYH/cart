package com.app.orderCnclDelivProd.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.orderCnclDelivProd.model.OrderCnclDelivProdExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.orderCnclDelivProd.mapper
* OrderCnclDelivProdMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("orderCnclDelivProdMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class OrderCnclDelivProdMapper  extends DaoBaseMapper {
//	public class OrderCnclDelivProdMapper  extends DaoBaseMapper {

	public int getOrderCnclDelivProdListCount( OrderCnclDelivProdExDto orderCnclDelivProdExDto ) throws Exception {
		return (Integer)selectByPk("orderCnclDelivProdMapper.getOrderCnclDelivProdListCount", orderCnclDelivProdExDto);
	}
	
	public List<OrderCnclDelivProdExDto> getOrderCnclDelivProdList( OrderCnclDelivProdExDto orderCnclDelivProdExDto ) throws Exception {
		return (List<OrderCnclDelivProdExDto>)list("orderCnclDelivProdMapper.getOrderCnclDelivProdList", orderCnclDelivProdExDto);
	}
	
	public OrderCnclDelivProdExDto getOrderCnclDelivProd( OrderCnclDelivProdExDto orderCnclDelivProdExDto ) throws Exception {
		return (OrderCnclDelivProdExDto) selectByPk("orderCnclDelivProdMapper.getOrderCnclDelivProd", orderCnclDelivProdExDto);
	}

	public void insertOrderCnclDelivProd( OrderCnclDelivProdExDto orderCnclDelivProdExDto ) throws Exception {
		insert("orderCnclDelivProdMapper.insertOrderCnclDelivProd", orderCnclDelivProdExDto);
	}
	
	public void updateOrderCnclDelivProd( OrderCnclDelivProdExDto orderCnclDelivProdExDto ) throws Exception {
		update("orderCnclDelivProdMapper.updateOrderCnclDelivProd", orderCnclDelivProdExDto);
	}

	public void deleteOrderCnclDelivProd( OrderCnclDelivProdExDto orderCnclDelivProdExDto ) throws Exception {
		delete("orderCnclDelivProdMapper.deleteOrderCnclDelivProd", orderCnclDelivProdExDto);
	}

}
