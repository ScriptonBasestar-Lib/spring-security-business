package kr.scripton.business.core.auth;

/**
 * @author archmagece@gmail.com
 * @since 2018-02-08 오후 6:07
 */
public interface NonceCheckService {
    Boolean isDuplicate(String nonce);
}