package com.config.security;

import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.common.Constant;

import lombok.extern.slf4j.Slf4j;

/**
 * 스프링 시큐리티 설정
 * 권한을 동적으로 생성하는 경우 security config를 수정하고 재배포하여 처리하지 않도록 하기위해
 * 권한 체크를 HTTP 요청 시 DB에서 HTTP 요청 URI를 확인하여 처리
 */
@Slf4j
@Component
public class AuthChecker {
	public boolean check(HttpServletRequest request, Authentication authentication) {
		log.info("##########Start Authcheck##########");
		
		boolean result = false;

		Object principal = authentication.getPrincipal();
		if (principal != null && principal instanceof AuthUser) {
			//인증 사용자
			//인증 사용자의 권한, 요청 URL을 메뉴, 권한 테이블에서 조회하여 허용되는 권한이면 true 반환
			//현재는 인증 사용자면 모두 접근 가능하도록 true 반환
			AuthUser user = (AuthUser) principal;
			String userId = user.getuserInfoDto().getUserId();
			String userNm = user.getuserInfoDto().getUserNm();
			String compId = user.getuserInfoDto().getCompId();
			request.setAttribute("USERID",userId);
			request.setAttribute("USERNM",userNm);
			request.setAttribute("COMPID",compId);

			String uri = request.getRequestURI();
			Matcher ignoreMatcher = Constant.UrlPattern.AUTH_IGNORE_PATTERN.matcher(uri);
			if (ignoreMatcher.find() || uri.equals("/")) {
				// 인증 사용자 모두 요청 가능한 경우
				return true;
			}
			
			//요청 uri 와 사용자 메뉴권한 확인
			return true;		//향후 삭제
			
		} else {
			//익명 사용자
			//result = true;	//향후 제거
		}
		
		log.info("##########End Authcheck##########");

		return result;
	}
}
