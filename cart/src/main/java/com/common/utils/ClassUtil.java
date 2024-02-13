package com.common.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ClassUtil {

	public static <T> String[] getMethodsNm(T obj){
		String[] sArr = new String[0];
		Class<?> clazz = obj.getClass();
		while(clazz != null) {
			String stype = clazz.getName();
			if (stype.indexOf("java.")==0) break;
			Method[] ms = clazz.getDeclaredMethods();
			int iSArr = sArr.length;
			int iMs = ms.length;
			int cnt = iSArr + iMs;
			sArr = Arrays.copyOf(sArr, cnt);
			for(int i=iSArr;i<cnt;i++) {
				sArr[i] = ms[i-iSArr].getName();
			}
			clazz = clazz.getSuperclass();
		}
		return sArr;
	}
	public static boolean isMethodNm(String[] sArr,String nm) {
		if (sArr==null || nm==null) return false;
		if (Arrays.asList(sArr).indexOf(nm)>=0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public static <E, T> E getCopyObj(T srcObj, E tgtObj) {
		List<Field> srcList = getAllFields(srcObj);
		List<Field> tgtList = getAllFields(tgtObj);
		if(srcList.size() < 1 || tgtList.size() < 1) return tgtObj;

		List<String> sList = new ArrayList<String>();
		for(Field obj:tgtList) {
			sList.add(obj.getName());
		}
		try {
			String s1 = getDType(srcObj.getClass().getName());
			if (s1.indexOf("java.")>-1 && s1.indexOf("map")>-1) {
				for(int i=0;i<tgtList.size();i++) {
					Field f0 = tgtList.get(i);
					String nm = f0.getName();
					if(((Map)srcObj).get(nm)==null) continue;
					f0.set(tgtObj, ((Map)srcObj).get(nm));
				}
				return tgtObj;
			}
			
			String[] sArr = getMethodsNm(tgtObj);
			for(int i=0;i<srcList.size();i++) {
				Field f0 = srcList.get(i);
				String nm = f0.getName();
				if (nm.indexOf("serialVersionUID")>=0) continue;
				if (isMethodNm(sArr,nm) == false) continue; 
				if (sList.indexOf(nm) < 0) continue;
				Field f1 = setEnableField(tgtList,nm);
				if(f1==null) continue;
				if ((f1.getModifiers() & Modifier.FINAL) == Modifier.FINAL) continue;
				Object obj = f0.get(srcObj);
//				f1.set(tgtObj, obj);
				Object oRst = castObject(f1.getType(), obj);
				if (oRst == null) continue;
				f1.set(tgtObj, oRst);
			}
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return tgtObj;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> Map<String,Object> getClassToMap(T body) {
		Map<String,Object> map = new HashMap<String,Object>();
		if (body == null) return null;

		try {
			String s1 = getDType(body.getClass().getName());
			if (s1.indexOf("java.")>-1 && s1.indexOf("map")>-1) {
				return (Map<String,Object>)body;
			}
			List<Field> fieldList = getAllFields(body);
			if(fieldList.size() < 1 || fieldList.size() < 1) return null;

			String[] sArr = getMethodsNm(body);
			for(int i=0;i<fieldList.size();i++) {
				Field f0 = fieldList.get(i);
				String nm = f0.getName();
				if (nm.indexOf("serialVersionUID")>=0) continue;
				if (isMethodNm(sArr,nm) == false) continue; 
				
				if (f0.get(body)==null) {
					String s2 = getDType(f0.getType().toString());
					if (s2.indexOf("java.")>-1) {
						if (s2.indexOf("list")>-1) {
							map.put(nm, new ArrayList());
							continue;
						}
						if (s2.indexOf("map")>-1) {
							map.put(nm, new HashMap());
							continue;
						}
					}
					map.put(nm, "");
					continue;
				}
				
				String stype = f0.get(body).getClass().getName().toLowerCase();
				if (stype.indexOf("java.")==0) {
					if (stype.indexOf("list")>=0) {
						List<Object> lOrg = (ArrayList<Object>)f0.get(body);
						List<Object> lTgt = new ArrayList<Object>();
						for(int k=0;k<lOrg.size();k++) {
							Map<String,Object> map2 = getClassToMap((T)lOrg.get(k));
							lTgt.add(map2);
						}
						map.put(nm, lTgt);
					}else if(stype.indexOf("map")>=0){
						map.put(nm,getClassToMap(f0.get(body)));
					}
					else {
						map.put(nm,f0.get(body));
					}
				}else {
					map.put(nm,getClassToMap(f0.get(body)));
				}
			}
			
			return map;
		}catch(Exception e) {
			return null;
		}
	}
	public static <T> Object getMapToClass(Map<String,Object> map, T body) {
		if (body == null) return null;

		try {
			List<Field> fieldList = getAllFields(body);
			if(fieldList.size() < 1 || fieldList.size() < 1) return null;
			for( String strKey : map.keySet() ){
				Field f1 = setEnableField(fieldList,strKey);
				if(f1==null) continue;
				if ((f1.getModifiers() & Modifier.FINAL) == Modifier.FINAL) continue;
//				f1.set(body, map.get(strKey));
				Object oRst = castObject(f1.getType(), map.get(strKey));
				if (oRst == null) continue;
				f1.set(body, oRst);
			}

			return body;
		}catch(Exception e) {
			return null;
		}
	}
	public static Field setEnableField(List<Field> fields, String name) {
		for(Field f: fields) {
			if(f.getName().equals(name)==false) continue;
			return f;
		}
		return null;
	}
	
	public static <T> List<Field> getAllFields(T t){
		Class<?> clazz = t.getClass();
		List<Field> fields = new ArrayList<>();
		while(clazz != null) {
			fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
			clazz = clazz.getSuperclass();
		}
		for(int i=0;i<fields.size();i++) {
			fields.get(i).setAccessible(true);
		}
		return fields;
	}

	@SuppressWarnings("unchecked")
	public static <T> T castObject(T clazz, Object object) {
		if (object == null) return null;
		String sCnm = clazz.toString().replace("class ", "").replace("interface ", "");
		if (sCnm.indexOf("java.")>-1) {
			String s1 = sCnm.toLowerCase();
			if (s1.indexOf("list")>-1 && s1.indexOf("map")>-1) {
				return (T)object;
			}
		}

		try {
			if(sCnm.equals("short")) {
				sCnm = "java.lang.Short";
			}else if(sCnm.equals("int")) {
				sCnm = "java.lang.Integer";
			}else if(sCnm.equals("long")) {
				sCnm = "java.lang.Long";
			}else if(sCnm.equals("float")) {
				sCnm = "java.lang.Float";
			}else if(sCnm.equals("double")) {
				sCnm = "java.lang.Double";
			}else if(sCnm.equals("char")||sCnm.equals("java.lang.Char")) {
				String s1 = object+"";
				Object o1 = s1.charAt(0);
				return (T)o1;
			}else if(sCnm.equals("boolean")) {
				sCnm = "java.lang.Boolean";
			}
			
			Class<?> cls = Class.forName(sCnm);
			Constructor<?> con = cls.getConstructor(String.class);
			Object ob = con.newInstance(object+"");
			return (T)ob;
		}catch(Exception e) {
			return null;
		}
	}

	public static <M> List<Map<String,Object>> getMapList(List<M> list){
		if (list == null) return null;
		List<Map<String,Object>> rLst = new ArrayList<Map<String,Object>>();

		try {
			int iCnt = list.size();
			for(int i=0;i<iCnt; i++){
				Object tO1 = list.get(i);
				HashMap<String,Object> o2 = (HashMap<String,Object>)getClassToMap(tO1);
				rLst.add(o2);
			}
			return rLst;
		}catch(Exception e) {
			return null;
		}
	}
	public static <T,M> List<?> castList(T obj, List<M> list){
		if (list == null) return null;
		List<Object> rLst = new ArrayList<Object>();

		try {
			int iCnt = list.size();
			for(int i=0;i<iCnt; i++){
				Object tO1 = list.get(i);
				Class<?> o2 = (Class<?>)getCopyObj(tO1,obj);
				rLst.add(o2);
			}
			return rLst;
		}catch(Exception e) {
			return null;
		}
	}
	public static <T> List<?> castMapList(T obj,List<Map<String,Object>> list){
		if (obj == null || list == null) return null;
		List<Object> lMap = new ArrayList<Object>();
		int cnt = list.size();
		try {
			for(int i=0;i<cnt;i++) {
				Object o2 = getMapToClass(list.get(i), obj.getClass().newInstance());
				lMap.add(o2);
			}
			return lMap;
		}catch(Exception e) {
			return null;
		}
	}
	
	public static <V> void add(V value, List<? super V> list) {
		list.add(value);
	}
	
	public static String getDType(String s1) {
		if (s1==null) return "";
		return s1.replace("class ", "").replace("interface ", "").toLowerCase();
	}

	//json
	public static Map<String,Object> getStrJsonToMap(String data) throws Exception{
		JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject)parser.parse(data);

		return (Map<String,Object>)getJsonObjToMap(jObj);
	}
	public static Map<String,Object> getJsonObjToMap(JSONObject json) throws Exception{
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != null)
        {
            retMap = chgJObjToMap(json);
        }
        return retMap;
    }
    public static Map<String,Object> chgJObjToMap(JSONObject object) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();

		Iterator<?> iterator = object.entrySet().iterator();
		while (iterator.hasNext()){
			Entry<?,?> entry = (Entry<?,?>)iterator.next();
			String key = (String)entry.getKey();
            Object value = object.get(key);
            if(value instanceof JSONArray){
                value = chgJArrayToList((JSONArray) value);
            }
            else if(value instanceof JSONObject){
                value = chgJObjToMap((JSONObject) value);
            }
            if (value==null) value="";
            map.put(key, value);
		}

        return map;
    }
    public static List<Object> chgJArrayToList(JSONArray array) throws Exception{
        List<Object> list = new ArrayList<Object>();

		for (Object obj : array) {
			//no list for structure
            if(obj instanceof JSONArray){
                obj = chgJArrayToList((JSONArray) obj);
            }
            else if(obj instanceof JSONObject){
                obj = chgJObjToMap((JSONObject) obj);
            }
			else{
				if (obj==null) obj = "";
			}
			list.add(obj);
		}
        return list;
    }

	public static <T> T getStrJsonToClass(String data, T cls) throws Exception{
		JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject)parser.parse(data);

		return (T)getJsonObjToClass(jObj, cls);
	}
    
	public static <T> T getJsonObjToClass(JSONObject json, T cls) throws Exception{
        if(json != null) return cls;
        cls = chgJObjToClass(json, cls);
 
        return cls;
    }
    
    @SuppressWarnings("rawtypes")
	public static <T> T chgJObjToClass(JSONObject object, T cls) throws Exception{

        List<Field> fieldList = getAllFields(cls);
        if(fieldList.size() < 1 || fieldList.size() < 1) return null;

		Iterator<?> iterator = object.entrySet().iterator();
		while (iterator.hasNext()){
			Entry<?,?> entry = (Entry<?,?>)iterator.next();
			String key = (String)entry.getKey();

            Field f1 = setEnableField(fieldList,key);
            if(f1==null) continue;
            if ((f1.getModifiers() & Modifier.FINAL) == Modifier.FINAL) continue;

            Object value = object.get(key);
            if (value == null){
                String sT2 = getDType(f1.getType().toString());
                if (sT2.indexOf("java.")>-1){
                    if (sT2.indexOf("list")>-1){
                        f1.set(cls, new ArrayList());
                        continue;
                    }
                    if (sT2.indexOf("map")>-1){
                        f1.set(cls, new HashMap());
                        continue;
                    }
                }
                f1.set(cls, "");
                continue;
            }

            if(value instanceof JSONArray){
                ParameterizedType pType = (ParameterizedType)f1.getGenericType();
                Class<?> subCls = pType.getActualTypeArguments()[0].getClass();
                value = chgJArrayToClsList((JSONArray) value, subCls.newInstance());
            }
            else if(value instanceof JSONObject){
                ParameterizedType pType = (ParameterizedType)f1.getGenericType();
                Class<?> subCls = pType.getActualTypeArguments()[0].getClass();
                value = chgJObjToClass((JSONObject) value, subCls.newInstance());
            }
            Object oRst = castObject(f1.getType(), value);
            if (oRst == null) continue;
            f1.set(cls, oRst);
		}

        return cls;
    }
    
    public static <T> List<Object> chgJArrayToClsList(JSONArray array, T cls) throws Exception{
        List<Object> list = new ArrayList<Object>();

		for (Object obj : array) {
			//no list for structure
            if(obj instanceof JSONArray){
                obj = chgJArrayToClsList((JSONArray) obj, cls);
            }
            else if(obj instanceof JSONObject){
                obj = chgJObjToClass((JSONObject) obj, cls);
            }
			else{
				if (obj==null) obj = "";
			}
			list.add(obj);
		}
        return list;
    }
    
	public static String getMapToString(Map<String,Object> map) {
    	if (map == null) return "";
		try {
			Map<String,Object> map2 = getClassToMap(map);
			return org.json.simple.JSONObject.toJSONString(map2);
		}catch(Exception e) {
			return null;
		}
    }
	public static String getClsToString(Object obj) {
    	if (obj == null) return "";
		try {
			Map<String,Object> map2 = getClassToMap(obj);
			return org.json.simple.JSONObject.toJSONString(map2);
		}catch(Exception e) {
			return null;
		}
    }
	//json end
}
