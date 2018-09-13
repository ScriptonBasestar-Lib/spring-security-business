package kr.scripton.business.core.auth;

import org.scriptonbasestar.tool.core.exception.runtime.SBAuthenticationException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author chaeeung.e
 * @since 2017-08-30
 */
public class NonceCheckFilter implements Filter {

	private NonceCheckService nonceCheckService;

	private String NAME_OF_NONCE = "x-auth-nonce";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request1 = (HttpServletRequest) request;
		String nonce = request1.getHeader(NAME_OF_NONCE);

		if(nonceCheckService.isDuplicate(nonce)){
			throw new SBAuthenticationException("중복 호출 - 이미 사용된 nonce 값입니다.");
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}