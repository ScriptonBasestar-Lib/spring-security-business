package kr.scripton.business.jwt.exception

class RequiredTypeException : RuntimeException{
	constructor(msg: String) : super(msg)
	constructor(msg: String, t: Throwable) : super(msg, t)
}
