package com.app.orderDtl.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.orderDtl.model.OrderDtlExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.orderDtl.mapper
* OrderDtlMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("orderDtlMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class OrderDtlMapper  extends DaoBaseMapper {
//	public class OrderDtlMapper  extends DaoBaseMapper {

	public int getOrderDtlListCount( OrderDtlExDto orderDtlExDto ) throws Exception {
		return (Integer)selectByPk("orderDtlMapper.getOrderDtlListCount", orderDtlExDto);
	}
	
	public List<OrderDtlExDto> getOrderDtlList( OrderDtlExDto orderDtlExDto ) throws Exception {
		return (List<OrderDtlExDto>)list("orderDtlMapper.getOrderDtlList", orderDtlExDto);
	}
	
	public OrderDtlExDto getOrderDtl( OrderDtlExDto orderDtlExDto ) throws Exception {
		return (OrderDtlExDto) selectByPk("orderDtlMapper.getOrderDtl", orderDtlExDto);
	}

	public void insertOrderDtl( OrderDtlExDto orderDtlExDto ) throws Exception {
		insert("orderDtlMapper.insertOrderDtl", orderDtlExDto);
	}
	
	public void updateOrderDtl( OrderDtlExDto orderDtlExDto ) throws Exception {
		update("orderDtlMapper.updateOrderDtl", orderDtlExDto);
	}

	public void deleteOrderDtl( OrderDtlExDto orderDtlExDto ) throws Exception {
		delete("orderDtlMapper.deleteOrderDtl", orderDtlExDto);
	}

}
