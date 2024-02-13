package com.app.commSend.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.commSend.model.CommSendExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.commSend.mapper
* CommSendMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("commSendMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CommSendMapper  extends DaoBaseMapper {
//	public class CommSendMapper  extends DaoBaseMapper {

	public int getCommSendListCount( CommSendExDto commSendExDto ) throws Exception {
		return (Integer)selectByPk("commSendMapper.getCommSendListCount", commSendExDto);
	}
	
	public List<CommSendExDto> getCommSendList( CommSendExDto commSendExDto ) throws Exception {
		return (List<CommSendExDto>)list("commSendMapper.getCommSendList", commSendExDto);
	}
	
	public CommSendExDto getCommSend( CommSendExDto commSendExDto ) throws Exception {
		return (CommSendExDto) selectByPk("commSendMapper.getCommSend", commSendExDto);
	}

	public void insertCommSend( CommSendExDto commSendExDto ) throws Exception {
		insert("commSendMapper.insertCommSend", commSendExDto);
	}
	
	public void updateCommSend( CommSendExDto commSendExDto ) throws Exception {
		update("commSendMapper.updateCommSend", commSendExDto);
	}

	public void deleteCommSend( CommSendExDto commSendExDto ) throws Exception {
		delete("commSendMapper.deleteCommSend", commSendExDto);
	}

}
