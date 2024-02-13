package com.app.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.notice.mapper.NoticeMapper;
import com.app.notice.model.NoticeExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;

	public int getNoticeListCount(NoticeExDto noticeExDto) throws Exception {
		return noticeMapper.getNoticeListCount(noticeExDto);
	}

	public List<NoticeExDto> getNoticeList(NoticeExDto noticeExDto) throws Exception {
		return noticeMapper.getNoticeList(noticeExDto);
	}

	public NoticeExDto getNotice(NoticeExDto noticeExDto) throws Exception {
		return noticeMapper.getNotice(noticeExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertNotice(NoticeExDto noticeExDto) throws Exception {
		noticeMapper.insertNotice(noticeExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateNotice(NoticeExDto noticeExDto) throws Exception {
		noticeMapper.updateNotice(noticeExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteNotice(NoticeExDto noticeExDto) throws Exception {
		noticeMapper.deleteNotice(noticeExDto);
	}
}
