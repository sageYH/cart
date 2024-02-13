package com.app.prodExposure.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.prodExposure.model.ProdExposureExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.prodExposure.mapper
* ProdExposureMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("prodExposureMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class ProdExposureMapper  extends DaoBaseMapper {
//	public class ProdExposureMapper  extends DaoBaseMapper {

	public int getProdExposureListCount( ProdExposureExDto prodExposureExDto ) throws Exception {
		return (Integer)selectByPk("prodExposureMapper.getProdExposureListCount", prodExposureExDto);
	}
	
	public List<ProdExposureExDto> getProdExposureList( ProdExposureExDto prodExposureExDto ) throws Exception {
		return (List<ProdExposureExDto>)list("prodExposureMapper.getProdExposureList", prodExposureExDto);
	}
	
	public ProdExposureExDto getProdExposure( ProdExposureExDto prodExposureExDto ) throws Exception {
		return (ProdExposureExDto) selectByPk("prodExposureMapper.getProdExposure", prodExposureExDto);
	}

	public void insertProdExposure( ProdExposureExDto prodExposureExDto ) throws Exception {
		insert("prodExposureMapper.insertProdExposure", prodExposureExDto);
	}
	
	public void updateProdExposure( ProdExposureExDto prodExposureExDto ) throws Exception {
		update("prodExposureMapper.updateProdExposure", prodExposureExDto);
	}

	public void deleteProdExposure( ProdExposureExDto prodExposureExDto ) throws Exception {
		delete("prodExposureMapper.deleteProdExposure", prodExposureExDto);
	}

}
