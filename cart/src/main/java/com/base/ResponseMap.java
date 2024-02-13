package com.base;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.ISO8601DateFormat;

public class ResponseMap implements Serializable {

	private static final long serialVersionUID = 3060835519439306498L;

	private String resultCd;
	private String resultMsg;
	
	public ResponseMap() {
		this("N","");
	}
	public ResponseMap(final String resultCd, String resultMsg) {
		this.resultCd = resultCd;
		this.resultMsg = resultMsg;
	}
	
	public String getResultCd() {
		return this.resultCd;
	}
	public void setResultCd(final String resultCd) {
		this.resultCd = resultCd;
	}

	public String getResultMsg() {
		return this.resultMsg;
	}
	public void setResultMsg(final String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String toJsonString(){
		final ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new ISO8601DateFormat());
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String result = "('resultCd':'Y', 'resultMsg':''}";
		try{
			result = mapper.writeValueAsString(this);
		}catch(final Exception e){}
		return result;
	}
	
	public Map<String,Object> getResultMap(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("resultCd", this.resultCd);
		map.put("resultMsg", this.resultMsg);
		return map;
	}
}
