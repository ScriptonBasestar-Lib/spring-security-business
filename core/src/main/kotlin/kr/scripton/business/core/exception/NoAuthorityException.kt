package kr.scripton.business.core.exception

import org.springframework.security.core.AuthenticationException

/**
 * @author archmagece
 * @since 2017-10-30
 */
class NoAuthorityException : AuthenticationException {
	constructor(msg: String) : super(msg)
	constructor(msg: String, t: Throwable) : super(msg, t)
}