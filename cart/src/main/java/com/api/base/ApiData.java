package com.api.base;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ApiData<T> {
	private String code;	//success
	private String message;
	private String additionalMessage;
	private Date responseTime;
	private PageInfo pageInfo;
	private T body;
	public static ApiData<Void> create(){
		return create(null);
	}
	public static <T> ApiData<T> create(T body){
		return ApiData.<T>builder().body(body).build();
	}
	@Override
	public String toString(){
		return "ApiData [code=" + code + ", message=" + message + ", additionalMessage=" + additionalMessage + "}";
	}
	public String toString(String title){
		return title+":[code=" + code + ", message=" + message + ", additionalMessage=" + additionalMessage + "]";
	}
}
