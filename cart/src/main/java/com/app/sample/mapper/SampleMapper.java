package com.app.sample.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.base.DaoBaseMapper;

@Repository("sampleMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class SampleMapper extends DaoBaseMapper {

	public List<Map<String,Object>> getDeptList( Map<String,Object> map ) throws Exception {
		return (List<Map<String,Object>>)list("sampleMapper.getDeptList", map);
	}
}
