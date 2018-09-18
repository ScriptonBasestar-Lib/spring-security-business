package kr.scripton.biz.core.filter

import kr.scripton.biz.core.registery.NonceCheckService
import kr.scripton.biz.core.exception.NoAuthorityException
import kr.scripton.biz.core.exception.NoParameterException
import kr.scripton.biz.core.registery.LocalNonceCheckService
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

/**
 * @author chaeeung.e
 * @since 2017-08-30
 *
 * Global 전체 시스템에서 중복되는 nonce 값 모두 사용금지
 */
class GlobalNonceCheckFilter(
		private val nonceName: String = DEFAULT_NONCE_NAME,
		private val nonceCheckService: NonceCheckService = LocalNonceCheckService()
) : GenericFilterBean() {

	override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
		val httpServletRequest = request as HttpServletRequest
		val nonce = httpServletRequest.getHeader(nonceName) ?: throw NoParameterException("no header - nonce header must not be null")

		if (nonceCheckService.isDuplicate(nonce)) {
			throw NoAuthorityException("duplicate call - this nonce is already used.")
		}

		chain.doFilter(request, response)
	}

	companion object {
		const val DEFAULT_NONCE_NAME = "x-auth-nonce"
	}
}