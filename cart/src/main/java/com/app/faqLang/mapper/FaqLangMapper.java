package com.app.faqLang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.faqLang.model.FaqLangExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.faqLang.mapper
* FaqLangMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("faqLangMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class FaqLangMapper  extends DaoBaseMapper {
//	public class FaqLangMapper  extends DaoBaseMapper {

	public int getFaqLangListCount( FaqLangExDto faqLangExDto ) throws Exception {
		return (Integer)selectByPk("faqLangMapper.getFaqLangListCount", faqLangExDto);
	}
	
	public List<FaqLangExDto> getFaqLangList( FaqLangExDto faqLangExDto ) throws Exception {
		return (List<FaqLangExDto>)list("faqLangMapper.getFaqLangList", faqLangExDto);
	}
	
	public FaqLangExDto getFaqLang( FaqLangExDto faqLangExDto ) throws Exception {
		return (FaqLangExDto) selectByPk("faqLangMapper.getFaqLang", faqLangExDto);
	}

	public void insertFaqLang( FaqLangExDto faqLangExDto ) throws Exception {
		insert("faqLangMapper.insertFaqLang", faqLangExDto);
	}
	
	public void updateFaqLang( FaqLangExDto faqLangExDto ) throws Exception {
		update("faqLangMapper.updateFaqLang", faqLangExDto);
	}

	public void deleteFaqLang( FaqLangExDto faqLangExDto ) throws Exception {
		delete("faqLangMapper.deleteFaqLang", faqLangExDto);
	}

}
