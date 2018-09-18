package kr.scripton.biz.core.registery

import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import java.io.IOException
import java.util.concurrent.TimeUnit


class LocalNonceCheckService(cacheSize: Long = 10000, expirationTime: Long = 1, expirationTimeUnit: TimeUnit = TimeUnit.HOURS) : NonceCheckService {
	private val cache = CacheBuilder.newBuilder()
			.maximumSize(cacheSize)
			.expireAfterAccess(expirationTime, expirationTimeUnit)
			.recordStats()
			.build(object : CacheLoader<String, String>() {
				@Throws(IOException::class)
				override fun load(s: String): String {
					return s
				}
			})

	override fun isDuplicate(nonce: String): Boolean {
		return if (cache.asMap().containsKey(nonce)) {
			true
		} else {
			cache.get(nonce)
			false
		}
	}

}
