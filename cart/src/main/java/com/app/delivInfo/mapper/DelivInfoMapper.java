package com.app.delivInfo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.delivInfo.model.DelivInfoExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.delivInfo.mapper
* DelivInfoMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("delivInfoMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class DelivInfoMapper  extends DaoBaseMapper {
//	public class DelivInfoMapper  extends DaoBaseMapper {

	public int getDelivInfoListCount( DelivInfoExDto delivInfoExDto ) throws Exception {
		return (Integer)selectByPk("delivInfoMapper.getDelivInfoListCount", delivInfoExDto);
	}
	
	public List<DelivInfoExDto> getDelivInfoList( DelivInfoExDto delivInfoExDto ) throws Exception {
		return (List<DelivInfoExDto>)list("delivInfoMapper.getDelivInfoList", delivInfoExDto);
	}
	
	public DelivInfoExDto getDelivInfo( DelivInfoExDto delivInfoExDto ) throws Exception {
		return (DelivInfoExDto) selectByPk("delivInfoMapper.getDelivInfo", delivInfoExDto);
	}

	public void insertDelivInfo( DelivInfoExDto delivInfoExDto ) throws Exception {
		insert("delivInfoMapper.insertDelivInfo", delivInfoExDto);
	}
	
	public void updateDelivInfo( DelivInfoExDto delivInfoExDto ) throws Exception {
		update("delivInfoMapper.updateDelivInfo", delivInfoExDto);
	}

	public void deleteDelivInfo( DelivInfoExDto delivInfoExDto ) throws Exception {
		delete("delivInfoMapper.deleteDelivInfo", delivInfoExDto);
	}

}
