package com.app.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.event.mapper.EventMapper;
import com.app.event.model.EventExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EventService {

	@Autowired
	private EventMapper eventMapper;

	public int getEventListCount(EventExDto eventExDto) throws Exception {
		return eventMapper.getEventListCount(eventExDto);
	}

	public List<EventExDto> getEventList(EventExDto eventExDto) throws Exception {
		return eventMapper.getEventList(eventExDto);
	}

	public EventExDto getEvent(EventExDto eventExDto) throws Exception {
		return eventMapper.getEvent(eventExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertEvent(EventExDto eventExDto) throws Exception {
		eventMapper.insertEvent(eventExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateEvent(EventExDto eventExDto) throws Exception {
		eventMapper.updateEvent(eventExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteEvent(EventExDto eventExDto) throws Exception {
		eventMapper.deleteEvent(eventExDto);
	}
}
