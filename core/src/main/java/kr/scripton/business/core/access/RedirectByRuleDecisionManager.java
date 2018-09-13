package kr.scripton.business.core.access;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.List;

public class RedirectByRuleDecisionManager extends AbstractAccessDecisionManager {
	protected RedirectByRuleDecisionManager(List<AccessDecisionVoter<?>> decisionVoters) {
		super(decisionVoters);
	}

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

	}
}
