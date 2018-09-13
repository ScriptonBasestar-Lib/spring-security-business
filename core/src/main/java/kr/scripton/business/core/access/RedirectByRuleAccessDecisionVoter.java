package kr.scripton.business.core.access;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import java.util.Collection;

public class RedirectByRuleAccessDecisionVoter<S> implements AccessDecisionVoter<S> {
	@Override
	public boolean supports(ConfigAttribute attribute) {
		return false;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public int vote(Authentication authentication, S object, Collection<ConfigAttribute> attributes) {
		return 0;
	}
}
