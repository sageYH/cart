package com.common.utils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.ui.Model;

/*
getStrToMap		//string to map
getMapToStr		//map to string
getListToStr	//list to string
getClassToStr	//class to string
getObjToMap		//map,class to map
getObjToList	//list to list(extends class)
requestToModel
*/
public class MapUtil {

	//string -> map
	public static Map<String,Object> getStrToMap(String data) {
		try {
			JSONParser parser = new JSONParser();
			JSONObject jObj = (JSONObject)parser.parse(data);

			return (Map<String,Object>)setJsonToMap(jObj);
		}catch(Exception e) {
			return (new HashMap<String,Object>());
		}
	}
	public static Map<String,Object> setJsonToMap(JSONObject json) throws Exception
	{
		Map<String, Object> retMap = new HashMap<String, Object>();

		if(json != null)
		{
			retMap = toJsonMap(json);
		}
		return retMap;
	}
	public static Map<String,Object> toJsonMap(JSONObject object) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator<?> iterator = object.entrySet().iterator();
		while (iterator.hasNext()){
			Entry<?,?> entry = (Entry<?,?>)iterator.next();
			String key = (String)entry.getKey();
			Object value = object.get(key);
			if(value instanceof JSONArray){
				Object o1 = ((JSONArray) value).get(0);
				if (o1 instanceof String || o1 instanceof Integer || o1 instanceof Long || o1 instanceof Double || o1 instanceof Float){
					List<Object> list = new ArrayList<Object>();
					for (Object obj : (JSONArray) value) {
						list.add(obj);
					}
					value = list;
				}
				else{
					value = toJsonList((JSONArray) value);
				}
			}
			else if(value instanceof JSONObject){
				value = toJsonMap((JSONObject) value);
			}
			if (value==null) value="";
			if (value instanceof String){
				map.put(key, enStr(value));
			}
			else{
				map.put(key, value);
			}
		}

