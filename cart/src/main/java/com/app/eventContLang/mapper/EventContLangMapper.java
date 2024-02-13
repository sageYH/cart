package com.app.eventContLang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.eventContLang.model.EventContLangExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.eventContLang.mapper
* EventContLangMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("eventContLangMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class EventContLangMapper  extends DaoBaseMapper {
//	public class EventContLangMapper  extends DaoBaseMapper {

	public int getEventContLangListCount( EventContLangExDto eventContLangExDto ) throws Exception {
		return (Integer)selectByPk("eventContLangMapper.getEventContLangListCount", eventContLangExDto);
	}
	
	public List<EventContLangExDto> getEventContLangList( EventContLangExDto eventContLangExDto ) throws Exception {
		return (List<EventContLangExDto>)list("eventContLangMapper.getEventContLangList", eventContLangExDto);
	}
	
	public EventContLangExDto getEventContLang( EventContLangExDto eventContLangExDto ) throws Exception {
		return (EventContLangExDto) selectByPk("eventContLangMapper.getEventContLang", eventContLangExDto);
	}

	public void insertEventContLang( EventContLangExDto eventContLangExDto ) throws Exception {
		insert("eventContLangMapper.insertEventContLang", eventContLangExDto);
	}
	
	public void updateEventContLang( EventContLangExDto eventContLangExDto ) throws Exception {
		update("eventContLangMapper.updateEventContLang", eventContLangExDto);
	}

	public void deleteEventContLang( EventContLangExDto eventContLangExDto ) throws Exception {
		delete("eventContLangMapper.deleteEventContLang", eventContLangExDto);
	}

}
