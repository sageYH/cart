package com.common.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpUtils {
	private static final String ENC_TYPE = "UTF-8";
	// 기본 접속 제한 시간 (1/1000초 단위 --> 밀리세컨드)
	private static final int TIMEOUT = 5000;

	public static String mapToHttpStr(Map<String, String> parameters) {
		// HashMap을 HTTP 요청 파라미터로 변환하여 전송
		StringBuilder sbData = new StringBuilder();
		for (Map.Entry<String, String> param : parameters.entrySet()) {
			if (sbData.length() != 0) {
				sbData.append('&');
			}
			sbData.append(param.getKey());
			sbData.append('=');
			sbData.append(param.getValue());
		}
		return sbData.toString();
	}
	
	public static Map<String,Object> httpSend(String sUrl, String param, String method, Map<String,Object> header, String cookie) {
		//return : resultCode.Response Code, resultData.Response Contents
		//method : POST, GET
		//cookie : "key1=value1&key2=value2.."
		if (param == null) param = "";
		if (cookie == null) cookie = "";
		if (method == null) method = "GET";
		
		HashMap<String,Object> rMap = new HashMap<String,Object>();
		rMap.put("resultCode","");
		rMap.put("resultData","");
		if (sUrl == null) return rMap;
		try {
			// 요청 보낼 URL
			URL url = null;
			if (method.toLowerCase().equals("get")) {
				if (param.equals("")) {
					url = new URL(sUrl);
				}else {
					url = new URL(sUrl + "?" + param);
				}
			}else {
				url = new URL(sUrl);
			}
		   
			// HttpURLConnection 객체 생성하여 연결 설정
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(TIMEOUT); // 연결 타임아웃 설정
			con.setReadTimeout(TIMEOUT); // 읽기 타임아웃 설정
			con.setRequestMethod(method); // GET, POST 등의 요청 설정
			
			if (header != null && header.size() > 0) {
				for (Map.Entry<String, Object> m1 : header.entrySet()) {
					con.setRequestProperty(m1.getKey(),m1.getValue().toString());
				}
			}
			if (!cookie.equals("")) {
				con.setRequestProperty("Cookie",cookie);
			}
			
			if (method.toLowerCase().equals("post")) {
				con.setDoOutput(true);
				// 데이터 전송
				try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
					byte[] postDataBytes = param.getBytes(ENC_TYPE);
					wr.write(postDataBytes);
					wr.flush();
					wr.close();
				}
			}
			
			// 응답 코드 확인
			int status = con.getResponseCode();

			// 응답 받기
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder content = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			
			rMap.put("resultCode",status+"");
			rMap.put("resultData",content.toString());
			
		}catch(Exception e) {
			rMap.put("resultCode","Err01");
			rMap.put("resultData","Access violation.");
		}

		return rMap;
	}

	//보안연결
	public static Map<String,Object> httpsSend(String sUrl, String param, String method, Map<String,Object> header, String cookie) {
		//return : resultCode.Response Code, resultData.Response Contents
		//method : POST, GET
		if (param == null) param = "";
		if (cookie == null) cookie = "";
		if (method == null) method = "GET";
		
		HashMap<String,Object> rMap = new HashMap<String,Object>();
		rMap.put("resultCode","");
		rMap.put("resultData","");
		if (sUrl == null) return rMap;
		try {
			// 요청 보낼 URL
			URL url = null;
			if (method.toLowerCase().equals("get")) {
				if (param.equals("")) {
					url = new URL(sUrl);
				}else {
					url = new URL(sUrl + "?" + param);
				}
			}else {
				url = new URL(sUrl);
			}
		   
			// HttpsURLConnection 객체 생성하여 연결 설정
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setConnectTimeout(TIMEOUT); // 연결 타임아웃 설정
			con.setReadTimeout(TIMEOUT); // 읽기 타임아웃 설정
			con.setRequestMethod(method); // GET, POST 등의 요청 설정
			
			if (header != null && header.size() > 0) {
				for (Map.Entry<String, Object> m1 : header.entrySet()) {
					con.setRequestProperty(m1.getKey(),m1.getValue().toString());
				}
			}
			if (!cookie.equals("")) {
				con.setRequestProperty("Cookie",cookie);
			}
			
			if (method.toLowerCase().equals("post")) {
				con.setDoOutput(true);
				// 데이터 전송
				try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
					byte[] postDataBytes = param.getBytes(ENC_TYPE);
					wr.write(postDataBytes);
					wr.flush();
					wr.close();
				}
			}
			
			// 응답 코드 확인
			int status = con.getResponseCode();

			// 응답 받기
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder content = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			
			rMap.put("resultCode",status+"");
			rMap.put("resultData",content.toString());
			
		}catch(Exception e) {
			rMap.put("resultCode","Err01");
			rMap.put("resultData","Access violation.");
		}

		return rMap;
	}
	
	//ssl 연결
	public static Map<String,Object> httpSSLSend(String sUrl, String param, String method, Map<String,Object> header, String cookie) {
		//return : resultCode.Response Code, resultData.Response Contents
		//method : POST, GET
		if (param == null) param = "";
		if (cookie == null) cookie = "";
		if (method == null) method = "GET";
		
		HashMap<String,Object> rMap = new HashMap<String,Object>();
		rMap.put("resultCode","");
		rMap.put("resultData","");
		if (sUrl == null) return rMap;
		try {
			// SSL 인증서 검증 우회
			TrustManager[] trustAllCerts = new TrustManager[]{
				new X509TrustManager() {
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}
					public void checkClientTrusted(X509Certificate[] certs, String authType) {}
					public void checkServerTrusted(X509Certificate[] certs, String authType) {}
				}
			};
		   
			// SSLContext 생성 및 초기화
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
			// HTTPS 연결 설정
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

			// 요청 보낼 URL
			URL url = null;
			if (method.toLowerCase().equals("get")) {
				if (param.equals("")) {
					url = new URL(sUrl);
				}else {
					url = new URL(sUrl + "?" + param);
				}
			}else {
				url = new URL(sUrl);
			}

			// HttpsURLConnection 객체 생성하여 연결 설정
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setConnectTimeout(TIMEOUT); // 연결 타임아웃 설정
			con.setReadTimeout(TIMEOUT); // 읽기 타임아웃 설정
			con.setRequestMethod(method); // GET, POST 등의 요청 설정
			
			if (header != null && header.size() > 0) {
				for (Map.Entry<String, Object> m1 : header.entrySet()) {
					con.setRequestProperty(m1.getKey(),m1.getValue().toString());
				}
			}
			if (!cookie.equals("")) {
				con.setRequestProperty("Cookie",cookie);
			}
			
			if (method.toLowerCase().equals("post")) {
				con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				con.setDoOutput(true);
				// 데이터 전송
				con.getOutputStream().write(param.getBytes("UTF-8"));
			}
			
			// 응답 코드 확인
			int status = con.getResponseCode();

			// 응답 받기
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder content = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			
			rMap.put("resultCode",status+"");
			rMap.put("resultData",content.toString());
			
		}catch(Exception e) {
			rMap.put("resultCode","Err01");
			rMap.put("resultData","Access violation.");
		}

		return rMap;
	}
}
