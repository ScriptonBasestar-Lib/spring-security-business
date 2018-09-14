package kr.scripton.biz.core.common

import org.springframework.security.core.Authentication
import org.springframework.security.web.FilterInvocation
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot

class CustomWebSecurityExpressionRoot(authentication: Authentication, filterInvocation: FilterInvocation) : WebSecurityExpressionRoot(authentication, filterInvocation) {

}