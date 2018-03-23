//package kr.scripton.business.core.rememberme
//
///**
// * @author archmagece
// * @since 2018-03-24 오전 12:30
// * @at spring-business-wrapper
// */
//class RedisRememberMeTokenRepository : RememberMeRepository {
//
//	private val redisTemplate: RedisTemplate<String, RememberMeUser>
//	private val REDIS_KEY: String
//
//	fun RedisRememberMeTokenRepository(redisTemplate: RedisTemplate<String, RememberMeUser>): ??? {
//		this.REDIS_KEY = "REDIS_REMEMBER_ME_KEY"
//		this.redisTemplate = redisTemplate
//	}
//
//	fun RedisRememberMeTokenRepository(redisKey: String, redisTemplate: RedisTemplate<String, RememberMeUser>): ??? {
//		this.REDIS_KEY = redisKey
//		this.redisTemplate = redisTemplate
//	}
//
//	override fun exists(key: String): Boolean {
//		val current = this.redisTemplate.opsForHash().get(REDIS_KEY, key) as RememberMeUser
//		return if (current == null) true else false
//	}
//
//	override fun storeToken(key: String, token: RememberMeUser) {
//		this.redisTemplate.opsForHash().put(REDIS_KEY, key, token)
//	}
//
//	override fun findToken(key: String): RememberMeUser {
//		return this.redisTemplate.opsForHash().get(REDIS_KEY, key) as RememberMeUser
//	}
//
//	override fun removeToken(key: String) {
//		this.redisTemplate.opsForHash().delete(REDIS_KEY, key)
//	}
//
//	override fun dumpAll() {
//		this.redisTemplate.delete(REDIS_KEY)
//	}
//
//}