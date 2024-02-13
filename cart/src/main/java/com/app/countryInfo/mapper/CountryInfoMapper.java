package com.app.countryInfo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.countryInfo.model.CountryInfoExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.countryInfo.mapper
* CountryInfoMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("countryInfoMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class CountryInfoMapper  extends DaoBaseMapper {
//	public class CountryInfoMapper  extends DaoBaseMapper {

	public int getCountryInfoListCount( CountryInfoExDto countryInfoExDto ) throws Exception {
		return (Integer)selectByPk("countryInfoMapper.getCountryInfoListCount", countryInfoExDto);
	}
	
	public List<CountryInfoExDto> getCountryInfoList( CountryInfoExDto countryInfoExDto ) throws Exception {
		return (List<CountryInfoExDto>)list("countryInfoMapper.getCountryInfoList", countryInfoExDto);
	}
	
	public CountryInfoExDto getCountryInfo( CountryInfoExDto countryInfoExDto ) throws Exception {
		return (CountryInfoExDto) selectByPk("countryInfoMapper.getCountryInfo", countryInfoExDto);
	}

	public void insertCountryInfo( CountryInfoExDto countryInfoExDto ) throws Exception {
		insert("countryInfoMapper.insertCountryInfo", countryInfoExDto);
	}
	
	public void updateCountryInfo( CountryInfoExDto countryInfoExDto ) throws Exception {
		update("countryInfoMapper.updateCountryInfo", countryInfoExDto);
	}

	public void deleteCountryInfo( CountryInfoExDto countryInfoExDto ) throws Exception {
		delete("countryInfoMapper.deleteCountryInfo", countryInfoExDto);
	}

}
