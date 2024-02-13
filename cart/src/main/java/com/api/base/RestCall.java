package com.api.base;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.common.utils.MapUtil;

@Component
public class RestCall {
	
	private static final Logger logger = LoggerFactory.getLogger(RestCall.class);
	private static final TokenUtil tokenUtil = new TokenUtil();
	private HttpHeaders httpHeaders;
	private UriComponentsBuilder uriComponentsBuilder;
	private Map<String, Object> userMap;
	private long executionTimes = -1;
	private boolean isTokenAuth = false;
	public static RestCall client(String url){
		return client(url, true);
	}
	public static RestCall client(String url, boolean isTokenAuth){
		RestCall restCall = new RestCall() {};
		restCall.uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(url);
		restCall.isTokenAuth = isTokenAuth;
		restCall.httpHeaders = new HttpHeaders();
		restCall.httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		restCall.httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return restCall;
	}
	
	public Map<String, Object> getUserMap(){
		return this.userMap;
	}
	public void setUserMap(Map<String, Object> userMap) {
		this.userMap = userMap;
	}
	
	public HttpHeaders getHttpHeaders() {
		return this.httpHeaders;
	}
	public void sethttpHeaders(HttpHeaders httpHeaders) {
		this.httpHeaders = httpHeaders;
	}
	
