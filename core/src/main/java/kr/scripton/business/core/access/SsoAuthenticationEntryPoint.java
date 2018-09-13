package kr.scripton.business.core.access;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SsoAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private String redirect;

	public SsoAuthenticationEntryPoint(String redirect){
		this.redirect = redirect;
	}
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			response.sendRedirect(this.redirect);
		}
	}
}
