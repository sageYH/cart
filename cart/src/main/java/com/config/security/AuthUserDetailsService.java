package com.config.security;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.app.userInfo.model.UserInfoExDto;
import com.app.userInfo.service.UserInfoService;
import com.common.Constant;

import lombok.extern.slf4j.Slf4j;

/**
 * 스프링 시큐리티의 username에 따른 사용자를 조회하기 위한 커스텀 서비스 클래스
 */
@Slf4j
@Component
public class AuthUserDetailsService implements UserDetailsService {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private HttpServletRequest request;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AuthUser authUser = null;

		// login.jsp 에서 SSO 갔다오고 난 후에 /ssologin 에서 SSO 인증 확인하고 login.jsp 에서 바로 form submit 호출 하여 스프링 시큐리티 시작
		String sso = "";
		String compId = "";
		try {
			sso = request.getParameter("sso")==null?"":request.getParameter("sso");
			compId = request.getParameter("compId");
		}catch(Exception e) {}
		boolean isSso = StringUtils.equalsIgnoreCase("Y", sso);
		if (StringUtils.isNotBlank(username)) {
			// 사용자 정보 DB 조회
			UserInfoExDto userInfoExDto = new UserInfoExDto();
			userInfoExDto.setUserId(username);
			UserInfoExDto member;
			try {
				member = userInfoService.getUserInfo(userInfoExDto);
			} catch (Exception e) {
				throw new UsernameNotFoundException(Constant.LoginMessage.USER_NOT_FOUND);
			}
			if (member != null) {
				// 계정 활성 : useYn
				if (StringUtils.equals("N", member.getUseYn())) {
					throw new UsernameNotFoundException(Constant.LoginMessage.ACCOUNT_DISABLE);
				}
				//계정 잠김 : lastLogDt
				if (StringUtils.equals("Y", member.getLockYn())) {
					throw new UsernameNotFoundException(Constant.LoginMessage.ACCOUNT_LOCK);
				}
				// 계정 만료 : startDt ~ endDt
				if (StringUtils.equals("Y", member.getExpectedYn())) {
					throw new UsernameNotFoundException(Constant.LoginMessage.ACCOUNT_EXPIRE);
				}
				// 권한
				Set<GrantedAuthority> authorities = new HashSet<>();
				authorities.add(new SimpleGrantedAuthority(StringUtils.joinWith("_", "ROLE","USER")));
				authUser = new AuthUser(member, authorities, isSso, compId);
			}
		}
		if (authUser == null) {
			throw new UsernameNotFoundException(Constant.LoginMessage.USER_NOT_FOUND);
		}
		return authUser;
	}
}
