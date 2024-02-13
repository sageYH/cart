package com.app.pymtAgcyComp.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.pymtAgcyComp.model.PymtAgcyCompExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.pymtAgcyComp.mapper
* PymtAgcyCompMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("pymtAgcyCompMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class PymtAgcyCompMapper  extends DaoBaseMapper {
//	public class PymtAgcyCompMapper  extends DaoBaseMapper {

	public int getPymtAgcyCompListCount( PymtAgcyCompExDto pymtAgcyCompExDto ) throws Exception {
		return (Integer)selectByPk("pymtAgcyCompMapper.getPymtAgcyCompListCount", pymtAgcyCompExDto);
	}
	
	public List<PymtAgcyCompExDto> getPymtAgcyCompList( PymtAgcyCompExDto pymtAgcyCompExDto ) throws Exception {
		return (List<PymtAgcyCompExDto>)list("pymtAgcyCompMapper.getPymtAgcyCompList", pymtAgcyCompExDto);
	}
	
	public PymtAgcyCompExDto getPymtAgcyComp( PymtAgcyCompExDto pymtAgcyCompExDto ) throws Exception {
		return (PymtAgcyCompExDto) selectByPk("pymtAgcyCompMapper.getPymtAgcyComp", pymtAgcyCompExDto);
	}

	public void insertPymtAgcyComp( PymtAgcyCompExDto pymtAgcyCompExDto ) throws Exception {
		insert("pymtAgcyCompMapper.insertPymtAgcyComp", pymtAgcyCompExDto);
	}
	
	public void updatePymtAgcyComp( PymtAgcyCompExDto pymtAgcyCompExDto ) throws Exception {
		update("pymtAgcyCompMapper.updatePymtAgcyComp", pymtAgcyCompExDto);
	}

	public void deletePymtAgcyComp( PymtAgcyCompExDto pymtAgcyCompExDto ) throws Exception {
		delete("pymtAgcyCompMapper.deletePymtAgcyComp", pymtAgcyCompExDto);
	}

}
