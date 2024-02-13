package com.app.cart.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.cart.model.CartExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.cart.mapper
* CartMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("cartMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CartMapper  extends DaoBaseMapper {
//	public class CartMapper  extends DaoBaseMapper {

	public int getCartListCount( CartExDto cartExDto ) throws Exception {
		return (Integer)selectByPk("cartMapper.getCartListCount", cartExDto);
	}
	
	public List<CartExDto> getCartList( CartExDto cartExDto ) throws Exception {
		return (List<CartExDto>)list("cartMapper.getCartList", cartExDto);
	}
	
	public CartExDto getCart( CartExDto cartExDto ) throws Exception {
		return (CartExDto) selectByPk("cartMapper.getCart", cartExDto);
	}

	public void insertCart( CartExDto cartExDto ) throws Exception {
		insert("cartMapper.insertCart", cartExDto);
	}
	
	public void updateCart( CartExDto cartExDto ) throws Exception {
		update("cartMapper.updateCart", cartExDto);
	}

	public void deleteCart( CartExDto cartExDto ) throws Exception {
		delete("cartMapper.deleteCart", cartExDto);
	}

}
