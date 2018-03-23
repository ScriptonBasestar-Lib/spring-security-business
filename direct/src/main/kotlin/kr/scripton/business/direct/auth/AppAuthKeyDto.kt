package kr.scripton.business.direct.auth

import java.io.Serializable

/**
 * @author archmagece
 * @since 2015-01-26-11
 */
class AppAuthKeyDto : Serializable {
	private val username: String? = null
	private val secret: String? = null
	private val expirationTime: Long? = null
}
