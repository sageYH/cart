package com.app.orderDtlOpt.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.orderDtlOpt.model.OrderDtlOptExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.orderDtlOpt.mapper
* OrderDtlOptMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("orderDtlOptMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class OrderDtlOptMapper  extends DaoBaseMapper {
//	public class OrderDtlOptMapper  extends DaoBaseMapper {

	public int getOrderDtlOptListCount( OrderDtlOptExDto orderDtlOptExDto ) throws Exception {
		return (Integer)selectByPk("orderDtlOptMapper.getOrderDtlOptListCount", orderDtlOptExDto);
	}
	
	public List<OrderDtlOptExDto> getOrderDtlOptList( OrderDtlOptExDto orderDtlOptExDto ) throws Exception {
		return (List<OrderDtlOptExDto>)list("orderDtlOptMapper.getOrderDtlOptList", orderDtlOptExDto);
	}
	
	public OrderDtlOptExDto getOrderDtlOpt( OrderDtlOptExDto orderDtlOptExDto ) throws Exception {
		return (OrderDtlOptExDto) selectByPk("orderDtlOptMapper.getOrderDtlOpt", orderDtlOptExDto);
	}

	public void insertOrderDtlOpt( OrderDtlOptExDto orderDtlOptExDto ) throws Exception {
		insert("orderDtlOptMapper.insertOrderDtlOpt", orderDtlOptExDto);
	}
	
	public void updateOrderDtlOpt( OrderDtlOptExDto orderDtlOptExDto ) throws Exception {
		update("orderDtlOptMapper.updateOrderDtlOpt", orderDtlOptExDto);
	}

	public void deleteOrderDtlOpt( OrderDtlOptExDto orderDtlOptExDto ) throws Exception {
		delete("orderDtlOptMapper.deleteOrderDtlOpt", orderDtlOptExDto);
	}

}
