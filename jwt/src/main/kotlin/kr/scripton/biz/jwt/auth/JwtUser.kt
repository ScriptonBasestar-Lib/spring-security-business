package kr.scripton.biz.jwt.auth

import com.auth0.jwt.interfaces.Claim
import kr.scripton.biz.core.auth.BusinessUser

interface JwtUser<out USER_ID> : BusinessUser<USER_ID> {
	val claims: Map<String, Claim>
}