		return map;
	}
	public static List<Object> toJsonList(JSONArray array) throws Exception
	{
		List<Object> list = new ArrayList<Object>();

		for (Object obj : array) {
			//no list for structure
			if(obj instanceof JSONArray){
				obj = toJsonList((JSONArray) obj);
				list.add(obj);
			}
			else if(obj instanceof JSONObject){
				obj = toJsonMap((JSONObject) obj);
				list.add(obj);
			}
			else{
				if (obj==null) obj = "";
				list.add(obj);
			}
		}
		return list;
	}
	public static Object enStr(Object obj){
		if (obj instanceof String){
			try{
				String s1 = obj+"";
				return s1;
			}
			catch(Exception e){
				return obj;
			}
		}
		else{
			return obj;
		}
	}
	public Object deStr(Object obj){
		if (obj instanceof String){
			try{
				String s1 = obj+"";
				return s1;
			}
			catch(Exception e){
				return obj;
			}
		}
		else{
			return obj;
		}
	}

	//Map to String
	public static String getMapToStr(Map<?,?> map){
		String sMap = "";
		try{
			if (map==null) return "";
			sMap = toMapStr(map);
		}
		catch(Exception e){}
		return sMap;
	}
	//List to String
	public static String getListToStr(List<?> array){
		String sList = "";
		try{
			if (array==null) return "";
			sList = toListStr(array);
		}
		catch(Exception e){}
		return sList;
	}
	//Classs to String
	public static <T> String getClassToStr(T cls){
		String sCls = "";
		try{
			if (cls==null) return "";
			Map<String,Object> map = ClassUtil.getClassToMap(cls);
			sCls = (toMapStr(map));
		}
		catch(Exception e){}
		return sCls;
	}
	public static String toMapStr(Map<?,?> object) throws Exception
	{
		String strRtn = "{";
		int cnt = 0;

		Iterator<?> iterator = object.entrySet().iterator();
		while (iterator.hasNext()){
			if (cnt > 0) {
				strRtn += ",";
			}
			cnt++;
			Entry<?,?> entry = (Entry<?,?>)iterator.next();
			String key = (String)entry.getKey();
			strRtn += ("\"" + key + "\":");
			
			Object value = object.get(key);
			if(value instanceof List){
				strRtn += (toListStr((List<?>) value));
			}
			else if(value instanceof Map){
				strRtn += (toMapStr((Map<?,?>) value));
			}
			else if (value==null || value instanceof String || value instanceof Integer || value instanceof Long || value instanceof Double || value instanceof Float){
				
				String v2 = "";
				if (value==null) {
					v2="null";
				}
				else {
					if (value instanceof String){
						v2 = "\"" + value + "\"";
					}
					else {
						v2 = value + "";
					}
				}
				strRtn += (v2);
			}
			else {
				//class
				Map<String,Object> map = ClassUtil.getClassToMap(value);
				strRtn += (toMapStr(map));
			}
		}
		
		strRtn += "}";

		return strRtn;
	}
    public static String toListStr(List<?> array) throws Exception
    {
    	String strRtn = "[";

    	int cntList = 0;
		for (Object obj : array) {
			if (cntList > 0) {
				strRtn += ",";
			}
			//no list for structure
			if(obj instanceof List){
				strRtn += (toListStr((List<?>)obj));
			}
			else if(obj instanceof Map){
				strRtn += (toMapStr((Map<?,?>) obj));
            }
			else if (obj==null || obj instanceof String || obj instanceof Integer || obj instanceof Long || obj instanceof Double || obj instanceof Float){
				String v2 = "";
				if (obj==null) {
					obj="null";
				}
				else {
					if (obj instanceof String){
						v2 = "\"" + obj + "\"";
					}
					else {
						v2 = obj + "";
					}
				}
				strRtn += (v2);
			}
			else{
				//class
				Map<String,Object> map = ClassUtil.getClassToMap(obj);
				strRtn += (toMapStr(map));
			}

			cntList++;
		}
		
		strRtn += "]";
		
        return strRtn;
    }
    
	//only map or class applied
	public static <T> Map<String,Object> getObjToMap(T obj){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (obj==null) return null;
			map = setObjToMap(obj);
		}
		catch(Exception e){}
		return map;
	}
	//list
	public static <T> List<?> getObjToList(T obj){
		List<?> list = new ArrayList<Object>();
		try{
			if (obj==null) return null;
			list = toList((List<?>)obj);
		}
		catch(Exception e){}
		return list;
	}
	public static <T> Map<String,Object> setObjToMap(T obj) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>();

		if(obj instanceof Map){
			map = toMap((Map<?,?>) obj);
		}
		else{
			//class
			Map<String,Object> clsMap = ClassUtil.getClassToMap(obj);
			map = toMap(clsMap);
		}

		return map;
	}

	public static Map<String,Object> toMap(Map<?,?> object) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator<?> iterator = object.entrySet().iterator();
		while (iterator.hasNext()){
			Entry<?,?> entry = (Entry<?,?>)iterator.next();
			String key = (String)entry.getKey();
			Object value = object.get(key);
			if(value instanceof List){
				Object o1 = ((List<?>) value).get(0);
				if (o1 instanceof String || o1 instanceof Integer || o1 instanceof Long || o1 instanceof Double || o1 instanceof Float){
					List<Object> list = new ArrayList<Object>();
					for (Object obj : (List<?>) value) {
						list.add(obj);
					}
					value = list;
				}
				else{
					value = toList((List<?>) value);
				}
			}
			else if(value instanceof Map){
				value = toMap((Map<?,?>) value);
			}
			if (value==null) value="";
			if (value instanceof String){
				map.put(key, enStr(value));
			}
			else{
				map.put(key, value);
			}
		}

		return map;
	}

	public static List<Object> toList(List<?> array) throws Exception
	{
		List<Object> list = new ArrayList<Object>();

		for (Object obj : array) {
			if(obj instanceof List){
				obj = toList((List<?>) obj);
			}
			else if(obj instanceof Map){
				obj = toMap((Map<?,?>) obj);
			}
			else{
				if (obj==null) obj = "";
				if (obj instanceof String){
					list.add(enStr(obj));
				}
				else{
					list.add(obj);
				}
			}
		}
		return list;
	}
    
	public static Model requestToModel(HttpServletRequest request, Model model) {
		Enumeration<String> enumeration = request.getParameterNames();
		while(enumeration.hasMoreElements()){
			String key = enumeration.nextElement();
			Map<String,Object> m1 = getStrToMap(request.getParameter(key));
			if (m1.size() < 1) {
				model.addAttribute(key,request.getParameter(key));
			}
			else {
				model.addAttribute(key,m1);
			}
		}
		
		return model;
	}
}
