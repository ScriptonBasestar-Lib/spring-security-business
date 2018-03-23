package kr.scripton.business.jwt.filter

import kr.scripton.business.core.auth.FindUserAuthorityService
import kr.scripton.business.core.auth.SsoAuthSuccessHandler
import kr.scripton.business.core.util.CookieUtil
import kr.scripton.business.jwt.auth.JwtUser
import kr.scripton.business.jwt.custom.JwtHandler
import org.springframework.security.core.Authentication
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author archmagece
 * @since 2017-10-30
 */
class SsoJwtAuthSuccessHandler<JWT_ID, JWT_USER : JwtUser<JWT_ID>> : SsoAuthSuccessHandler {

	private val serviceName: String
	private val signingKey: String
	private val findUserAuthorityService: FindUserAuthorityService<JWT_ID>
	private val jwtHandler: JwtHandler<JWT_ID, JWT_USER>

	constructor(serviceName: String, signingKey: String, findUserAuthorityService: FindUserAuthorityService<JWT_ID>, jwtHandler: JwtHandler<JWT_ID, JWT_USER>) {
		this.serviceName = serviceName
		this.signingKey = signingKey
		this.findUserAuthorityService = findUserAuthorityService
		this.jwtHandler = jwtHandler
	}

	override fun postProcessing(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
		TODO()
//		val claim = authentication.principal as JwtUser<JWT_ID>
//		for (domain in findUserAuthorityService.findUserComponent(claim.userUnique)) {
//			CookieUtil.toCookie(response, domain, serviceName, jwtHandler.generateToken(signingKey, claim))
//		}
	}

	override fun support(authentication: Authentication):Boolean {

		return authentication.principal::class.java.isAssignableFrom(JwtUser::class.java)
	}

}
