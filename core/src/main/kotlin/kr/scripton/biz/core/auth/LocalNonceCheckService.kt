package kr.scripton.biz.core.auth

class LocalNonceCheckService : NonceCheckService {
	private val set = HashSet<String>()
	override fun isDuplicate(nonce: String): Boolean {
		if (set.contains(nonce)) {
			return false
		}
		set.add(nonce)
		return true
	}

}