//package kr.scripton.business.core.rememberme
//
//import org.springframework.beans.factory.annotation.Qualifier
//import org.springframework.security.core.Authentication
//import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.core.userdetails.UsernameNotFoundException
//import org.springframework.security.crypto.codec.Base64
//import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices
//import org.springframework.security.web.authentication.rememberme.CookieTheftException
//import org.springframework.security.web.authentication.rememberme.InvalidCookieException
//import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException
//import java.security.SecureRandom
//import java.util.*
//import javax.servlet.http.HttpServletRequest
//import javax.servlet.http.HttpServletResponse
//
///**
// * @author archmagece
// * @since 2018-03-24 오전 12:37
// * @at spring-business-wrapper
// */
//
//class RedisRememberMeServices : AbstractRememberMeServices {
//
//	private val tokenRepository: RedisRememberMeTokenRepository
//	private val random: SecureRandom
//
//	private val seriesLength = DEFAULT_SERIES_LENGTH
//	private val tokenLength = DEFAULT_TOKEN_LENGTH
//
//	constructor(cookieKey: String, userDetailsService: UserDetailsService, tokenRepository: RedisRememberMeTokenRepository) : super(cookieKey, userDetailsService) {
//		this.tokenRepository = tokenRepository
//		random = SecureRandom()
//	}
//
//
//	constructor(cookieKey: String, userDetailsService: UserDetailsService, @Qualifier redisTemplate: RedisTemplate<String, RedisRememberMeToken>) : super(cookieKey, userDetailsService) {
//		tokenRepository = RedisRememberMeTokenRepository(redisTemplate)
//		random = SecureRandom()
//	}
//
//	override fun onLoginSuccess(request: HttpServletRequest, response: HttpServletResponse, successfulAuthentication: Authentication) {
//		val username = successfulAuthentication.name
//
//		logger.debug("Creating new persistent login for user " + username)
//
//		val rememberMeToken = RedisRememberMeToken(username, generateSeriesData(), generateTokenData(), Date())
//		try {
//			tokenRepository.storeToken(username, rememberMeToken)
//			addCookie(rememberMeToken, request, response)
//		} catch (e: Exception) {
//			logger.error("Failed to save persistent token ", e)
//		}
//
//	}
//
//	@Throws(RememberMeAuthenticationException::class, UsernameNotFoundException::class)
//	override fun processAutoLoginCookie(cookieTokens: Array<String>, request: HttpServletRequest, response: HttpServletResponse): UserDetails {
//
//		if (cookieTokens.size != 2) {
//			throw InvalidCookieException("Cookie token did not contain " + 2 + " tokens, but contained '" + Arrays.asList(*cookieTokens) + "'")
//		}
//
//		val presentedSeries = cookieTokens[0]
//		val presentedToken = cookieTokens[1]
//
//		val token = tokenRepository
//				.getToken(presentedSeries)
//				?: // No series match, so we can't authenticate using this cookie
//				throw RememberMeAuthenticationException(
//						"No persistent token found for series id: " + presentedSeries)
//
//// We have a match for this user/series combination
//		if (presentedToken != token!!.getTokenValue()) {
//			// Token doesn't match series value. Delete all logins for this user and throw
//			// an exception to warn them.
//			tokenRepository.removeToken(token!!.getUsername())
//
//			throw CookieTheftException(
//					messages.getMessage(
//							"PersistentTokenBasedRememberMeServices.cookieStolen",
//							"Invalid remember-me token (Series/token) mismatch. Implies previous cookie theft attack."))
//		}
//
//		if (token!!.getDate().getTime() + tokenValiditySeconds * 1000L < System
//						.currentTimeMillis()) {
//			throw RememberMeAuthenticationException("Remember-me login has expired")
//		}
//
//		// Token also matches, so login is valid. Update the token value, keeping the
//		// *same* series number.
//		if (logger.isDebugEnabled) {
//			logger.debug("Refreshing persistent login token for user '"
//					+ token!!.getUsername() + "', series '" + token!!.getUsername() + "'")
//		}
//
//		val newToken = RedisRememberMeToken(token!!.getUsername(), token!!.getSeries(), generateTokenData(), Date())
//
//		if (!tokenRepository.exists(newToken.getUsername())) {
//			tokenRepository.storeToken(newToken.getUsername(), newToken)
//			addCookie(newToken, request, response)
//		} else {
//			logger.error("Failed to update token: Token Already Exists")
//			throw RememberMeAuthenticationException("Autologin failed due to data access problem")
//		}
//
//		return userDetailsService.loadUserByUsername(token!!.getUsername())
//	}
//
//	private fun addCookie(token: RedisRememberMeToken, request: HttpServletRequest, response: HttpServletResponse) {
//		setCookie(arrayOf(token.getUsername(), token.getTokenValue()), tokenValiditySeconds, request, response)
//	}
//
//	protected fun generateSeriesData(): String {
//		val newSeries = ByteArray(seriesLength)
//		random.nextBytes(newSeries)
//		return String(Base64.encode(newSeries))
//	}
//
//	protected fun generateTokenData(): String {
//		val newToken = ByteArray(tokenLength)
//		random.nextBytes(newToken)
//		return String(Base64.encode(newToken))
//	}
//
//	override fun logout(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication?) {
//		super.logout(request, response, authentication)
//		val username = authentication!!.name
//		tokenRepository.removeToken(username)
//	}
//
//	companion object {
//
//		val DEFAULT_SERIES_LENGTH = 16
//		val DEFAULT_TOKEN_LENGTH = 16
//	}
//
//}
