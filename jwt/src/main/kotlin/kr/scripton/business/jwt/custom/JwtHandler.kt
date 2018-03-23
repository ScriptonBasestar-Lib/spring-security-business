package kr.scripton.business.jwt.custom

import com.auth0.jwt.interfaces.Claim
import kr.scripton.business.jwt.auth.JwtUser
import org.springframework.security.core.Authentication
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author archmagece
 * @since 2017-11-10
 */
interface JwtHandler<out USER_ID, out JWT_USER : JwtUser<USER_ID>> {
	fun generateUser(signingKey: String, jwtToken: String): JWT_USER
	fun generateToken(signingKey: String, claim: Claim): String
}