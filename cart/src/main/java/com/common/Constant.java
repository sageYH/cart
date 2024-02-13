package com.common;

import java.util.regex.Pattern;

public interface Constant {
	public static final String YES = "Y";
	public static final String NO = "N";
	
	// Error Page URL
	public interface ERROR_URL {
		public static final String ERROR = "/error/error";
		public static final String NOT_FOUND = "/error/notFound";//404 : 요청 URL 없는 경우
		public static final String METHOD_NOT_ALLOWED = "/error/methodNotAllowed";//405 : 요청 메소드 없는 경우
		public static final String INTERNAL_SERVER_ERROR = "/error/internalServerError";//500 : 요청 처리중 오류 발생한 경우
		public static final String UNAUTHORIZED = "/error/unauthorized";//401 : 사용자 미인증인 경우
		public static final String FORBIDDEN = "/error/forbidden";//403 : 접근 권한이 없는 경우
	}
	// 로그인 처리 메시지
	public interface LoginMessage {
		public static final String ACCOUNT_DISABLE = "ACCOUNT_DISABLE";
		public static final String ACCOUNT_EXPIRE = "ACCOUNT_EXPIRE";
		public static final String ACCOUNT_LOCK = "ACCOUNT_LOCK";
		public static final String AUTH_FAIL = "AUTH_FAIL";
		public static final String LOGIN_FAIL = "LOGIN_FAIL";
		public static final String SSO_CONNECT_FAIL = "SSO_CONNECT_FAIL";
		public static final String SSO_LOGIN_FAIL = "SSO_LOGIN_FAIL";
		public static final String UDB_CONNECT_FAIL = "UDB_CONNECT_FAIL";
		public static final String UDB_LOGIN_FAIL = "UDB_LOGIN_FAIL";
		public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
	}
	// 로그 항목
	public interface LOG {
		public static final String CLIENT_IP = "client_ip"; 		// 클라이언트 아이피
		public static final String SERVER_IP = "server_ip"; 		// 서버 IP
		public static final String CONTROLLER_NM = "controller_nm";	// 호출 컨트롤러(호출 클래스)
		public static final String METHOD_NM = "method_nm";			// 호출 메소드
		public static final String PARAMS = "msg";				// 파라미터
		public static final String REQUEST_URI = "rqst_uri";		// 호출 URL
		public static final String HTTP_METHOD = "rqst_method";		// HTTP 메소드
		public static final String USER_INFO = "user_info";			// 사용자 정보
		public static final String LOG_TIME = "log_time";			// 로그일시
	}
	//spring profile
	public interface Profile {
		public static final String LOCAL = "local";
		public static final String DEV = "dev";
		public static final String PROD = "prod";
	}
	//URL Pattern : 메뉴 조회 및 권한 확인 시 사용
	public interface UrlPattern {
		public static final Pattern MENU_CHECK_PATTERN = Pattern.compile("(/detail$|/detail/|/regist$|/regist/|/modify$|/modify/)");
		public static final Pattern AUTH_CHECK_PATTERN = Pattern.compile("(/detail$|/detail/|/regist$|/regist/|/modify$|/modify/|/insert$|/insert/|/update$|/update/|/delete$|/delete/)");
		public static final Pattern AUTH_IGNORE_PATTERN = Pattern.compile("(^/error/|^/file/|^/popup/|/popup$|/ajax/|/ajax$|/excel/|/excel$)");
	}
}
