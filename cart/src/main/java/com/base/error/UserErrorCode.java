package com.base.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

	INACTIVE_USER(HttpStatus.FORBIDDEN, "User is inactive"),
	EXPECTATION_FAILED(HttpStatus.EXPECTATION_FAILED, "Expectation Failed"),
	;

	private final HttpStatus httpStatus;
	private final String message;
}
