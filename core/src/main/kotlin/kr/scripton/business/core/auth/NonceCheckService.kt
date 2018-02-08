package kr.scripton.business.core.auth

interface NonceCheckService {
    fun isDuplicate(nonce:String):Boolean
}