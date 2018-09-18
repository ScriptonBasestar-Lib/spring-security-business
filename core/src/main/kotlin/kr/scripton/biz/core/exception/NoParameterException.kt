package kr.scripton.biz.core.exception

import org.springframework.security.core.AuthenticationException


class NoParameterException : AuthenticationException {
	constructor(msg: String) : super(msg)
	constructor(msg: String, t: Throwable) : super(msg, t)
}