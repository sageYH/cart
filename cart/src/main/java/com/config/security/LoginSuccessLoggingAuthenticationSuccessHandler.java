package com.config.security;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.common.Constant;
import com.common.utils.CommonUtil;

public class LoginSuccessLoggingAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Resource
	private CommonUtil commonUtil;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		Map<String,Object> params = new HashMap<>();
		AuthUser customAuthUser = null;
		Object token = null;

		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof AuthUser) {
			customAuthUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} else {
			token = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}

		params.put(Constant.LOG.CLIENT_IP, commonUtil.getClientIP(request));
		params.put(Constant.LOG.SERVER_IP,  commonUtil.getServerIP());
		params.put(Constant.LOG.METHOD_NM, "onAuthenticationSuccess");
		params.put(Constant.LOG.LOG_TIME, LocalDateTime.now());
		params.put(Constant.LOG.REQUEST_URI, request.getRequestURI());
		params.put(Constant.LOG.HTTP_METHOD, request.getMethod());

		if (customAuthUser != null) {
			params.put(Constant.LOG.USER_INFO, customAuthUser.getuserInfoDto());
			// 사용자 마지막 로그 일시 업데이트
			// 사용자 접속 IP
			//commonUtil.getClientIP(request)
		} else if (token != null) {
			params.put(Constant.LOG.USER_INFO, token.toString());
		}

		//log insert

		super.onAuthenticationSuccess(request, response, authentication);
	}
	
}
