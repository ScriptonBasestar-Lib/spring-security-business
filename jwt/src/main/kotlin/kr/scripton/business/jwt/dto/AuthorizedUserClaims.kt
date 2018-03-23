package kr.scripton.business.jwt.dto

import com.auth0.jwt.interfaces.Claim
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * @author archmagece
 * @since 2018-03-24 오전 12:22
 * @at spring-business-wrapper
 */
class AuthorizedUserClaims(private val username: String,
						   private val id: Long,
						   private val nickname: String,
						   private val type: String,
						   private val roles: Collection<String>,
						   private val authorityScope: Collection<String>,
						   private val authorities: Set<GrantedAuthority>,
						   private val enabled: Boolean,
						   private val accountNonExpired: Boolean,
						   private val credentialsNonExpired: Boolean,
						   private val accountNonLocked: Boolean,
						   private val password: String?) : UserDetails {

	override fun getUsername() = username
	override fun getAuthorities() = authorities
	override fun isEnabled() = enabled
	override fun isCredentialsNonExpired() = credentialsNonExpired
	override fun getPassword() = password
	override fun isAccountNonExpired() = accountNonExpired
	override fun isAccountNonLocked() = accountNonLocked

	companion object {
		const val USER_USERNAME = "unm"
		const val USER_ID = "uid"
		const val USER_NICKNAME = "nnm"
		/**
		 * 사용자 종류 user type : ust
		 * human : 사용자가 접근하는 리소스, 사용자에게 소유권\
		 * machine : 서버에서 접근하는 리소스, 한 서비스에 소유권
		 */
		const val USER_TYPE = "utp"
		/**
		 * 접근권한 확인용 글로벌 롤
		 */
		const val USER_ROLE = "uro"
		/**
		 * authority scope : ats
		 * global : 한 서비스 집단 전체에 사용되는 권한\
		 * 사용가능 서비스 목록
		 * NOT_EMPTY
		 */
		const val AUTHORITY_SCOPE = "ats"
	}

	constructor(
			claims: Map<String, Claim>,
			enabled: Boolean,
			accountNonExpired: Boolean,
			credentialsNonExpired: Boolean,
			accountNonLocked: Boolean) : this(
				claims[USER_USERNAME]!!.asString(),
				claims[USER_ID]!!.asLong(),
				claims[USER_NICKNAME]!!.asString(),
				claims[USER_TYPE]!!.asString(),
				claims[USER_ROLE]!!.asList(String::class.java),
				claims[AUTHORITY_SCOPE]!!.asList(String::class.java),
				emptySet(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, null) {
		// not contains
//		if(!(claims.containsKey(USER_USERNAME) && claims.containsKey(USER_ID) && claims.containsKey(USER_NICKNAME) && claims.containsKey(USER_TYPE) && claims.containsKey(USER_ROLE) &&  claims.containsKey(AUTHORITY_SCOPE))){
//			throw IllegalArgumentException(
//					"필수 인증값이 빠져 있습니다")
//		}
	}

}