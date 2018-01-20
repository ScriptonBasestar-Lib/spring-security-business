package kr.scripton.business.jwt.filter

import kr.scripton.business.jwt.auth.JwtUser
import kr.scripton.business.jwt.util.JwtCookieUtil
import org.scriptonbasestar.tool.core.exception.compiletime.SBTextExtractException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author archmagece
 * @since 2017-10-30
 */
class JwtCookieFilter<USER_ID, JWT_USER : JwtUser<USER_ID>> : JwtAbstractFilter<USER_ID, JWT_USER>() {
	override fun extractTokenString(request: HttpServletRequest, response: HttpServletResponse): String {
		return JwtCookieUtil.tokenFromCookie(request, serviceName, signingKey) ?: throw SBTextExtractException("쿠키가 존재하지 않습니다.")
	}
}
