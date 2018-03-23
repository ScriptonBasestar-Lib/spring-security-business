package kr.scripton.business.jwt.util

import javax.servlet.http.HttpServletRequest

/**
 * @author archmagece
 * @since 2018-03-24 오전 12:20
 * @at spring-business-wrapper
 */
object JwtRequestUtil {

	fun getUserId(request: HttpServletRequest): Long? {
		return request.getAttribute(AuthorizedUserClaims.USER_ID) as Long
		//		return Long.parseLong(request.getAttribute(SBAuthorizedUserClaims.USER_ID).toString());
	}

	fun getUserUsername(request: HttpServletRequest): String {
		return request.getAttribute(SBAuthorizedUserClaims.USER_USERNAME) as String
	}

	fun getUserNickname(request: HttpServletRequest): String {
		return request.getAttribute(SBAuthorizedUserClaims.USER_NICKNAME) as String
	}

	fun getUserRole(request: HttpServletRequest): Collection<String> {
		return request.getAttribute(SBAuthorizedUserClaims.USER_ROLE) as Collection<String>
	}
}