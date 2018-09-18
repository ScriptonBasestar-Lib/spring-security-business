package kr.scripton.biz.core.util

import org.junit.Before
import org.junit.Test
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

//https://www.programcreek.com/java-api-examples/?class=org.springframework.mock.web.MockHttpServletRequest&method=setCookies
class CookieUtilTest {

	lateinit var req: HttpServletRequest
	lateinit var rsp: HttpServletResponse

	@Before
	fun before(){
		req = MockHttpServletRequest("POST", "/resource/dev/file")
		rsp = MockHttpServletResponse()
	}

	@Test
	fun toCookieTest(){
		CookieUtil.toCookie(rsp, "polypia.net", "SERVICE_NAME", "aaa")
		println(CookieUtil.fromCookie(req, "SERVICE_NAME"))
	}

}