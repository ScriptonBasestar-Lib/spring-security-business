package kr.scripton.biz.core.registery

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import java.util.concurrent.TimeUnit

/**
 * @author archmagece@gmail.com
 * @since 2018-02-08 오후 6:35
 */
class RedisNonceCheckService(val timeout: Long = 1, val timeunit: TimeUnit = TimeUnit.HOURS) : NonceCheckService {

	@Autowired
	private val redisBooleanTemplate: RedisTemplate<String, Boolean>? = null

	override fun isDuplicate(nonce: String): Boolean {
		if (redisBooleanTemplate!!.opsForSet().operations.hasKey(nonce)!!) {
			redisBooleanTemplate.expire(nonce, timeout, timeunit)
			return true
		}
		redisBooleanTemplate.opsForSet().add(nonce, true)
		redisBooleanTemplate.expire(nonce, timeout, timeunit)
		return false
	}
}
