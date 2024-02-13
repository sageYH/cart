package com.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.config.security.AuthUser;

public class MenuCheckInterceptor implements HandlerInterceptor {
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if (modelAndView == null) return;
		if (SecurityContextHolder.getContext().getAuthentication() == null) return;
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof AuthUser == false) return;
		
		AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uri = request.getRequestURI();
		
		//추가 params
		//modelAndView.addObject("add", "");
		
	}

}
