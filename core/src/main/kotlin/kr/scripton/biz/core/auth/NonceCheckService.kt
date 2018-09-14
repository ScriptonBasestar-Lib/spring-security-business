package kr.scripton.biz.core.auth

/**
 * @author archmagece@gmail.com
 * @since 2018-02-08 오후 6:07
 */
interface NonceCheckService {
	fun isDuplicate(nonce: String): Boolean
}