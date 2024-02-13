package com.app.cartOption.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.cartOption.model.CartOptionExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.cartOption.mapper
* CartOptionMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("cartOptionMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CartOptionMapper  extends DaoBaseMapper {
//	public class CartOptionMapper  extends DaoBaseMapper {

	public int getCartOptionListCount( CartOptionExDto cartOptionExDto ) throws Exception {
		return (Integer)selectByPk("cartOptionMapper.getCartOptionListCount", cartOptionExDto);
	}
	
	public List<CartOptionExDto> getCartOptionList( CartOptionExDto cartOptionExDto ) throws Exception {
		return (List<CartOptionExDto>)list("cartOptionMapper.getCartOptionList", cartOptionExDto);
	}
	
	public CartOptionExDto getCartOption( CartOptionExDto cartOptionExDto ) throws Exception {
		return (CartOptionExDto) selectByPk("cartOptionMapper.getCartOption", cartOptionExDto);
	}

	public void insertCartOption( CartOptionExDto cartOptionExDto ) throws Exception {
		insert("cartOptionMapper.insertCartOption", cartOptionExDto);
	}
	
	public void updateCartOption( CartOptionExDto cartOptionExDto ) throws Exception {
		update("cartOptionMapper.updateCartOption", cartOptionExDto);
	}

	public void deleteCartOption( CartOptionExDto cartOptionExDto ) throws Exception {
		delete("cartOptionMapper.deleteCartOption", cartOptionExDto);
	}

}
