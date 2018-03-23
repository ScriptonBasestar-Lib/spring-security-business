package kr.scripton.business.jwt.filter

import kr.scripton.business.jwt.auth.JwtUser
import kr.scripton.business.jwt.custom.JwtSsoHandler
import kr.scripton.business.jwt.exception.ExtractTextException
import kr.scripton.business.jwt.util.JwtCookieUtil
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author archmagece
 * @since 2017-10-30
 */
class JwtCookieFilter<USER_ID, JWT_USER : JwtUser<USER_ID>>(override var jwtSsoHandler: JwtSsoHandler?) : JwtAbstractFilter<USER_ID, JWT_USER>() {
	override fun extractTokenString(request: HttpServletRequest, response: HttpServletResponse): String {
		return JwtCookieUtil.tokenFromCookie(request, serviceName, signingKey) ?: throw ExtractTextException("쿠키가 존재하지 않습니다.")
	}
}
