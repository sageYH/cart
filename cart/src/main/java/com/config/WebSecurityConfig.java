package com.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

import com.config.security.AuthEntryPoint;
import com.config.security.AuthFailure;
import com.config.security.AuthProvider;
import com.config.security.AuthUserDetailsService;
import com.config.security.LoginSuccessLoggingAuthenticationSuccessHandler;
import com.config.security.LogoutHandler;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig<S extends Session> extends WebSecurityConfigurerAdapter {
	@Resource(name = "authEntryPoint")
	private AuthEntryPoint authEntryPoint;

	@Resource(name = "authUserDetailsService")
	private AuthUserDetailsService authUserDetailsService;

//	// Spring Session
//	@Resource
//	private FindByIndexNameSessionRepository<S> sessionRepository;

	@Override
	public void configure(WebSecurity web) {
		// 리소스 파일 관련 패턴은 무시하도록 시큐리티 설정
		web.ignoring().antMatchers("/css/**", "/js/**", "/assets/**", "/img/**", "/favicon.ico");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Spring Session
		http.sessionManagement().disable();
				// .maximumSessions(1)    				 // 사용자별 최대세션
				// .maxSessionsPreventsLogin(false)	 // 세션이 사용중이면 이전 사용자를 로그아웃 처리
				// .expiredUrl("/login");
				// .sessionRegistry(sessionRegistry()); // prinicipal 문자열 이름으로 동일한 사용자를 체크
		// 시큐리티 관련 기본셋팅
		http.headers().frameOptions().sameOrigin();

		http.csrf().disable();

		//http.formLogin().loginPage("/login.do").defaultSuccessUrl("/", true).permitAll();
		http.formLogin().loginPage("/login.do").defaultSuccessUrl("/default.do", true).loginProcessingUrl("/lgn.do")
				.usernameParameter("userId").passwordParameter("userPwd")
				.successHandler(loginSuccessHandler()).failureHandler(authFailure()).permitAll();
		http.logout().logoutUrl("/logout.do").logoutSuccessHandler(logoutHandler()).invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll();
		//SSO LOGIN 중간 처리
		http.authorizeRequests().antMatchers("/login.do").permitAll();
		http.authorizeRequests().antMatchers("/sso/**").permitAll();
		//외부 시스템 요청은 인증 없이 처리
		http.authorizeRequests().antMatchers("/api/**").permitAll();
		//error
		http.authorizeRequests().antMatchers("/error/**").permitAll();

		http.authorizeRequests().antMatchers("/**").access("@authChecker.check(request,authentication)").and().httpBasic().authenticationEntryPoint(authEntryPoint);
	}

	/**
	 * 인증 처리
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getAuthProvider());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthProvider getAuthProvider() {
		AuthProvider authProvider = new AuthProvider();
		authProvider.setUserDetailsService(authUserDetailsService);
		authProvider.setHideUserNotFoundExceptions(false);
		return authProvider;
	}
	
//	// Spring Session
//	@Bean
//	public SpringSessionBackedSessionRegistry<S> sessionRegistry() {
//		return new SpringSessionBackedSessionRegistry<>(this.sessionRepository);
//	}
	
	@Bean
	public LoginSuccessLoggingAuthenticationSuccessHandler loginSuccessHandler() {
		return new LoginSuccessLoggingAuthenticationSuccessHandler();
	}
	
	@Bean
	public LogoutHandler logoutHandler() {
		return new LogoutHandler();
	}
	
	@Bean
	public AuthFailure authFailure() {
		AuthFailure authFailure = new AuthFailure();
		//authFailure.setDefaultFailureUrl("/login.do");
		return authFailure;
	}
}
