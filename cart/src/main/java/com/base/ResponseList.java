package com.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.ISO8601DateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.common.utils.MapUtil;

public class ResponseList implements Serializable {

	private static final long serialVersionUID = 5129263203552176273L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseList.class);

	private int totalRows;
	private List<?> payloads;
	
	public ResponseList() {
		this(new ArrayList<>(), 0);
	}

	public ResponseList(final List<?> payloads, final int totalRows){
		this.payloads = payloads;
		this.totalRows = totalRows;
	}

	public int gettotalRows(){
		return this.totalRows;
	}
	public void settotalRows(final int totalRows){
		this.totalRows = totalRows;
	}
	public List<?> getPayloads(){
		return this.payloads;
	}
	public void setPayloads(final List<?> payloads){
		this.payloads = payloads;
	}

	public String toJsonString(){
		final ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new ISO8601DateFormat());
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String result = "('totalRows':'0', 'payloads':[]}";
		try{
			result = mapper.writeValueAsString(this);
		}catch(final Exception e){
			ResponseList.LOGGER.trace(e.getMessage(), e);
		}
		return result;
	}
	
	public Map<String,Object> getResultMap(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("payloads", this.payloads);
		map.put("totalRows", this.totalRows);
		return map;
//		Map<String,Object> rtnMap = MapUtil.getObjToMap(map);
//		return rtnMap;
	}

	public List<?> getResultList(){
		return getPayloads();
	}
}
