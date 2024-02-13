package com.app.buyPymt.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.buyPymt.model.BuyPymtExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.buyPymt.mapper
* BuyPymtMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("buyPymtMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class BuyPymtMapper  extends DaoBaseMapper {
//	public class BuyPymtMapper  extends DaoBaseMapper {

	public int getBuyPymtListCount( BuyPymtExDto buyPymtExDto ) throws Exception {
		return (Integer)selectByPk("buyPymtMapper.getBuyPymtListCount", buyPymtExDto);
	}
	
	public List<BuyPymtExDto> getBuyPymtList( BuyPymtExDto buyPymtExDto ) throws Exception {
		return (List<BuyPymtExDto>)list("buyPymtMapper.getBuyPymtList", buyPymtExDto);
	}
	
	public BuyPymtExDto getBuyPymt( BuyPymtExDto buyPymtExDto ) throws Exception {
		return (BuyPymtExDto) selectByPk("buyPymtMapper.getBuyPymt", buyPymtExDto);
	}

	public void insertBuyPymt( BuyPymtExDto buyPymtExDto ) throws Exception {
		insert("buyPymtMapper.insertBuyPymt", buyPymtExDto);
	}
	
	public void updateBuyPymt( BuyPymtExDto buyPymtExDto ) throws Exception {
		update("buyPymtMapper.updateBuyPymt", buyPymtExDto);
	}

	public void deleteBuyPymt( BuyPymtExDto buyPymtExDto ) throws Exception {
		delete("buyPymtMapper.deleteBuyPymt", buyPymtExDto);
	}

}
