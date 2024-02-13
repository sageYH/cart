package com.app.pointInfo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.pointInfo.model.PointInfoExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.pointInfo.mapper
* PointInfoMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("pointInfoMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class PointInfoMapper  extends DaoBaseMapper {
//	public class PointInfoMapper  extends DaoBaseMapper {

	public int getPointInfoListCount( PointInfoExDto pointInfoExDto ) throws Exception {
		return (Integer)selectByPk("pointInfoMapper.getPointInfoListCount", pointInfoExDto);
	}
	
	public List<PointInfoExDto> getPointInfoList( PointInfoExDto pointInfoExDto ) throws Exception {
		return (List<PointInfoExDto>)list("pointInfoMapper.getPointInfoList", pointInfoExDto);
	}
	
	public PointInfoExDto getPointInfo( PointInfoExDto pointInfoExDto ) throws Exception {
		return (PointInfoExDto) selectByPk("pointInfoMapper.getPointInfo", pointInfoExDto);
	}

	public void insertPointInfo( PointInfoExDto pointInfoExDto ) throws Exception {
		insert("pointInfoMapper.insertPointInfo", pointInfoExDto);
	}
	
	public void updatePointInfo( PointInfoExDto pointInfoExDto ) throws Exception {
		update("pointInfoMapper.updatePointInfo", pointInfoExDto);
	}

	public void deletePointInfo( PointInfoExDto pointInfoExDto ) throws Exception {
		delete("pointInfoMapper.deletePointInfo", pointInfoExDto);
	}

}
