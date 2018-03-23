package kr.scripton.business.core.filter

import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author archmagece
 * @since 2018-03-24 오전 12:49
 * @at spring-business-wrapper
 */
class RequestRedirectFilter : GenericFilterBean() {

	@Throws(IOException::class, ServletException::class)
	override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

		if (request is HttpServletRequest && response is HttpServletResponse) {
			var redirectTarget = request.requestURL.toString().replaceFirst("https".toRegex(), "http")
			redirectTarget = redirectTarget.replaceFirst(":8443".toRegex(), ":8080")
			if (!request.isSecure()) {
				response.sendRedirect(redirectTarget)
				return
			}
		}

		chain.doFilter(request, response)
	}
}
