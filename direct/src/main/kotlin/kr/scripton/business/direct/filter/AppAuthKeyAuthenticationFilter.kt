package kr.scripton.business.direct.filter

import kr.scripton.business.direct.auth.AppAuthKeyManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

/**
 * @author archmagece
 * @since 2018-03-24 오전 12:44
 * @at spring-business-wrapper
 */
class AppAuthKeyAuthenticationFilter(//	@Resource(name = "userServiceCustom")
		private val userDetailsService: UserDetailsService, //	@Autowired
		private val appKeyManager: AppAuthKeyManager) : GenericFilterBean() {

	var usernameHeader = "username"
	var secretHeader = "secret"

	@Throws(IOException::class, ServletException::class)
	override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
		val username = (request as HttpServletRequest).getHeader(this.usernameHeader)
		val secret = request.getHeader(this.secretHeader)

		if (!(username == null || username.isEmpty() || secret == null || secret.isEmpty())) {
			if (!appKeyManager.check(username, secret)) {
				//TODO exception 변경.- 오류나면서 response 발생.
				throw BadCredentialsException("인증실패")
			}

			val userDetails = userDetailsService.loadUserByUsername(username)
			//			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));

			//			UsernameOnlyAuthenticationToken authRequest = new UsernameOnlyAuthenticationToken(email,secret);
			val authRequest = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
			SecurityContextHolder.getContext().authentication = authRequest
			//			return this.getAuthenticationManager().authenticate(authRequest);
		}

		chain.doFilter(request, response)
	}
}
