package com.api.base;

import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientResponseException;

public class ResultApiData<T> {
	private final ResponseEntity<T> responseEntity;
	private final Exception exception;
	private final RestClientResponseException responseException;
	public ResultApiData(ResponseEntity<T> responseEntity){
		this(responseEntity, null);
	}
	public ResultApiData(Exception exception){
		this(null, exception);
	}
	public ResultApiData(RestClientResponseException exception){
		this(null, exception);
	}
	public ResultApiData(ResponseEntity<T> responseEntity,Exception exception){
		this.responseEntity = responseEntity;
		this.exception = exception;
		this.responseException = null;
	}
	public ResultApiData(ResponseEntity<T> responseEntity, RestClientResponseException exception){
		this.responseEntity = responseEntity;
		this.exception = null;
		this.responseException = exception;
	}
	public MultiValueMap<String, String> getHeaders(){
		return hasResponseError() ? responseException.getResponseHeaders() : responseEntity.getHeaders();
	}
	public int getSatusCode(){
		if(Objects.nonNull(this.responseEntity)){
			return this.responseEntity.getStatusCodeValue();
		} else if(hasResponseError()){
			return this.responseException.getRawStatusCode();
		}
		return -1;
	}
	public String getStatusText(){
		if(Objects.nonNull(this.responseEntity)){
			return this.responseEntity.getStatusCode().getReasonPhrase();
		} else if(hasResponseError()){
			return this.responseException.getStatusText();
		}
		return null;
	}
	public T getBody(){
		return Objects.nonNull(this.responseEntity) ? this.responseEntity.getBody() : null;
	}
	public String getBodyAsErrorString(){
		return hasResponseError() ? responseException.getResponseBodyAsString() : null;
	}
	public boolean hasError(){
		return hasUnKownError() || hasResponseError();
	}
	public boolean hasUnKownError(){
		return Objects.nonNull(exception);
	}
	public boolean hasResponseError(){
		return Objects.nonNull(responseException);
	}
	public Exception getException(){
		return Objects.nonNull(this.exception) ? this.exception : this.responseException;
	}
	@SuppressWarnings("rawtypes")
	public String toString(T t, String title){
		if(t instanceof ApiData){
			ApiData theResponse = (ApiData) t;
			return title+":[respMsg="+theResponse.toString(title)+", exception=" + exception.getMessage() + ", responseException="+ responseException.getMessage() + "]";
		}
		return title+":[exception=" + exception.getMessage() + ", responseException="+ responseException.getMessage() + "]";
	}
}
