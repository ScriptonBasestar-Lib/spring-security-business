package kr.scripton.business.core

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author archmagece
 * @project tool-jvm-parent
 * @since 2018-02-19 오후 11:13
 */
@Configuration
class TestConfig {
	@Bean
	EnvironmentStringPBEConfig EnvironmentVariablesConfiguration() {
		EnvironmentStringPBEConfig conf = new EnvironmentStringPBEConfig()
		conf.setAlgorithm("PBEWithMD5AndDES")
		conf.setPasswordEnvName("APP_ENCRYPTION_PASSWORD")
		return conf
	}
	@Bean
	StandardPBEStringEncryptor ConfigurationEncryptor() {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor()
		encryptor.setConfig(this.EnvironmentVariablesConfiguration())
		encryptor.setPassword("9pwc3dke")
		return encryptor
	}
}
