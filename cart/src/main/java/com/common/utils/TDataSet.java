package com.common.utils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.Base64.Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class TDataSet extends HashMap<String,Object> {
	private static final long serialVersionUID = 2081210012703200104L;

	private transient Map<String, Object> model = new HashMap<String, Object>();

	public String mode = "";	//L:list
	public String errorCode = "";
	public String errorMsg = "";
	public String errorDoc = "";

	public int totRows = 0;		//query total count.
	public int rows = 0;			//result count.
	public int totPages = 0;
	public int pages = 0;
	public int currentIndex = 0;
	public int pageCnt = 0;

	public int ingCnt = 0;
	public int complateCnt = 0;
	public int cancelCnt = 0;
	
	public TDataSet() {
		super(0);	//0 initial capacity of ten elements
		this.setMessage("", "");
	}
	
	public int getColCount(){
		if (this.isEmpty()) return 0;
		return this.size();
	}

	public static boolean isNull(Object val) 
	{
		boolean result = true;
		
		if (val != null)
		{
			if (val instanceof String)
				if (String.valueOf(val).trim().length() > 0)
					return false;
				else
					return true;
			else
				return false;
		}
		
		return result;
	}
	
	public static String getRandomKey(int size) 
	{
		Random r = new Random(new Date().getTime());
		int i = r.nextInt();
		Encoder ecd = Base64.getEncoder();
		String encodeStr = ecd.encodeToString(String.valueOf(i).getBytes());
		
		return encodeStr.toUpperCase().substring(0, size);
	}
	
	public boolean isNumber(String val){
		boolean b1=false;
		try{
			@SuppressWarnings("unused")
			double d1=Double.parseDouble(val);
			b1=true;
		}
		catch(Exception ex){}
		return b1;
	}
	public int getFieldCount(){
		if (this.isEmpty()) return 0;
		String s1 = this.getString("COLCOUNT");
		if (this.isNumber(s1)==false) s1 = "0";
		return Integer.parseInt(s1);
	}

	@SuppressWarnings("unchecked")
	public Vector<Object> getVector(Object obj){
		Vector<Object> vector = null;
		if (obj == null || !(obj instanceof Vector)) return null;
		vector = (Vector<Object>)obj;
		return vector;
	}
	
	public int getRowCount(){
		if (this.isEmpty()) return 0;

		Iterator<?> iterator = this.entrySet().iterator();
		Entry<?,?> entry = (Entry<?,?>)iterator.next();
		String name = (String)entry.getKey();
		if (name == null) return 0;

		Vector<Object> vector = getVector(super.get(name));
		if(vector == null) return 0;

		return vector.size();
	}

	public int getRowCount(String name){
		if (this.isEmpty()) return 0;

		Vector<Object> vector = getVector(super.get(name));
		if(vector == null) return 0;

		return vector.size();
	}


	public int getFindRow(String name,Object val){
		if (this.isEmpty()) return -1;
		
		Vector<Object> vector = getVector(super.get(name));
		if(vector == null) return -1;
		
		return vector.indexOf(val);
	}

	public int getMaxRowCount(){
		if (this.isEmpty()) return 0;

		int imax = 0;
		Iterator<?> iterator = this.entrySet().iterator();
		while (iterator.hasNext()){
			try{
				Entry<?,?> entry = (Entry<?,?>)iterator.next();
				String key = (String)entry.getKey();
				Vector<Object> vector = getVector(super.get(key));
				if(vector != null){
					if (imax < vector.size()) imax = vector.size();
				}
			} catch(Exception e1){}
		}
		
		return imax;
	}

	public TDataSet getRowValues(int i){
		TDataSet ds = new TDataSet();
		if (this.isEmpty()) return ds;
		Iterator<?> iterator = this.entrySet().iterator();
		while (iterator.hasNext()){
			try{
				Entry<?,?> entry = (Entry<?,?>)iterator.next();
				String key = (String)entry.getKey();
				ds.setValue(key, 0, this.getString(key,i));		//i row -> ds first row copy
			} catch(Exception e1){}
		}
		
		return ds;
	}

	public TDataSet getRowArray(String[] keys,int i){
		TDataSet ds = new TDataSet();
		if (this.isEmpty()) return ds;
		for(int k=0;k<keys.length;k++){
			try{
				ds.setValue(keys[k], 0, this.getString(keys[k],i));		//i row -> ds first row copy
			} catch(Exception e){}
		}
		
		return ds;
	}

	public int insertRow(){
		//마지막 row만 가능 1 row이상.
		int row = -1;
		try{
			row = this.getMaxRowCount();
			if (row<1) return row;
			Iterator<?> iterator = this.entrySet().iterator();
			while (iterator.hasNext()){
				try{
					Entry<?,?> entry = (Entry<?,?>)iterator.next();
					String key = (String)entry.getKey();
					this.setValue(key, row, "");
				} catch(Exception e1){}
			}
		}
		catch(Exception e){}
		return row;
	}

	public void copyRowValues(int r,TDataSet ds, int i){
		//r:this copy row ds:대상 i:대상 row  ds -> this
		if (this.isEmpty()) return;
		
		try{
			Iterator<?> iterator = this.entrySet().iterator();
			while (iterator.hasNext()){
				try{
					Entry<?,?> entry = (Entry<?,?>)iterator.next();
					String key = (String)entry.getKey();
					ds.setValue(key, r, this.getString(key,i));
				} catch(Exception e1){}
			}
		}
		catch(Exception e){}
	}

	public Object getValue(String name, int index, Object initVal){
		Vector<Object> vector = getVector(super.get(name));
		if(vector == null) return initVal;

		try{
			if (vector.elementAt(index) instanceof TDataSet) {
//				System.out.println("dataset........");
			}
			return vector.elementAt(index);
		} catch(ArrayIndexOutOfBoundsException _ex){
			return initVal;
		}
	}
	public Object getValue(String name, Object initVal){
		return getValue(name, 0, initVal);
	}
	public Object getValue(String name){
		return getValue(name, 0, null);
	}

	public Boolean getBoolean(String name, int index, Object initVal){
		Object obj = getValue(name, index, initVal);
		if(obj == null) return false;

		try{
			return Boolean.valueOf((String)obj);
		}catch(Exception e){return false;}
	}
	public Boolean getBoolean(String name, Object initVal){
		return getBoolean(name, 0, initVal);
	}
	public Boolean getBoolean(String name, int index){
		return getBoolean(name, index, false);
	}
	public Boolean getBoolean(String name){
		return getBoolean(name, 0, false);
	}


	public String getString(String name, int index, Object initVal){
		Object obj = getValue(name, index, initVal);
		if(obj == null) return (String)initVal;

		try{
			return String.valueOf(obj);
		}catch(Exception e){return "";}
	}
	public String getString(String name, Object initVal){
		return getString(name, 0, initVal);
	}
	public String getString(String name, int index){
		return getString(name, index, "");
	}
	public String getString(String name){
		return getString(name, 0, "");
	}

	public int getInteger(String name , int index , int initVal){
		Object obj = getValue(name, index, null);
		if (obj == null) return initVal;

		try{
			return Integer.parseInt(obj.toString());
		}catch(Exception e){return initVal;}
	}
	public int getInteger(String name, int index){
		return getInteger(name, index, 0);
	}
	public int getInteger(String name){
		return getInteger(name, 0, 0);
	}

	public double getDouble(String name, int index, double initVal){
		Object obj = getValue(name, index, null);
		if (obj == null) return initVal;

		try{
			return Double.parseDouble(obj.toString());
		}catch(Exception e){return initVal;}
	}
	public double getDouble(String name, int index){
		return getDouble(name, index, 0);
	}
	public double getDouble(String name){
		return getDouble(name, 0, 0);
	}

	public void setValue(String name, int index, Object value){
		Vector<Object> vector = getVector(get(name));
		if(vector == null){
			vector = new Vector<Object>();
			put(name, vector);
		}

		if(index >= vector.size()) vector.setSize(index + 1);
		vector.setElementAt(value, index);
	}

	public void setValue(String name, Object value){
		setValue(name, 0, value);
	}
	
	public void deleteRow(int r){
		
		try{
			Iterator<?> iterator = this.entrySet().iterator();
			while (iterator.hasNext()){
				try{
					Entry<?,?> entry = (Entry<?,?>)iterator.next();
					String key = (String)entry.getKey();
					if (this.getRowCount(key)<=r) continue;
					Vector<Object> vector = getVector(super.get(key));
					if(vector == null) continue;
					vector.removeElementAt(r);
					
				} catch(Exception e1){}
			}
		}
		catch(Exception e){}
	}
	
	public void removeField(String str){
		try{
			if (this.containsKey(str)){
				this.remove(str);
			}
		}
		catch(Exception e){}
	}

	//Map copy
	public void setMapCopy(Map<String,?> map){
		try{
			super.putAll(map);
		}
		catch(Exception e){}
	}
	//Map to dataset
	@SuppressWarnings("unchecked")
	public void setMapAll(Map<?,?> map){
		mode = "";
		try{
			if (map==null) return;
			TDataSet ds = toMapDS(map);
			super.putAll((Map<String,?>)ds.clone());
		}
		catch(Exception e){}
	}
    public TDataSet toMapDS(Map<?,?> object) throws Exception
    {
		TDataSet ds = new TDataSet();

		Iterator<?> iterator = object.entrySet().iterator();
		while (iterator.hasNext()){
			Entry<?,?> entry = (Entry<?,?>)iterator.next();
			String key = (String)entry.getKey();
            Object value = object.get(key);
            if(value instanceof List){
            	if (((List<?>) value).get(0) instanceof String){
            		int row = 0;
            		for (Object obj : (List<?>) value) {
                        ds.setValue(key,row,obj);
                        row++;
            		}
           			continue;
            	}
            	else{
            		value = toListDS((List<?>) value,key);
            	}
            }
            else if(value instanceof Map){
                value = toMapDS((Map<?,?>) value);
            }
            if (value==null) value="";
            ds.setValue(key,0,value);
		}

        return ds;
    }
    public TDataSet toListDS(List<?> array,String key) throws Exception
    {
    	TDataSet ds = new TDataSet();

    	int row = 0;
		for (Object obj : array) {
			//no list for structure
			if(obj instanceof List){
				obj = toListDS((List<?>)obj,key);
			}
			else if(obj instanceof Map){
                obj = toMapDS((Map<?,?>) obj);
            }
			else{
				if (obj==null) obj = "";
				ds.setValue(key, row, obj);
				row++;
			}
		}
        return ds;
    }
	//Map to dataset end.
	//DATASET object to map
	public Map<String,Object> datasetToMap(TDataSet ds) throws Exception
    {
		//***first Map : never list. first only map
        Map<String, Object> retMap = new HashMap<String, Object>();

        retMap = dsToMap(ds);
        getPageInfo(retMap);

        return retMap;
    }
	public Map<String,Object> dsToMap(TDataSet ds) throws Exception
	{
        Map<String, Object> map = new HashMap<String,Object>();
		map.put("errorCode",ds.getString("errorCode")==null?"":ds.getString("errorCode"));
		map.put("errorMsg",ds.getString("errorMsg")==null?"":ds.getString("errorMsg"));
		ds.clearBaseKey();

		Map<String, String> map1 = ds.getFields();
		Set<String> key = map1.keySet();
		for (Iterator<String> iterator = key.iterator(); iterator.hasNext();) {
			String keyNm = (String)iterator.next();
			
			Object o1 = ds.getValue(keyNm);
			if (o1 instanceof TDataSet){
				int cnt = ((TDataSet)o1).getMaxRowCount();
				if (cnt == 1){
					if (((TDataSet)o1).mode.equals("L")){
						o1 = dsToList((TDataSet)o1);
					}
					else{
						o1 = dsToMap((TDataSet)o1);
					}
				}
				else if(cnt > 1){
					o1 = dsToList((TDataSet)o1);
				}
			}
			else{
				if (o1==null) o1 = "";
			}
			map.put(keyNm,o1);
		}
		return map;
	}
	
    public List<Object> dsToList(TDataSet ds) throws Exception
    {
        List<Object> list = new ArrayList<Object>();
		ds.clearBaseKey();

		Map<String, String> map1 = ds.getFields();
		Set<String> key = map1.keySet();

		int cnt = ds.getMaxRowCount();
		if (cnt < 1) return list;
		
		for(int i=0;i<cnt;i++){
			Map<String,Object> map2 = new HashMap<String,Object>();
			for (Iterator<String> iterator = key.iterator(); iterator.hasNext();) {
				String keyNm = (String)iterator.next();
				
				Object o1 = ds.getValue(keyNm,i,null);
				if (o1 instanceof TDataSet){
					if (((TDataSet)o1).mode.equals("L")){
						o1 = dsToList((TDataSet)o1);
					}
					else{
						o1 = dsToMap((TDataSet)o1);
					}
				}
				else{
					if (o1==null) o1 = "";
				}
				map2.put(keyNm,o1);
			}
			list.add(map2);
		}

        return list;
    }
	//DATASET object to map end.

	//set List
	public void setListMap(List<?> ls){
		mode = "L";
		try{
			int row = 0;
			for (Object obj : ls) {
				if (obj instanceof Map){
					this.setMap((Map<?,?>)obj,row);
					row++;
				}
			}
		}
		catch(Exception e){}
	}
	//map
	public void setMap(Map<?,?> map,int row){
		try{
			Iterator<?> iterator = map.entrySet().iterator();
			while (iterator.hasNext()){
				Entry<?,?> entry = (Entry<?,?>)iterator.next();
				String key = (String)entry.getKey();
				if (map.get(key)==null){
					this.setValue(key, row, "");
				}
				else{
					this.setValue(key, row, deStr(map.get(key)));
//					this.setValue(key, row, map.get(key));
				}
			}
		}
		catch(Exception e){}
	}
	
	public HashMap<String,Object> getMap(){
		return getMap(0);
	}

	public HashMap<String,Object> getMap(int row){
		HashMap<String,Object> map = new HashMap<String,Object>();

		try{
			Iterator<?> iterator = this.entrySet().iterator();
			while (iterator.hasNext()){
				Entry<?,?> entry = (Entry<?,?>)iterator.next();
				String key = (String)entry.getKey();
				map.put(key, this.getValue(key,row,null));
			}
		}
		catch(Exception e){}
		return map;
	}
	//set List end.

	public List<HashMap<String,Object>> getList(){
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

		try{
			int max = this.getMaxRowCount();

			Set<String> key = this.keySet();
			for(int i=0;i<max;i++){
				HashMap<String,Object> map = new HashMap<String,Object>();
				for (Iterator<String> iterator = key.iterator(); iterator.hasNext();) {
					String keyNm = (String)iterator.next();
					Object obj = this.getValue(keyNm,i,null);
					if (obj==null){
						map.put((String)keyNm, "");
					}
					else{
						map.put((String)keyNm, obj);
					}
				}
				list.add(map);
			}
		}
		catch(Exception e){}
		return list;
	}

	public void setMessage(String code, String msg){
		setMessage(code,msg,0);
	}

	public void setMessage(String code, String msg, int index){
		if (msg.indexOf("java.sql.SQLException") > -1){
			msg = "자료작업 중 오류가 발생했습니다.(SQLException)..setMessage";
		} else {
			if (msg.indexOf("SQL") > -1){
				msg = "자료작업 중 오류가 발생했습니다.(SQL)..setMessage";
			}
		}
		this.errorCode = code;
		this.errorMsg = msg;
		setValue("errorCode",index,code);
		setValue("errorMsg",index,msg);
	}

	public Vector<Object> getValues(String name){
		return getVector(get(name));
	}

	public HashMap<String,String> getFields(){
		HashMap<String,String> Fields = new HashMap<String,String>();
		int k=0;
		Set<String> key = this.keySet();
		for (Iterator<String> iterator = key.iterator(); iterator.hasNext();) {
			String keyNm = (String)iterator.next();
			Fields.put(keyNm, k+"");
			k++;
		}

		return Fields;
	}

	public final Map<String, Object> getModel() {
		model.put("_DS", this);
		return this.model;
	}
	//string -> JSON -> map
	public Map<String,Object> getStrJsonToMap(String data) throws Exception{
		JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject)parser.parse(data);

		return (Map<String,Object>)jsonToMap(jObj);
	}
	//JSON object to map
	public Map<String,Object> jsonToMap(JSONObject json) throws Exception
    {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != null)
        {
            retMap = toMap(json);
            getPageInfo(retMap);
        }
        return retMap;
    }
	public Object enStr(Object obj){
		if (obj instanceof String){
			try{
				String s1 = obj+"";
//				s1 = (new String(s1.getBytes("EUC-KR"),"latin1"));
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
//				s1 = (new String(s1.getBytes("latin1"),"EUC-KR"));
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
    public Map<String,Object> toMap(JSONObject object) throws Exception
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
                    value = toList((JSONArray) value);
            	}
            }
            else if(value instanceof JSONObject){
                value = toMap((JSONObject) value);
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
    public List<Object> toList(JSONArray array) throws Exception
    {
        List<Object> list = new ArrayList<Object>();

		for (Object obj : array) {
			//no list for structure
            if(obj instanceof JSONArray){
                obj = toList((JSONArray) obj);
            }
            else if(obj instanceof JSONObject){
                obj = toMap((JSONObject) obj);
            }
			else{
				if (obj==null) obj = "";
			}
			list.add(obj);
		}
        return list;
    }
	//JSON object to map end.
    //string -> JSON -> dataset
    public TDataSet getStrJsonToDS(String data) throws Exception{
		JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject)parser.parse(data);
		return jsonToDataSet(jObj);
    }
	//JSON object to dataset
	public TDataSet jsonToDataSet(JSONObject json) throws Exception
    {
		TDataSet ds = new TDataSet();

        if(json != null)
        {
            ds = toJsonMapDS(json);
            getPageInfo(ds);
        }
        return ds;
    }
    public TDataSet toJsonMapDS(JSONObject object) throws Exception
    {
		TDataSet ds = new TDataSet();

		Iterator<?> iterator = object.entrySet().iterator();
		while (iterator.hasNext()){
			Entry<?,?> entry = (Entry<?,?>)iterator.next();
			String key = (String)entry.getKey();
            Object value = object.get(key);
            if(value instanceof JSONArray){
            	Object o1 = ((JSONArray) value).get(0);
            	if (o1 instanceof String || o1 instanceof Integer || o1 instanceof Long || o1 instanceof Double || o1 instanceof Float){
            		int row = 0;
            		for (Object obj : (JSONArray) value) {
            			ds.setValue(key, row, obj);
            			row++;
            		}
           			continue;
            	}
            	else{
            		value = toJsonListDS((JSONArray) value,key);
            	}
            }
            else if(value instanceof JSONObject){
                value = toJsonMapDS((JSONObject) value);
            }
            if (value==null) value="";
			if (value instanceof String){
	            ds.setValue(key,0,enStr(value));
			}
			else{
	            ds.setValue(key,0,value);
			}
		}

        return ds;
    }
    public TDataSet toJsonListDS(JSONArray array,String key) throws Exception
    {
    	TDataSet ds = new TDataSet();

    	int row = 0;
		for (Object obj : array) {
			//no list for structure
            if(obj instanceof JSONArray){
                obj = toJsonListDS((JSONArray) obj,key);
            }
            else if(obj instanceof JSONObject){
                obj = toJsonMapDS((JSONObject) obj);
            }
			else{
				if (obj==null) obj = "";
				if (obj instanceof String){
					ds.setValue(key, row, enStr(obj));
				}
				else{
					ds.setValue(key, row, obj);
				}
				row++;
			}
		}
        return ds;
    }
    public void clearBaseKey(){
    	String[] keys = {"errorCode","errorMsg"};
    	for(int i=0;i<keys.length;i++){
    		if (this.containsKey(keys[i])){
    			this.remove(keys[i]);
    		}
    	}
    }
    
	//JSON object to dataset end.
    public static HttpSession getSession(){
    	try {
    		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    		HttpSession session = request.getSession(true);
    		return session;
		} catch (NullPointerException e) {
			return null;
		}
    }
    public static String getUserId(){
    	if (getSession() == null || getSession().getAttribute("USER_ID") == null) return "";
		String userId = (String)getSession().getAttribute("USER_ID");
		return userId;
    }
    public static String getCompId(){
    	if (getSession() == null || getSession().getAttribute("COMP_ID") == null) return "";
		String compId = (String)getSession().getAttribute("COMP_ID");
		return compId;
    }
    public static int getGrade(){
    	if (getSession() == null || getSession().getAttribute("GRADE") == null) return 0;
		String grade = (String)getSession().getAttribute("GRADE");
		try
		{
			return Integer.parseInt(grade);
		}
		catch ( NumberFormatException e )
		{
			return 0;
		}
    }
    public static String getCompNm() {
    	if (getSession() == null || getSession().getAttribute("COMP_NM") == null) return "";
		String compNm = (String)getSession().getAttribute("COMP_NM");
		return compNm;
    }
    public static String getBizRegNum() {
    	if (getSession() == null || getSession().getAttribute("BIZ_REG_NUM") == null) return "";
		String bizRegNum = (String)getSession().getAttribute("BIZ_REG_NUM");
		return bizRegNum;
    }
    //page Set
    public void getPageInfo(Map<String,Object> map){
		if (map.get("pageSize")==null) return;
		int pageSize = Integer.parseInt(map.get("pageSize").toString());
		if (map.get("currentIndex")==null) return;
		currentIndex = Integer.parseInt(map.get("currentIndex").toString());

		int startPage = ((currentIndex-1)*pageSize) + 1;
    	int endPage = (currentIndex*pageSize);
    	map.put("currentIndex", currentIndex);
    	map.put("startPage", startPage);
    	map.put("endPage", endPage);
    	map.put("pageSize", pageSize);
    }
    public void getPageInfo(TDataSet ds){
		if (ds.getString("pageSize")=="") return;
		int pageSize = ds.getInteger("pageSize");
    	if (ds.getString("currentIndex")==null) return;
    	currentIndex = ds.getInteger("currentIndex");

		int startPage = ((currentIndex-1)*pageSize) + 1;
    	int endPage = (currentIndex*pageSize);
    	ds.setValue("currentIndex", currentIndex);
    	ds.setValue("startPage",0, startPage);
    	ds.setValue("endPage",0, endPage);
    	ds.setValue("pageSize",0, pageSize);
    }
    public void setTotalRows(int totRows){
    	this.setValue("totalRows", 0, totRows);
    }
    public void setIngCnt(int intCnt){
    	this.setValue("ingCnt", 0, intCnt);
    }
    public void setComplateCnt(int complateCnt){
    	this.setValue("complateCnt", 0, complateCnt);
    }
    public void setCancelCnt(int cancelCnt){
    	this.setValue("cancelCnt", 0, cancelCnt);
    }
    
    // 사업자번호 검증을 위한  함수추가 (2020.11.14 BY_SYPARK) 
    public static boolean isValidBizNum(String regNum) {
    	
        if (!isNumeric(regNum) || regNum.length() != 10)
            return false;
        
        int[] LOGIC_NUM = {1, 3, 7, 1, 3, 7, 1, 3, 5, 1};
        int sum = 0;
        int j = -1;
        for (int i = 0; i < 9; i++) {
            j = Character.getNumericValue(regNum.charAt(i));
            sum += j * LOGIC_NUM[i];
        }

        sum += (int) (Character.getNumericValue(regNum.charAt(8)) * 5 /10);

        int checkNum = (10 - sum % 10) % 10 ;
        return (checkNum == Character.getNumericValue(regNum.charAt(9)));
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
    
    //page Set end.
}
