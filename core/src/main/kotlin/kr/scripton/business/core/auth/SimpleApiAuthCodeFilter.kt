package kr.scripton.business.core.auth

import kr.scripton.business.core.exception.NoAuthorityException
import org.springframework.http.HttpHeaders
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author archmagece@gmail.com
 * @since 2017-08-30
 */
class SimpleApiAuthCodeFilter : OncePerRequestFilter() {

	private var serverAuthKey: String? = null
	private var serverAuthKeyLength = 0
	private var serverAuthCode: String? = null

	@Throws(ServletException::class)
	override fun initFilterBean() {
		serverAuthKey = filterConfig!!.getInitParameter("config.server.auth-key").trim { it <= ' ' } + " "
		serverAuthKeyLength = serverAuthKey!!.length
		serverAuthCode = filterConfig!!.getInitParameter("config.server.auth-code").trim { it <= ' ' }
	}

	@Throws(ServletException::class, IOException::class)
	override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
		val header = request.getHeader(HttpHeaders.AUTHORIZATION)

		if (header != null && header.startsWith(serverAuthKey!!)) {
			val serverAuthCodeParam = header.substring(serverAuthKeyLength)
			if (serverAuthCodeParam.trim { it <= ' ' }.contentEquals(serverAuthCode!!)) {
				filterChain.doFilter(request, response)
				return
			}
		}
		//헤더가 없거나 코드값이 틀리면 접근금지
		throw NoAuthorityException("API auth failed. code is wrong or empty")
	}
}
