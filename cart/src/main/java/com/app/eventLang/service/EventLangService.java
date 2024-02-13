package com.app.eventLang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.eventLang.mapper.EventLangMapper;
import com.app.eventLang.model.EventLangExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EventLangService {

	@Autowired
	private EventLangMapper eventLangMapper;

	public int getEventLangListCount(EventLangExDto eventLangExDto) throws Exception {
		return eventLangMapper.getEventLangListCount(eventLangExDto);
	}

	public List<EventLangExDto> getEventLangList(EventLangExDto eventLangExDto) throws Exception {
		return eventLangMapper.getEventLangList(eventLangExDto);
	}

	public EventLangExDto getEventLang(EventLangExDto eventLangExDto) throws Exception {
		return eventLangMapper.getEventLang(eventLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertEventLang(EventLangExDto eventLangExDto) throws Exception {
		eventLangMapper.insertEventLang(eventLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateEventLang(EventLangExDto eventLangExDto) throws Exception {
		eventLangMapper.updateEventLang(eventLangExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteEventLang(EventLangExDto eventLangExDto) throws Exception {
		eventLangMapper.deleteEventLang(eventLangExDto);
	}
}