	public RestCall addHeader(String key, String value){
		if(Objects.isNull(httpHeaders)){
			httpHeaders = new HttpHeaders();
		}
		httpHeaders.add(key, value);
		return this;
	}
	@SuppressWarnings("unused")
	public RestCall setHeader(String key, String value){
		if (Objects.isNull(httpHeaders)){
			httpHeaders = new HttpHeaders();
		}
		httpHeaders.set(key, value);
		return this;
	}
	public RestCall setBasicAuth(String encodedCredentials){
		httpHeaders.setBasicAuth(encodedCredentials);
		return this;
	}
	public RestCall queryParam(String name, Object... values){
		this.uriComponentsBuilder.queryParam(name, values);
		return this;
	}
	public RestCall uriVariable(String name, Object value){
		if(Objects.isNull(userMap)){
			this.userMap = new HashMap<>();
		}
		this.userMap.put(name, value);
		return this;
	}
	public long getLatencyTimes(){
		return executionTimes;
	}
	public <T> ResultApiData<T> get(Class<T> type){
		return execute(null, HttpMethod.GET, type);
	}
	public <T> ResultApiData<T> get(Type type){
		return execute(null, HttpMethod.GET, type);
	}
	public <T> ResultApiData<T> get(ParameterizedTypeReference<T> responseReference){
		return execute(null, HttpMethod.GET, responseReference.getType());
	}
	public <T> ResultApiData<T> post(Object request, Class<T> type){
		return execute(request, HttpMethod.POST, type);
	}
	public <T> ResultApiData<T> post(Object request, Type type){
		return execute(request, HttpMethod.POST, type);
	}
	public <T> ResultApiData<T> post(Object request, ParameterizedTypeReference<T> responseReference){
		return execute(request, HttpMethod.POST, responseReference.getType());
	}
	private RestTemplate restTemplate(){
		return Instance.get();
	}
	private void configRequestHeader(HttpHeaders httpHeaders){
		configAuthorization(httpHeaders);
		Instance.configRequestHeader(httpHeaders);
	}
	private void configAuthorization(HttpHeaders httpHeaders){
		if(this.isTokenAuth){
			httpHeaders.setBearerAuth(tokenUtil.generateToken());
		}
	}
	private <T> ResultApiData<T> execute(Object request, HttpMethod method, Type type){
		if(Objects.nonNull(userMap)){
			this.uriComponentsBuilder.uriVariables(userMap);
//			this.uriComponentsBuilder.buildAndExpand(userMap);
		}
		URI uri = this.uriComponentsBuilder.build().toUri();
		configRequestHeader(httpHeaders);
		RequestEntity<?> requestEntity = new RequestEntity<>(request, httpHeaders, method, uri);
		RestCallback restCallback = restRequestCallback(restTemplate().httpEntityCallback(requestEntity, type));
		RestCallExtractor<T> restCallExtractor = restResponseExtractor(restTemplate().responseEntityExtractor(type));
		ResponseEntity<T> responseEntity = null;
		Exception exception = null;
		long start = System.currentTimeMillis();
		try{
			responseEntity = restTemplate().execute(requestEntity.getUrl(), method, restCallback, restCallExtractor);
			return new ResultApiData<>(responseEntity);
		} catch(RestClientResponseException e){
			exception = e;
			return new ResultApiData<>(e);
		} catch(Exception e){
			exception = e;
			return new ResultApiData<>(e);
		} finally{
			this.executionTimes = System.currentTimeMillis() - start;
			logger(requestEntity, restCallback, responseEntity, restCallExtractor, exception);
		}
	}
	private RestCallback restRequestCallback(RequestCallback callback){
		return new RestCallback(callback);
	}
	private <T> RestCallExtractor<T> restResponseExtractor(ResponseExtractor<ResponseEntity<T>> extractor){
		return new RestCallExtractor<>(extractor);
	}
	private <T> void logger(RequestEntity<?> requestEntiry, RestCallback restCallback,
			ResponseEntity<T> responseEntity, RestCallExtractor<T> restCallExtractor, Exception e){
		if(Objects.isNull(e)){
			logger(requestEntiry, restCallback, responseEntity, restCallExtractor);
		} else if(e instanceof RestClientResponseException){
			RestClientResponseException re = (RestClientResponseException) e;
			logger(requestEntiry.getUrl(), requestEntiry.getMethod(), requestEntiry.getHeaders(),
				restCallback.getBodyAsString(), re.getRawStatusCode(), re.getStatusText(),
				re.getResponseHeaders(), re.getResponseBodyAsString(), e);
		} else{
			logger(requestEntiry.getUrl(), requestEntiry.getMethod(), requestEntiry.getHeaders(),
				restCallback.getBodyAsString(), -1, null, null, null, e);
		}
	}
	private <T> void logger(RequestEntity<?> requestEntiry, RequestCallback restCallback,
			ResponseEntity<T> responseEntity, RestCallExtractor<T> restCallExtractor){
		logger(requestEntiry.getUrl(), requestEntiry.getMethod(), requestEntiry.getHeaders(),
			"body string...", responseEntity.getStatusCodeValue(),
			"status code....", responseEntity.getHeaders(),
			restCallExtractor.getBodyAsString(), null);
	}
	private void logger(URI url, HttpMethod method, HttpHeaders reqHeaders, String reqBody, int statusCode,
						String statusText, HttpHeaders resHeaders, String resBody, Exception e){
		StringBuilder sb = new StringBuilder().append('\n');
		sb.append("[REST CALL] execution times: ").append(this.executionTimes).append(" ms\n");
		sb.append("<< Request >>").append('\n');
		sb.append(" URL    : ").append(url).append('\n');
		sb.append(" Method : ").append(method).append('\n');
		sb.append(" Headers: ").append(reqHeaders).append('\n');
		sb.append(" Body   : ").append(reqBody).append('\n');
		sb.append("<< Response >>").append('\n');
		sb.append(" Code   : ").append(statusCode).append(' ').append(statusText).append('\n');
		sb.append(" Headers: ").append(resHeaders).append('\n');
		sb.append(" Body   : ").append(resBody).append('\n');
		if(Objects.isNull(e)){
			if(logger.isDebugEnabled()){
				logger.debug(sb.toString());
			}
		}
		else{
			sb.append('\n').append("# Exception : ");
			logger.error(sb.toString(), e);
		}
	}
	
	@Component
	static class Instance{
		public static RestTemplate restTemplate = new RestTemplate();

		static void set(RestTemplate restTemplate){
			Instance.restTemplate = restTemplate;
		}
		@Bean
		static RestTemplate get(){
			return Instance.restTemplate;
		}
		private static ClientInfoResolver clientInfoResolver;
		static void configRequestHeader(HttpHeaders httpHeaders){
			if(Objects.nonNull(Instance.clientInfoResolver)){
				ClientInfo clientInfo = Instance.clientInfoResolver.resolve();
				if (Objects.nonNull(clientInfo)){
					httpHeaders.set(ClientInfo.CLIENT_INFO_HEADER_NAME, MapUtil.getClassToStr(clientInfo));
				}
			}
		}
		@Autowired
		public void init(RestTemplate restTemplate){
			Instance.restTemplate = restTemplate;
		}
		@Autowired(required = false)
		public void init(ClientInfoResolver clientInfoResolver){
			Instance.clientInfoResolver = clientInfoResolver;
		}
	}
}
