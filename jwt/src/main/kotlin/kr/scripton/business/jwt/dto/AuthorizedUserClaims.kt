package kr.scripton.business.jwt.dto

import lombok.Getter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.SpringSecurityCoreVersion
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.util.Assert
import java.io.Serializable
import java.util.*

/**
 * @author archmagece
 * @since 2018-03-24 오전 12:22
 * @at spring-business-wrapper
 */
class AuthorizedUserClaims : UserDetails {

	val USER_ID = "uid"
	val USER_NICKNAME = "nnm"
	val USER_USERNAME = "unm"
	//	public static final String USER_COMPONENT = "usc";
	val USER_ROLE = "uro"
	val USER_PRINCIPAL = "upr"
	//claims에는 포함하지 않고 사이트 파라미터용으로
	@Getter//override
	protected val authorities: Set<GrantedAuthority>
	@Getter//override
	protected val enabled: Boolean
	@Getter//override
	protected val accountNonExpired: Boolean
	@Getter//override
	protected val credentialsNonExpired: Boolean
	@Getter//override
	protected val accountNonLocked: Boolean
	@Getter//override
	private val password: String? = null


	fun SBAuthorizedUserClaims(
			userId: Long?,
			nickname: String,
			username: String?,
			userRoles: Set<String>,
			enabled: Boolean,
			accountNonExpired: Boolean,
			credentialsNonExpired: Boolean,
			accountNonLocked: Boolean,
			authorities: Collection<GrantedAuthority>): ??? {
		if (username == null || "" == username) {
			throw IllegalArgumentException(
					"Cannot pass null or empty values to constructor")
		}
		setUserId(userId!!)
		setNickname(nickname)
		setUsername(username)
		setUserRoles(userRoles)
		this.enabled = enabled
		this.accountNonExpired = accountNonExpired
		this.credentialsNonExpired = credentialsNonExpired
		this.accountNonLocked = accountNonLocked
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities))
	}

	fun SBAuthorizedUserClaims(
			userId: Long?,
			nickname: String,
			username: String?,
			userRoles: Set<String>,
			enabled: Boolean,
			accountNonExpired: Boolean,
			credentialsNonExpired: Boolean,
			accountNonLocked: Boolean): ??? {
		if (username == null || "" == username) {
			throw IllegalArgumentException(
					"Cannot pass null or empty values to constructor")
		}
		setUserId(userId!!)
		setNickname(nickname)
		setUsername(username)
		setUserRoles(userRoles)
		this.enabled = enabled
		this.accountNonExpired = accountNonExpired
		this.credentialsNonExpired = credentialsNonExpired
		this.accountNonLocked = accountNonLocked
		this.authorities = Collections.EMPTY_SET
	}

	fun SBAuthorizedUserClaims(
			claims: Claims,
			enabled: Boolean,
			accountNonExpired: Boolean,
			credentialsNonExpired: Boolean,
			accountNonLocked: Boolean): ??? {
		super(claims)
		if (claims.get(USER_USERNAME) == null || "" == claims.get(USER_USERNAME)) {
			throw IllegalArgumentException(
					"Cannot pass null or empty values to constructor")
		}
		this.enabled = enabled
		this.accountNonExpired = accountNonExpired
		this.credentialsNonExpired = credentialsNonExpired
		this.accountNonLocked = accountNonLocked
		this.authorities = Collections.EMPTY_SET
	}

	//user id
	fun getUserId(): Long? {
		return get(USER_ID, Long::class.java)
	}

	protected fun setUserId(userId: Long) {
		setValue(USER_ID, userId)
	}

	//user nickname
	fun getNickname(): String {
		return getString(USER_NICKNAME)
	}

	protected fun setNickname(nickname: String) {
		setValue(USER_NICKNAME, nickname)
	}

	//user username
	override fun getUsername(): String {
		return getString(USER_USERNAME)
	}

	protected fun setUsername(username: String) {
		setValue(USER_USERNAME, username)
	}

	//user roles
	fun getUserRoles(): Collection<String>? {
		return get<Collection<*>>(USER_ROLE, Collection<*>::class.java)
	}

	protected fun setUserRoles(userRoles: Collection<String>) {
		setValue(USER_ROLE, userRoles)
	}

//	//user principals
//	public Collection<String> getUserPrincipals() {
//		return get(USER_PRINCIPAL, Collection.class);
//	}
//
//	protected void setUserPrincipals(Collection<String> userRoles) {
//		setValue(USER_PRINCIPAL, userRoles);
//	}

	operator fun <T> get(claimName: String, requiredType: Class<T>): T? {
		var value = get(claimName) ?: return null

		if (Claims.EXPIRATION.equals(claimName) ||
				Claims.ISSUED_AT.equals(claimName) ||
				Claims.NOT_BEFORE.equals(claimName)) {
			value = getDate(claimName)
		}

		if (requiredType == Date::class.java && value is Long) {
			value = Date(value as Long)
		} else if (requiredType == Long::class.java) {
			value = java.lang.Long.parseLong(value.toString())
		}

		if (!requiredType.isInstance(value)) {
			throw RequiredTypeException("Expected value to be of type: " + requiredType + ", but was " + value.javaClass)
		}

		return requiredType.cast(value)
	}

	private fun sortAuthorities(
			authorities: Collection<GrantedAuthority>): SortedSet<GrantedAuthority> {
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection")
		// Ensure array iteration order is predictable (as per
		// UserDetails.getAuthorities() contract and SEC-717)
		val sortedAuthorities = TreeSet(AuthorityComparator())

		for (grantedAuthority in authorities) {
			Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements")
			sortedAuthorities.add(grantedAuthority)
		}

		return sortedAuthorities
	}

	private class AuthorityComparator : Comparator<GrantedAuthority>, Serializable {

		override fun compare(g1: GrantedAuthority, g2: GrantedAuthority): Int {
			// Neither should ever be null as each entry is checked before adding it to the set.
			// If the findGrantedAuthority is null, it is a custom findGrantedAuthority and should precede others.
			if (g2.authority == null) {
				return -1
			}

			return if (g1.authority == null) {
				1
			} else g1.authority.compareTo(g2.authority)

		}

		companion object {
			private const val serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID
		}
	}

}