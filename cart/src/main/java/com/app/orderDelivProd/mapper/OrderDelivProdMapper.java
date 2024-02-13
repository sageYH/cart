package com.app.orderDelivProd.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.orderDelivProd.model.OrderDelivProdExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.orderDelivProd.mapper
* OrderDelivProdMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("orderDelivProdMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class OrderDelivProdMapper  extends DaoBaseMapper {
//	public class OrderDelivProdMapper  extends DaoBaseMapper {

	public int getOrderDelivProdListCount( OrderDelivProdExDto orderDelivProdExDto ) throws Exception {
		return (Integer)selectByPk("orderDelivProdMapper.getOrderDelivProdListCount", orderDelivProdExDto);
	}
	
	public List<OrderDelivProdExDto> getOrderDelivProdList( OrderDelivProdExDto orderDelivProdExDto ) throws Exception {
		return (List<OrderDelivProdExDto>)list("orderDelivProdMapper.getOrderDelivProdList", orderDelivProdExDto);
	}
	
	public OrderDelivProdExDto getOrderDelivProd( OrderDelivProdExDto orderDelivProdExDto ) throws Exception {
		return (OrderDelivProdExDto) selectByPk("orderDelivProdMapper.getOrderDelivProd", orderDelivProdExDto);
	}

	public void insertOrderDelivProd( OrderDelivProdExDto orderDelivProdExDto ) throws Exception {
		insert("orderDelivProdMapper.insertOrderDelivProd", orderDelivProdExDto);
	}
	
	public void updateOrderDelivProd( OrderDelivProdExDto orderDelivProdExDto ) throws Exception {
		update("orderDelivProdMapper.updateOrderDelivProd", orderDelivProdExDto);
	}

	public void deleteOrderDelivProd( OrderDelivProdExDto orderDelivProdExDto ) throws Exception {
		delete("orderDelivProdMapper.deleteOrderDelivProd", orderDelivProdExDto);
	}

}
