package kr.scripton.business.direct.auth

import kr.scripton.business.core.auth.BusinessUser

/**
 * @author archmagece
 * @since 2017-10-30
 */
interface DirectUser<out USER_ID> : BusinessUser<USER_ID>
