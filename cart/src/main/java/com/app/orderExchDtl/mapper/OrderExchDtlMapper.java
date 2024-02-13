package com.app.orderExchDtl.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.orderExchDtl.model.OrderExchDtlExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.orderExchDtl.mapper
* OrderExchDtlMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("orderExchDtlMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class OrderExchDtlMapper  extends DaoBaseMapper {
//	public class OrderExchDtlMapper  extends DaoBaseMapper {

	public int getOrderExchDtlListCount( OrderExchDtlExDto orderExchDtlExDto ) throws Exception {
		return (Integer)selectByPk("orderExchDtlMapper.getOrderExchDtlListCount", orderExchDtlExDto);
	}
	
	public List<OrderExchDtlExDto> getOrderExchDtlList( OrderExchDtlExDto orderExchDtlExDto ) throws Exception {
		return (List<OrderExchDtlExDto>)list("orderExchDtlMapper.getOrderExchDtlList", orderExchDtlExDto);
	}
	
	public OrderExchDtlExDto getOrderExchDtl( OrderExchDtlExDto orderExchDtlExDto ) throws Exception {
		return (OrderExchDtlExDto) selectByPk("orderExchDtlMapper.getOrderExchDtl", orderExchDtlExDto);
	}

	public void insertOrderExchDtl( OrderExchDtlExDto orderExchDtlExDto ) throws Exception {
		insert("orderExchDtlMapper.insertOrderExchDtl", orderExchDtlExDto);
	}
	
	public void updateOrderExchDtl( OrderExchDtlExDto orderExchDtlExDto ) throws Exception {
		update("orderExchDtlMapper.updateOrderExchDtl", orderExchDtlExDto);
	}

	public void deleteOrderExchDtl( OrderExchDtlExDto orderExchDtlExDto ) throws Exception {
		delete("orderExchDtlMapper.deleteOrderExchDtl", orderExchDtlExDto);
	}

}
