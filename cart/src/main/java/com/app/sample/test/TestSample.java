package com.app.sample.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.common.utils.ClassUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestSample {

	public static void main(String[] args) {
		test();
	}

	public static void test(){
		try {
			HashMap<String,Object> hm = new HashMap<String,Object>();
			hm.put("string1","String.........");
			hm.put("short1","10");
			hm.put("int1","20");
//			hm.put("long1","300.99999");
//			hm.put("float1",null);
			hm.put("double1","444.333");
			hm.put("char1","c");
			hm.put("boolean1","1");
//			hm.put("BigInteger1","656666");
//			hm.put("BigDecimal1","55.66");
			List<String> l1 = new ArrayList<String>();
			l1.add("s1");
			l1.add("s2");
			hm.put("lStr",l1);
			TestVo tv = new TestVo();
			tv = (TestVo)ClassUtil.getMapToClass(hm, tv);
			@SuppressWarnings("unchecked")
			List<String> lstRst = (List<String>)ClassUtil.castList(new String(),l1);
			List<TestVo> lTv1 = new ArrayList<TestVo>();
			TestVo tv1 = new TestVo();
			tv1 = ClassUtil.getCopyObj(tv, tv1);
			lTv1.add(tv1);
			tv.setlStr(lTv1);
			ObjectMapper mapper = new ObjectMapper();
//			MultiValueMap<String, String> mm = multiMpaConvert(mapper, tv);

			System.out.println("tv.............."+mapper.writeValueAsString(tv));
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}

	public static MultiValueMap<String, String> multiMpaConvert(ObjectMapper objectMapper, Object dto) { // (2)
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			Map<String, String> map = objectMapper.convertValue(dto, new TypeReference<Map<String, String>>() {}); // (3)
			params.setAll(map); // (4)

			return params;
		} catch (Exception e) {
			throw new IllegalStateException("Url Parameter 변환중 오류가 발생했습니다.");
		}
	}
}
