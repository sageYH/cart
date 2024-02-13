package com.app.orderCancProd.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.orderCancProd.model.OrderCancProdExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.orderCancProd.mapper
* OrderCancProdMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("orderCancProdMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class OrderCancProdMapper  extends DaoBaseMapper {
//	public class OrderCancProdMapper  extends DaoBaseMapper {

	public int getOrderCancProdListCount( OrderCancProdExDto orderCancProdExDto ) throws Exception {
		return (Integer)selectByPk("orderCancProdMapper.getOrderCancProdListCount", orderCancProdExDto);
	}
	
	public List<OrderCancProdExDto> getOrderCancProdList( OrderCancProdExDto orderCancProdExDto ) throws Exception {
		return (List<OrderCancProdExDto>)list("orderCancProdMapper.getOrderCancProdList", orderCancProdExDto);
	}
	
	public OrderCancProdExDto getOrderCancProd( OrderCancProdExDto orderCancProdExDto ) throws Exception {
		return (OrderCancProdExDto) selectByPk("orderCancProdMapper.getOrderCancProd", orderCancProdExDto);
	}

	public void insertOrderCancProd( OrderCancProdExDto orderCancProdExDto ) throws Exception {
		insert("orderCancProdMapper.insertOrderCancProd", orderCancProdExDto);
	}
	
	public void updateOrderCancProd( OrderCancProdExDto orderCancProdExDto ) throws Exception {
		update("orderCancProdMapper.updateOrderCancProd", orderCancProdExDto);
	}

	public void deleteOrderCancProd( OrderCancProdExDto orderCancProdExDto ) throws Exception {
		delete("orderCancProdMapper.deleteOrderCancProd", orderCancProdExDto);
	}

}
