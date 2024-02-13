package com.app.eventCont.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.eventCont.mapper.EventContMapper;
import com.app.eventCont.model.EventContExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EventContService {

	@Autowired
	private EventContMapper eventContMapper;

	public int getEventContListCount(EventContExDto eventContExDto) throws Exception {
		return eventContMapper.getEventContListCount(eventContExDto);
	}

	public List<EventContExDto> getEventContList(EventContExDto eventContExDto) throws Exception {
		return eventContMapper.getEventContList(eventContExDto);
	}

	public EventContExDto getEventCont(EventContExDto eventContExDto) throws Exception {
		return eventContMapper.getEventCont(eventContExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertEventCont(EventContExDto eventContExDto) throws Exception {
		eventContMapper.insertEventCont(eventContExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateEventCont(EventContExDto eventContExDto) throws Exception {
		eventContMapper.updateEventCont(eventContExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteEventCont(EventContExDto eventContExDto) throws Exception {
		eventContMapper.deleteEventCont(eventContExDto);
	}
}
