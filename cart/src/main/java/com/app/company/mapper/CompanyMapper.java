package com.app.company.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.company.model.CompanyExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.company.mapper
* CompanyMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("companyMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CompanyMapper  extends DaoBaseMapper {
//	public class CompanyMapper  extends DaoBaseMapper {

	public int getCompanyListCount( CompanyExDto companyExDto ) throws Exception {
		return (Integer)selectByPk("companyMapper.getCompanyListCount", companyExDto);
	}
	
	public List<CompanyExDto> getCompanyList( CompanyExDto companyExDto ) throws Exception {
		return (List<CompanyExDto>)list("companyMapper.getCompanyList", companyExDto);
	}
	
	public CompanyExDto getCompany( CompanyExDto companyExDto ) throws Exception {
		return (CompanyExDto) selectByPk("companyMapper.getCompany", companyExDto);
	}

	public void insertCompany( CompanyExDto companyExDto ) throws Exception {
		insert("companyMapper.insertCompany", companyExDto);
	}
	
	public void updateCompany( CompanyExDto companyExDto ) throws Exception {
		update("companyMapper.updateCompany", companyExDto);
	}

	public void deleteCompany( CompanyExDto companyExDto ) throws Exception {
		delete("companyMapper.deleteCompany", companyExDto);
	}

}
