package com.app.faq.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.faq.model.FaqExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.faq.mapper
* FaqMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("faqMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class FaqMapper  extends DaoBaseMapper {
//	public class FaqMapper  extends DaoBaseMapper {

	public int getFaqListCount( FaqExDto faqExDto ) throws Exception {
		return (Integer)selectByPk("faqMapper.getFaqListCount", faqExDto);
	}
	
	public List<FaqExDto> getFaqList( FaqExDto faqExDto ) throws Exception {
		return (List<FaqExDto>)list("faqMapper.getFaqList", faqExDto);
	}
	
	public FaqExDto getFaq( FaqExDto faqExDto ) throws Exception {
		return (FaqExDto) selectByPk("faqMapper.getFaq", faqExDto);
	}

	public void insertFaq( FaqExDto faqExDto ) throws Exception {
		insert("faqMapper.insertFaq", faqExDto);
	}
	
	public void updateFaq( FaqExDto faqExDto ) throws Exception {
		update("faqMapper.updateFaq", faqExDto);
	}

	public void deleteFaq( FaqExDto faqExDto ) throws Exception {
		delete("faqMapper.deleteFaq", faqExDto);
	}

}
