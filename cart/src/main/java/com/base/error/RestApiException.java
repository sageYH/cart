package com.base.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//ex) throw new RestApiException(UserErrorCode.EXPECTATION_FAILED);
@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException {

	private static final long serialVersionUID = -7207608042615948686L;
	private final ErrorCode errorCode;
}
