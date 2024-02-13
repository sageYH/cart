package com.app.countryInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.countryInfo.mapper.CountryInfoMapper;
import com.app.countryInfo.model.CountryInfoExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CountryInfoService {

	@Autowired
	private CountryInfoMapper countryInfoMapper;

	public int getCountryInfoListCount(CountryInfoExDto countryInfoExDto) throws Exception {
		return countryInfoMapper.getCountryInfoListCount(countryInfoExDto);
	}

	public List<CountryInfoExDto> getCountryInfoList(CountryInfoExDto countryInfoExDto) throws Exception {
		return countryInfoMapper.getCountryInfoList(countryInfoExDto);
	}

	public CountryInfoExDto getCountryInfo(CountryInfoExDto countryInfoExDto) throws Exception {
		return countryInfoMapper.getCountryInfo(countryInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCountryInfo(CountryInfoExDto countryInfoExDto) throws Exception {
		countryInfoMapper.insertCountryInfo(countryInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCountryInfo(CountryInfoExDto countryInfoExDto) throws Exception {
		countryInfoMapper.updateCountryInfo(countryInfoExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCountryInfo(CountryInfoExDto countryInfoExDto) throws Exception {
		countryInfoMapper.deleteCountryInfo(countryInfoExDto);
	}
}
