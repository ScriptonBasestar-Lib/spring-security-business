package kr.scripton.business.core.filter

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.HashSet
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @athor archmagece
 * @since 2017-01-30 18
 *
 * 권한없는 사용자 특정 페이지에 가둬놓기..
 *
 */
class SecurityPageJailFilter(userRoles: Set<String>, private val redirectToUri: String, vararg uriPatterns: String) : OncePerRequestFilter() {

	private val grantedAuthoritySet: MutableSet<GrantedAuthority>
	private val uriPatterns: Array<String>
	//	private RequestMatcher requiresAuthenticationRequestMatcher;
	private val antPathMatcher = AntPathMatcher()

	init {
		grantedAuthoritySet = HashSet()
		for (userRole in userRoles) {
			grantedAuthoritySet.add(SimpleGrantedAuthority(userRole))
		}
		this.uriPatterns = uriPatterns
	}

	@Throws(ServletException::class, IOException::class)
	override fun doFilterInternal(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, filterChain: FilterChain) {
		//패턴잡아서 맞을 때 redirect\
		for (uriPattern in uriPatterns) {
			if (antPathMatcher.match(uriPattern, httpServletRequest.requestURI)) {
				httpServletResponse.sendRedirect(redirectToUri)
			}
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse)
	}
}
