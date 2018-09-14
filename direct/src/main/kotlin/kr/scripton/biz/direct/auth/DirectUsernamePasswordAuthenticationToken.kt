package kr.scripton.biz.direct.auth

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class DirectUsernamePasswordAuthenticationToken : UsernamePasswordAuthenticationToken {

	val requestSignature: String

	constructor(principal: String,
				credentials: String,
				requestSignature: String) : super(principal, credentials) {
		this.requestSignature = requestSignature
	}
}