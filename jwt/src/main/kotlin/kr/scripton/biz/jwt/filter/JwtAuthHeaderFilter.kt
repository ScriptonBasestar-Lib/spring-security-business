package kr.scripton.biz.jwt.filter

import kr.scripton.biz.jwt.auth.JwtUser
import kr.scripton.biz.jwt.custom.JwtSsoHandler
import kr.scripton.biz.jwt.exception.ExtractTextException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthHeaderFilter<USER_ID, JWT_USER : JwtUser<USER_ID>>(override var jwtSsoHandler: JwtSsoHandler?) : JwtAbstractFilter<USER_ID, JWT_USER>() {
    override fun extractTokenString(request: HttpServletRequest, response: HttpServletResponse): String {
        val authHeader = request.getHeader("Authorization") ?: throw ExtractTextException("인증 헤더가 없습니다.")
        if (!authHeader.matches("^(?i)Bearer\\s+.+".toRegex())) {
            throw ExtractTextException("JWT 인증 헤더가 아닙니다.")
        }
        return authHeader.split("(?i)Bearer\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
    }
//    @Throws(SBTextExtractException::class)
//    protected fun extractTokenString(request: HttpServletRequest, response: HttpServletResponse): String {
//        val authHeader = request.getHeader("Authorization") ?: throw SBTextExtractException("인증 헤더가 없습니다.")
//        if (!authHeader.matches("^(?i)Bearer\\s+.+".toRegex())) {
//            throw SBTextExtractException("JWT 인증 헤더가 아닙니다.")
//        }
//        return authHeader.split("(?i)Bearer\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
//    }

}
