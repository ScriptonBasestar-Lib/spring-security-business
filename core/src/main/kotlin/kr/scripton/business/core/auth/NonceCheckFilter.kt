package kr.scripton.business.core.auth

import org.scriptonbasestar.tool.core.exception.runtime.SBAuthenticationException
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

/**
 * @author chaeeung.e
 * @since 2017-08-30
 */
class NonceCheckFilter : Filter {

    lateinit var nonceCheckService: NonceCheckService

    private var NAME_OF_NONCE = "x-auth-nonce"

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain) {
        var httpServletRequest = request as HttpServletRequest
        var nonce = httpServletRequest.getHeader(NAME_OF_NONCE)

        if(nonceCheckService.isDuplicate(nonce)){
            throw SBAuthenticationException("중복 호출 - 이미 사용된 nonce 값입니다.")
        }

        chain.doFilter(request, response)
    }


}