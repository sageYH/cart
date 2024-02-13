package com.app.commInfo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.commInfo.model.CommInfoExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.commInfo.mapper
* CommInfoMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("commInfoMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CommInfoMapper  extends DaoBaseMapper {
//	public class CommInfoMapper  extends DaoBaseMapper {

	public int getCommInfoListCount( CommInfoExDto commInfoExDto ) throws Exception {
		return (Integer)selectByPk("commInfoMapper.getCommInfoListCount", commInfoExDto);
	}
	
	public List<CommInfoExDto> getCommInfoList( CommInfoExDto commInfoExDto ) throws Exception {
		return (List<CommInfoExDto>)list("commInfoMapper.getCommInfoList", commInfoExDto);
	}
	
	public CommInfoExDto getCommInfo( CommInfoExDto commInfoExDto ) throws Exception {
		return (CommInfoExDto) selectByPk("commInfoMapper.getCommInfo", commInfoExDto);
	}

	public void insertCommInfo( CommInfoExDto commInfoExDto ) throws Exception {
		insert("commInfoMapper.insertCommInfo", commInfoExDto);
	}
	
	public void updateCommInfo( CommInfoExDto commInfoExDto ) throws Exception {
		update("commInfoMapper.updateCommInfo", commInfoExDto);
	}

	public void deleteCommInfo( CommInfoExDto commInfoExDto ) throws Exception {
		delete("commInfoMapper.deleteCommInfo", commInfoExDto);
	}

}
