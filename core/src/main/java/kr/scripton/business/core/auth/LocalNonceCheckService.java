package kr.scripton.business.core.auth;

import java.util.HashSet;

public class LocalNonceCheckService implements NonceCheckService {
	private HashSet<String> set = new HashSet<>();

	@Override
	public Boolean isDuplicate(String nonce) {
		if (set.contains(nonce)) {
			return false;
		}
		set.add(nonce);
		return true;
	}
}