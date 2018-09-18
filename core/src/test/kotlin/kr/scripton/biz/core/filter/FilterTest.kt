package kr.scripton.biz.core.filter

import kr.scripton.biz.core.exception.NoAuthorityException
import kr.scripton.biz.core.exception.NoParameterException
import org.junit.Before
import org.junit.Test
import org.springframework.mock.web.MockFilterChain
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import java.security.MessageDigest
import java.util.*

class FilterTest {

	private lateinit var filter: GlobalNonceCheckFilter
	private val md = MessageDigest.getInstance("MD5")

	@Before
	fun before() {
		//given all
		filter = GlobalNonceCheckFilter()
		filter.setBeanName("testNonceFilter")
	}

	@Test(expected = NoParameterException::class)
	fun `nonceFilterTest - empty header`() {
		//given
		val mockChain = MockFilterChain()
		val req = MockHttpServletRequest("POST", "/resource/dev/file")
		val rsp = MockHttpServletResponse()

		//when

		//then
		filter.doFilter(req, rsp, mockChain)
//		assertEquals("/maintenance.jsp", rsp.getLastRedirect())
	}

	@Test(expected = NoAuthorityException::class)
	fun `nonceFilterTest - nonce duplicate`() {
		//given
		val mockChain1 = MockFilterChain()
		val req1 = MockHttpServletRequest("POST", "/resource/dev/file")
		val rsp1 = MockHttpServletResponse()
		val mockChain2 = MockFilterChain()
		val req2 = MockHttpServletRequest("POST", "/resource/dev/file")
		val rsp2 = MockHttpServletResponse()

		val randomNonce = md.digest(Random().nextInt().toString().toByteArray())
		req1.addHeader(GlobalNonceCheckFilter.DEFAULT_NONCE_NAME, randomNonce)
		req2.addHeader(GlobalNonceCheckFilter.DEFAULT_NONCE_NAME, randomNonce)

		//when

		//then
		filter.doFilter(req1, rsp1, mockChain1)
		filter.doFilter(req2, rsp2, mockChain2)
//		assertEquals("/maintenance.jsp", rsp.getLastRedirect())
	}

	@Test
	fun `nonceFilterTest - works fines`() {
		//given
		val mockChain1 = MockFilterChain()
		val req1 = MockHttpServletRequest("POST", "/resource/dev/file")
		val rsp1 = MockHttpServletResponse()

		val randomNonce = md.digest(Random().nextInt().toString().toByteArray())
		req1.addHeader(GlobalNonceCheckFilter.DEFAULT_NONCE_NAME, randomNonce)

		//when

		//then
		filter.doFilter(req1, rsp1, mockChain1)
//		assertEquals("/maintenance.jsp", rsp.getLastRedirect())
	}
}
