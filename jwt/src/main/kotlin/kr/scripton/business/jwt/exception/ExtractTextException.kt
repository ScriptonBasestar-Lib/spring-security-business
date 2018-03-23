package kr.scripton.business.jwt.exception


//AuthenticationException
class ExtractTextException : RuntimeException{
	constructor(msg: String) : super(msg)
	constructor(msg: String, t: Throwable) : super(msg, t)
}
