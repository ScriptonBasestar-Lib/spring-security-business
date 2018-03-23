package kr.scripton.business.jwt.filter

import kr.scripton.business.jwt.auth.JwtUser
import kr.scripton.business.jwt.custom.JwtSsoHandler
import kr.scripton.business.jwt.exception.ExtractTextException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author archmagece
 * @since 2017-10-30
 */
class JwtHeaderFilter<USER_ID, JWT_USER : JwtUser<USER_ID>>(override var jwtSsoHandler: JwtSsoHandler?) : JwtAbstractFilter<USER_ID, JWT_USER>() {
	override fun extractTokenString(request: HttpServletRequest, response: HttpServletResponse): String {
		val authHeader = request.getHeader("Authorization") ?: throw ExtractTextException("인증 헤더가 없습니다.")
		if (!authHeader.matches("^(?i)Bearer\\s+.+".toRegex())) {
			throw ExtractTextException("JWT 인증 헤더가 아닙니다.")
		}
		return authHeader.split("(?i)Bearer\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
	}
}
