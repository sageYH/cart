package com.app.terms.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.terms.model.TermsExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.terms.mapper
* TermsMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("termsMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class TermsMapper  extends DaoBaseMapper {
//	public class TermsMapper  extends DaoBaseMapper {

	public int getTermsListCount( TermsExDto termsExDto ) throws Exception {
		return (Integer)selectByPk("termsMapper.getTermsListCount", termsExDto);
	}
	
	public List<TermsExDto> getTermsList( TermsExDto termsExDto ) throws Exception {
		return (List<TermsExDto>)list("termsMapper.getTermsList", termsExDto);
	}
	
	public TermsExDto getTerms( TermsExDto termsExDto ) throws Exception {
		return (TermsExDto) selectByPk("termsMapper.getTerms", termsExDto);
	}

	public void insertTerms( TermsExDto termsExDto ) throws Exception {
		insert("termsMapper.insertTerms", termsExDto);
	}
	
	public void updateTerms( TermsExDto termsExDto ) throws Exception {
		update("termsMapper.updateTerms", termsExDto);
	}

	public void deleteTerms( TermsExDto termsExDto ) throws Exception {
		delete("termsMapper.deleteTerms", termsExDto);
	}

}
