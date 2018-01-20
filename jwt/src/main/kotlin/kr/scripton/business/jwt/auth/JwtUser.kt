package kr.scripton.business.jwt.auth

import com.auth0.jwt.interfaces.Claim
import kr.scripton.business.core.auth.BusinessUser

interface JwtUser<out USER_ID> : Claim, BusinessUser<USER_ID>