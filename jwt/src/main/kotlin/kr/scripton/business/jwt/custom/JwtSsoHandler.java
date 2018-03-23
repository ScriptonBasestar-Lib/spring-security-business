package kr.scripton.business.jwt.custom;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author archmagece
 * @since 2017-10-11
 * FIXME sso handler 걍handler 역할 구분
 */
public interface JwtSsoHandler {

	void postProcessing(HttpServletRequest request, HttpServletResponse response, Authentication authentication);

}
