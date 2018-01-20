package kr.scripton.business.oauth.auth

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

/**
 * @author archmagece
 * @since 2017-10-30
 */
class OAuthAuthenticationToken : AbstractAuthenticationToken {

	private val principal: OAuthUser

	constructor(principal: OAuthUser, authorities: Collection<GrantedAuthority>) : super(authorities) {
		this.principal = principal
	}

	override fun getCredentials(): Any? {
		return null
	}

	override fun getPrincipal(): Any {
		return principal
	}

}