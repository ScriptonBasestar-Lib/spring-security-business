package kr.scripton.biz.direct.filter

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import java.util.regex.Pattern
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

/**
 * @author archmagece
 * @since 2018-03-24 오전 12:47
 * @at spring-business-wrapper
 */
class HardKeyAuthenticationFilter(private val secretKey: String, vararg ignorePatterns: String) : GenericFilterBean() {
	private val ignorePatterns: Array<Pattern>
	var secretHeader = "hardKeySecret"
//		set(secretHeader) {
//			field = secretHeader
//		}

	init {
		this.ignorePatterns = Array(ignorePatterns.size){
			i -> Pattern.compile(ignorePatterns[i])
		}
	}

	@Throws(IOException::class, ServletException::class)
	override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
		val secret = (request as HttpServletRequest).getHeader(this.secretHeader)

		if (secret == null || secret.isEmpty()) {
			throw BadCredentialsException("인증실패 : 키가 없음")
		}
		if (secret != secretKey) {
			throw BadCredentialsException("인증실패 : 키가 틀림")
		}
		TODO() // TODO authorities 기본권한??사용자 권한??
//		val authorities = setOf<GrantedAuthority>()
//		val userDetails = User("apiuser", "", true, true, true, true, authorities)
//		val authRequest = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
//		SecurityContextHolder.getContext().authentication = authRequest
//
//		chain.doFilter(request, response)
	}
}
