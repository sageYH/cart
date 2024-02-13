package com.app.termsDets.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.termsDets.model.TermsDetsExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.termsDets.mapper
* TermsDetsMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("termsDetsMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class TermsDetsMapper  extends DaoBaseMapper {
//	public class TermsDetsMapper  extends DaoBaseMapper {

	public int getTermsDetsListCount( TermsDetsExDto termsDetsExDto ) throws Exception {
		return (Integer)selectByPk("termsDetsMapper.getTermsDetsListCount", termsDetsExDto);
	}
	
	public List<TermsDetsExDto> getTermsDetsList( TermsDetsExDto termsDetsExDto ) throws Exception {
		return (List<TermsDetsExDto>)list("termsDetsMapper.getTermsDetsList", termsDetsExDto);
	}
	
	public TermsDetsExDto getTermsDets( TermsDetsExDto termsDetsExDto ) throws Exception {
		return (TermsDetsExDto) selectByPk("termsDetsMapper.getTermsDets", termsDetsExDto);
	}

	public void insertTermsDets( TermsDetsExDto termsDetsExDto ) throws Exception {
		insert("termsDetsMapper.insertTermsDets", termsDetsExDto);
	}
	
	public void updateTermsDets( TermsDetsExDto termsDetsExDto ) throws Exception {
		update("termsDetsMapper.updateTermsDets", termsDetsExDto);
	}

	public void deleteTermsDets( TermsDetsExDto termsDetsExDto ) throws Exception {
		delete("termsDetsMapper.deleteTermsDets", termsDetsExDto);
	}

}
