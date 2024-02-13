package com.app.pointPaid.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.pointPaid.model.PointPaidExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.pointPaid.mapper
* PointPaidMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("pointPaidMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class PointPaidMapper  extends DaoBaseMapper {
//	public class PointPaidMapper  extends DaoBaseMapper {

	public int getPointPaidListCount( PointPaidExDto pointPaidExDto ) throws Exception {
		return (Integer)selectByPk("pointPaidMapper.getPointPaidListCount", pointPaidExDto);
	}
	
	public List<PointPaidExDto> getPointPaidList( PointPaidExDto pointPaidExDto ) throws Exception {
		return (List<PointPaidExDto>)list("pointPaidMapper.getPointPaidList", pointPaidExDto);
	}
	
	public PointPaidExDto getPointPaid( PointPaidExDto pointPaidExDto ) throws Exception {
		return (PointPaidExDto) selectByPk("pointPaidMapper.getPointPaid", pointPaidExDto);
	}

	public void insertPointPaid( PointPaidExDto pointPaidExDto ) throws Exception {
		insert("pointPaidMapper.insertPointPaid", pointPaidExDto);
	}
	
	public void updatePointPaid( PointPaidExDto pointPaidExDto ) throws Exception {
		update("pointPaidMapper.updatePointPaid", pointPaidExDto);
	}

	public void deletePointPaid( PointPaidExDto pointPaidExDto ) throws Exception {
		delete("pointPaidMapper.deletePointPaid", pointPaidExDto);
	}

}
