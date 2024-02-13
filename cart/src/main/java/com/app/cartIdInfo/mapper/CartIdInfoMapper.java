package com.app.cartIdInfo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.cartIdInfo.model.CartIdInfoExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.cartIdInfo.mapper
* CartIdInfoMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("cartIdInfoMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CartIdInfoMapper  extends DaoBaseMapper {
//	public class CartIdInfoMapper  extends DaoBaseMapper {

	public int getCartIdInfoListCount( CartIdInfoExDto cartIdInfoExDto ) throws Exception {
		return (Integer)selectByPk("cartIdInfoMapper.getCartIdInfoListCount", cartIdInfoExDto);
	}
	
	public List<CartIdInfoExDto> getCartIdInfoList( CartIdInfoExDto cartIdInfoExDto ) throws Exception {
		return (List<CartIdInfoExDto>)list("cartIdInfoMapper.getCartIdInfoList", cartIdInfoExDto);
	}
	
	public CartIdInfoExDto getCartIdInfo( CartIdInfoExDto cartIdInfoExDto ) throws Exception {
		return (CartIdInfoExDto) selectByPk("cartIdInfoMapper.getCartIdInfo", cartIdInfoExDto);
	}

	public void insertCartIdInfo( CartIdInfoExDto cartIdInfoExDto ) throws Exception {
		insert("cartIdInfoMapper.insertCartIdInfo", cartIdInfoExDto);
	}
	
	public void updateCartIdInfo( CartIdInfoExDto cartIdInfoExDto ) throws Exception {
		update("cartIdInfoMapper.updateCartIdInfo", cartIdInfoExDto);
	}

	public void deleteCartIdInfo( CartIdInfoExDto cartIdInfoExDto ) throws Exception {
		delete("cartIdInfoMapper.deleteCartIdInfo", cartIdInfoExDto);
	}

}
