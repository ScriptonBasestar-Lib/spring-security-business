package kr.scripton.biz.core.filter

import org.springframework.security.core.AuthenticationException
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import java.nio.file.AccessDeniedException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author archmagece
 * @since 2015-04-28-19
 */
class TimeOutCheckFilter : GenericFilterBean() {

	private val ajaxHeader: String? = null

	//http://lng1982.tistory.com/170
	@Throws(IOException::class, ServletException::class)
	override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
		val req = request as HttpServletRequest
		val res = response as HttpServletResponse

		if (isAjaxRequest(req)) {
			try {
				chain.doFilter(req, res)
			} catch (e: AccessDeniedException) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN)
			} catch (e: AuthenticationException) {
				res.sendError(HttpServletResponse.SC_UNAUTHORIZED)
			}

		} else {
			chain.doFilter(req, res)
		}
	}

	private fun isAjaxRequest(req: HttpServletRequest): Boolean {
		return req.getHeader(ajaxHeader) != null && req.getHeader(ajaxHeader) == java.lang.Boolean.TRUE.toString()
	}
}
