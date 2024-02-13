package com.app.eventContLang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.eventContLang.mapper.EventContLangMapper;
import com.app.eventContLang.model.EventContLangExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EventContLangService {

	@Autowired
	private EventContLangMapper eventContLangMapper;

	public int getEventContLangListCount(EventContLangExDto eventContLangExDto) throws Exception {
		return eventContLangMapper.getEventContLangListCount(eventContLangExDto);
	}

	public List<EventContLangExDto> getEventContLangList(EventContLangExDto eventContLangExDto) throws Exception {
		return eventContLangMapper.getEventContLangList(eventContLangExDto);
	}

	public EventContLangExDto getEventContLang(EventContLangExDto eventContLangExDto) throws Exception {
		return eventContLangMapper.getEventContLang(eventContLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertEventContLang(EventContLangExDto eventContLangExDto) throws Exception {
		eventContLangMapper.insertEventContLang(eventContLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateEventContLang(EventContLangExDto eventContLangExDto) throws Exception {
		eventContLangMapper.updateEventContLang(eventContLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteEventContLang(EventContLangExDto eventContLangExDto) throws Exception {
		eventContLangMapper.deleteEventContLang(eventContLangExDto);
	}
}
