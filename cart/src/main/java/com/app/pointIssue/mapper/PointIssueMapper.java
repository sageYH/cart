package com.app.pointIssue.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.pointIssue.model.PointIssueExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.pointIssue.mapper
* PointIssueMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("pointIssueMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class PointIssueMapper  extends DaoBaseMapper {
//	public class PointIssueMapper  extends DaoBaseMapper {

	public int getPointIssueListCount( PointIssueExDto pointIssueExDto ) throws Exception {
		return (Integer)selectByPk("pointIssueMapper.getPointIssueListCount", pointIssueExDto);
	}
	
	public List<PointIssueExDto> getPointIssueList( PointIssueExDto pointIssueExDto ) throws Exception {
		return (List<PointIssueExDto>)list("pointIssueMapper.getPointIssueList", pointIssueExDto);
	}
	
	public PointIssueExDto getPointIssue( PointIssueExDto pointIssueExDto ) throws Exception {
		return (PointIssueExDto) selectByPk("pointIssueMapper.getPointIssue", pointIssueExDto);
	}

	public void insertPointIssue( PointIssueExDto pointIssueExDto ) throws Exception {
		insert("pointIssueMapper.insertPointIssue", pointIssueExDto);
	}
	
	public void updatePointIssue( PointIssueExDto pointIssueExDto ) throws Exception {
		update("pointIssueMapper.updatePointIssue", pointIssueExDto);
	}

	public void deletePointIssue( PointIssueExDto pointIssueExDto ) throws Exception {
		delete("pointIssueMapper.deletePointIssue", pointIssueExDto);
	}

}
