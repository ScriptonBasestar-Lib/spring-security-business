package kr.scripton.business.jwt.util

/**
 * @author archmagece
 * @since 2018-03-24 오전 12:26
 * @at spring-business-wrapper
 */
object JwtUtil {

	fun generateToken(signingKey: String, claims: Claims): String {
		return Jwts.builder().signWith(SignatureAlgorithm.HS256, signingKey)
				.setClaims(claims)
				.compact()
	}

	fun getBody(signingKey: String, token: String): SBAuthorizedUserClaims {
		val claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody()
		return SBAuthorizedUserClaims(claims, true, true, true, true)
	}

	fun getBody(signingKey: String, JwtHandler: SBJwtHandler, token: String): SBAuthorizedUserClaims {
		val claims = Jwts.parser().setSigningKey(signingKey).parse(token, JwtHandler)
		return SBAuthorizedUserClaims(claims, true, true, true, true)
	}

}