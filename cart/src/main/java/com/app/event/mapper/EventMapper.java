package com.app.event.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.event.model.EventExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.event.mapper
* EventMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("eventMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class EventMapper  extends DaoBaseMapper {
//	public class EventMapper  extends DaoBaseMapper {

	public int getEventListCount( EventExDto eventExDto ) throws Exception {
		return (Integer)selectByPk("eventMapper.getEventListCount", eventExDto);
	}
	
	public List<EventExDto> getEventList( EventExDto eventExDto ) throws Exception {
		return (List<EventExDto>)list("eventMapper.getEventList", eventExDto);
	}
	
	public EventExDto getEvent( EventExDto eventExDto ) throws Exception {
		return (EventExDto) selectByPk("eventMapper.getEvent", eventExDto);
	}

	public void insertEvent( EventExDto eventExDto ) throws Exception {
		insert("eventMapper.insertEvent", eventExDto);
	}
	
	public void updateEvent( EventExDto eventExDto ) throws Exception {
		update("eventMapper.updateEvent", eventExDto);
	}

	public void deleteEvent( EventExDto eventExDto ) throws Exception {
		delete("eventMapper.deleteEvent", eventExDto);
	}

}
