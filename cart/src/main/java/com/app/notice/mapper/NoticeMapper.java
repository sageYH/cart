package com.app.notice.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.notice.model.NoticeExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.notice.mapper
* NoticeMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("noticeMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class NoticeMapper  extends DaoBaseMapper {
//	public class NoticeMapper  extends DaoBaseMapper {

	public int getNoticeListCount( NoticeExDto noticeExDto ) throws Exception {
		return (Integer)selectByPk("noticeMapper.getNoticeListCount", noticeExDto);
	}
	
	public List<NoticeExDto> getNoticeList( NoticeExDto noticeExDto ) throws Exception {
		return (List<NoticeExDto>)list("noticeMapper.getNoticeList", noticeExDto);
	}
	
	public NoticeExDto getNotice( NoticeExDto noticeExDto ) throws Exception {
		return (NoticeExDto) selectByPk("noticeMapper.getNotice", noticeExDto);
	}

	public void insertNotice( NoticeExDto noticeExDto ) throws Exception {
		insert("noticeMapper.insertNotice", noticeExDto);
	}
	
	public void updateNotice( NoticeExDto noticeExDto ) throws Exception {
		update("noticeMapper.updateNotice", noticeExDto);
	}

	public void deleteNotice( NoticeExDto noticeExDto ) throws Exception {
		delete("noticeMapper.deleteNotice", noticeExDto);
	}

}
