package kr.scripton.business.direct.auth

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class DirectUsernameOnlyAuthenticationToken : UsernamePasswordAuthenticationToken {
	constructor(principal: Any) : super(principal, null)
	constructor(principal: Any, credentials: Any) : super(principal, credentials)
}