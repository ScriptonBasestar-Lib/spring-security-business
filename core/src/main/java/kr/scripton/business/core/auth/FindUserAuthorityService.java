package kr.scripton.business.core.auth;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface FindUserAuthorityService<USER_ID> {
	/**
	 * SSO에 연동된 서비스 중 사용자에게 허용된 서비스 도메인
	 * 추가2차 : 이번에 요청이 들어온 서비스
	 *
	 * @param userId
	 * @return
	 */
	Collection<String> findUserComponent(USER_ID userId);

	Collection<GrantedAuthority> findGrantedAuthority(String... roles);

	Collection<String> findUserRole(USER_ID userId);

	Collection<String> findUserPrincipal(USER_ID userId);

	Collection<String> findPrincipalByRoleName(String roleName);
}