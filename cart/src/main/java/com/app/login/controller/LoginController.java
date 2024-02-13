package com.app.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@Lazy
@RequiredArgsConstructor
public class LoginController {

	@RequestMapping("login.do")
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Object obj = request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		if (obj != null && obj instanceof Exception) {
			Exception e = (Exception) obj;
			model.addAttribute("result",e.getMessage());
			request.getSession().removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		} else {
			// 세션에서 사용자 정보 조회 : SSO 인증 성공이면 사용자 정보 있음
			HttpSession session = request.getSession();
			Object userInfo = session.getAttribute("DEFAULT_SESSION_USERID");
			String userId = null;
			if (userInfo != null) {
				userId = userInfo.toString();
				// 사용자 정보가 있으면 전달
				if (StringUtils.isNotBlank(userId)) {
					model.addAttribute("userInfo",userId);
				}
			}
		}
		return "app/login";
	}
	@RequestMapping("default.do")
	public String defaultLnk(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		return "app/cmnCode/cmnCodeList";
	}
	@RequestMapping("/")
	public String defaultFirst(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		return "app/cmnCode/cmnCodeList";
	}
}
