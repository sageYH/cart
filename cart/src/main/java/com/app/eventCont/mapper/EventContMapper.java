package com.app.eventCont.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.eventCont.model.EventContExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.eventCont.mapper
* EventContMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("eventContMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class EventContMapper  extends DaoBaseMapper {
//	public class EventContMapper  extends DaoBaseMapper {

	public int getEventContListCount( EventContExDto eventContExDto ) throws Exception {
		return (Integer)selectByPk("eventContMapper.getEventContListCount", eventContExDto);
	}
	
	public List<EventContExDto> getEventContList( EventContExDto eventContExDto ) throws Exception {
		return (List<EventContExDto>)list("eventContMapper.getEventContList", eventContExDto);
	}
	
	public EventContExDto getEventCont( EventContExDto eventContExDto ) throws Exception {
		return (EventContExDto) selectByPk("eventContMapper.getEventCont", eventContExDto);
	}

	public void insertEventCont( EventContExDto eventContExDto ) throws Exception {
		insert("eventContMapper.insertEventCont", eventContExDto);
	}
	
	public void updateEventCont( EventContExDto eventContExDto ) throws Exception {
		update("eventContMapper.updateEventCont", eventContExDto);
	}

	public void deleteEventCont( EventContExDto eventContExDto ) throws Exception {
		delete("eventContMapper.deleteEventCont", eventContExDto);
	}

}
