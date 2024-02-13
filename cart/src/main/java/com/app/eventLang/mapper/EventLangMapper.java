package com.app.eventLang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.eventLang.model.EventLangExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.eventLang.mapper
* EventLangMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("eventLangMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class EventLangMapper  extends DaoBaseMapper {
//	public class EventLangMapper  extends DaoBaseMapper {

	public int getEventLangListCount( EventLangExDto eventLangExDto ) throws Exception {
		return (Integer)selectByPk("eventLangMapper.getEventLangListCount", eventLangExDto);
	}
	
	public List<EventLangExDto> getEventLangList( EventLangExDto eventLangExDto ) throws Exception {
		return (List<EventLangExDto>)list("eventLangMapper.getEventLangList", eventLangExDto);
	}
	
	public EventLangExDto getEventLang( EventLangExDto eventLangExDto ) throws Exception {
		return (EventLangExDto) selectByPk("eventLangMapper.getEventLang", eventLangExDto);
	}

	public void insertEventLang( EventLangExDto eventLangExDto ) throws Exception {
		insert("eventLangMapper.insertEventLang", eventLangExDto);
	}
	
	public void updateEventLang( EventLangExDto eventLangExDto ) throws Exception {
		update("eventLangMapper.updateEventLang", eventLangExDto);
	}

	public void deleteEventLang( EventLangExDto eventLangExDto ) throws Exception {
		delete("eventLangMapper.deleteEventLang", eventLangExDto);
	}

}
