package kr.scripton.business.jwt.filter

import kr.scripton.business.jwt.auth.JwtUser
import kr.scripton.business.jwt.custom.JwtSsoHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthCookieFilter<USER_ID, JWT_USER : JwtUser<USER_ID>>(override var jwtSsoHandler: JwtSsoHandler?) : JwtAbstractFilter<USER_ID, JWT_USER>() {
    override fun extractTokenString(request: HttpServletRequest, response: HttpServletResponse): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
