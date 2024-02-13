package com.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.userInfo.model.UserInfoDto;

/**
 * 스프링 시큐리티 로그인 처리를 위한 커스텀 UserDetails 클래스
 */
public class AuthUser implements UserDetails {

	private static final long serialVersionUID = -467945565843728173L;

	private String userId;
	private String userNm;
	private Collection<? extends GrantedAuthority> authorities;

	// 계정 만료
	private boolean isAccountNonExpired;
	// 계정 잠금
	private boolean isAccountNonLocked;
	// 비번 만료
	private boolean isCredentialsNonExpired;
	// 계정 활성 여부
	private boolean isEnabled;

	//SSO check 여부
	private boolean isSso = false;

	// 사용자 정보
	private UserInfoDto userInfoDto;
	//회사코드
	private String compId;
	//국가코드
	@SuppressWarnings("unused")
	private String cntryCd;

	public AuthUser(UserInfoDto userInfoDto, Collection<? extends GrantedAuthority> authorities, boolean isSso, String compId) {
		this.userId = userInfoDto.getUserId();
		this.userNm = userInfoDto.getUserNm();
		this.authorities = authorities;
		//계정 만료 :  - PSWD_EXPECTED_YMD : expired_yn
		this.isAccountNonExpired = userInfoDto.getExpectedYn().equals("N");
		// 계정 잠금 :  - 최종 로그인 180일 경과 : lock_yn
		this.isAccountNonLocked = userInfoDto.getLockYn().equals("N");
		// 비번 만료 : 비번 관리하지 않음
		this.isCredentialsNonExpired = true;
		// 계정 활성 여부
		this.isEnabled = userInfoDto.getUseYn().equals("Y");

		//SSO 인증 여부
		this.isSso = isSso;

		// 사용자 정보
		this.userInfoDto = userInfoDto;
		
		//회사코드
		this.compId=compId;
		//cntryCd
		this.cntryCd=userInfoDto.getCntryCd();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AuthUser) {
			return this.userId.equals(((AuthUser)obj).userId);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.userId.hashCode();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 권한
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.userId;
	}

	@Override
	public String getUsername() {
		return this.userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		// 계정 만료 - PSWD_EXPECTED_YMD : expired_yn
		return this.isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정 잠금 - 최종 로그인 180일 경과 : lock_yn
		return this.isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 비번 만료 여부 : 없음
		return this.isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		// 계정 활성 여부 : use_yn
		return this.isEnabled;
	}

	public boolean isSso() {
		//SSO 인증 여부
		return this.isSso;
	}

	public UserInfoDto getuserInfoDto() {
		// 사용자 정보
		return this.userInfoDto;
	}
	
	public String getcompId() {
		//회사코드
		return this.compId;
	}
}
