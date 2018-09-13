package kr.scripton.business.core.auth;


import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author archmagece
 * @since 2017-10-30
 */
public interface BusinessUser<USER_ID> extends UserDetails {
	/**
	 * 사용자를 나타내는 값
	 * ex) 데이터베이스의 SEQ, 사용자의 아이디, 이메일, 전화번호.. 등 서비스에서 사용하는값에 따라
	 * Unique, Immutable
	 */
	USER_ID getUnique();
	/**
	 * 서비스에서 사용자의 공개 이름
	 * 실명, 별명, 무관
	 */
	String getNickname();
	/**
	 * 서비스에서 사용자 역할
	 * ex) MASTER, MANAGER, USER... 등
	 */
	Collection<String> getRoles();
}