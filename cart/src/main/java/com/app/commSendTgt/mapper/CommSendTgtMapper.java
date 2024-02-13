package com.app.commSendTgt.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.commSendTgt.model.CommSendTgtExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.commSendTgt.mapper
* CommSendTgtMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("commSendTgtMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CommSendTgtMapper  extends DaoBaseMapper {
//	public class CommSendTgtMapper  extends DaoBaseMapper {

	public int getCommSendTgtListCount( CommSendTgtExDto commSendTgtExDto ) throws Exception {
		return (Integer)selectByPk("commSendTgtMapper.getCommSendTgtListCount", commSendTgtExDto);
	}
	
	public List<CommSendTgtExDto> getCommSendTgtList( CommSendTgtExDto commSendTgtExDto ) throws Exception {
		return (List<CommSendTgtExDto>)list("commSendTgtMapper.getCommSendTgtList", commSendTgtExDto);
	}
	
	public CommSendTgtExDto getCommSendTgt( CommSendTgtExDto commSendTgtExDto ) throws Exception {
		return (CommSendTgtExDto) selectByPk("commSendTgtMapper.getCommSendTgt", commSendTgtExDto);
	}

	public void insertCommSendTgt( CommSendTgtExDto commSendTgtExDto ) throws Exception {
		insert("commSendTgtMapper.insertCommSendTgt", commSendTgtExDto);
	}
	
	public void updateCommSendTgt( CommSendTgtExDto commSendTgtExDto ) throws Exception {
		update("commSendTgtMapper.updateCommSendTgt", commSendTgtExDto);
	}

	public void deleteCommSendTgt( CommSendTgtExDto commSendTgtExDto ) throws Exception {
		delete("commSendTgtMapper.deleteCommSendTgt", commSendTgtExDto);
	}

}
