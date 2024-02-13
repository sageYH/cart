package com.app.orderExchDelivProd.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.orderExchDelivProd.model.OrderExchDelivProdExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.orderExchDelivProd.mapper
* OrderExchDelivProdMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("orderExchDelivProdMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class OrderExchDelivProdMapper  extends DaoBaseMapper {
//	public class OrderExchDelivProdMapper  extends DaoBaseMapper {

	public int getOrderExchDelivProdListCount( OrderExchDelivProdExDto orderExchDelivProdExDto ) throws Exception {
		return (Integer)selectByPk("orderExchDelivProdMapper.getOrderExchDelivProdListCount", orderExchDelivProdExDto);
	}
	
	public List<OrderExchDelivProdExDto> getOrderExchDelivProdList( OrderExchDelivProdExDto orderExchDelivProdExDto ) throws Exception {
		return (List<OrderExchDelivProdExDto>)list("orderExchDelivProdMapper.getOrderExchDelivProdList", orderExchDelivProdExDto);
	}
	
	public OrderExchDelivProdExDto getOrderExchDelivProd( OrderExchDelivProdExDto orderExchDelivProdExDto ) throws Exception {
		return (OrderExchDelivProdExDto) selectByPk("orderExchDelivProdMapper.getOrderExchDelivProd", orderExchDelivProdExDto);
	}

	public void insertOrderExchDelivProd( OrderExchDelivProdExDto orderExchDelivProdExDto ) throws Exception {
		insert("orderExchDelivProdMapper.insertOrderExchDelivProd", orderExchDelivProdExDto);
	}
	
	public void updateOrderExchDelivProd( OrderExchDelivProdExDto orderExchDelivProdExDto ) throws Exception {
		update("orderExchDelivProdMapper.updateOrderExchDelivProd", orderExchDelivProdExDto);
	}

	public void deleteOrderExchDelivProd( OrderExchDelivProdExDto orderExchDelivProdExDto ) throws Exception {
		delete("orderExchDelivProdMapper.deleteOrderExchDelivProd", orderExchDelivProdExDto);
	}

}
