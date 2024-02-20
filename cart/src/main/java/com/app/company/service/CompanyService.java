package com.app.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.company.mapper.CompanyMapper;
import com.app.company.model.CompanyExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CompanyService {

	@Autowired
	private CompanyMapper companyMapper;

	public int getCompanyListCount(CompanyExDto companyExDto) throws Exception {
		return companyMapper.getCompanyListCount(companyExDto);
	}

	public List<CompanyExDto> getCompanyList(CompanyExDto companyExDto) throws Exception {
		return companyMapper.getCompanyList(companyExDto);
	}

	public CompanyExDto getCompany(CompanyExDto companyExDto) throws Exception {
		return companyMapper.getCompany(companyExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCompany(CompanyExDto companyExDto) throws Exception {
		companyMapper.insertCompany(companyExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCompany(CompanyExDto companyExDto) throws Exception {
		companyMapper.updateCompany(companyExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCompany(CompanyExDto companyExDto) throws Exception {
		companyMapper.deleteCompany(companyExDto);
	}
}
