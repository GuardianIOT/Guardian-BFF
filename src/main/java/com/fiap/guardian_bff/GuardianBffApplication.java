package com.fiap.guardian_bff;

import com.fiap.guardian_bff.config.DisableSSLCertificateValidation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
@EnableFeignClients
public class GuardianBffApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {
		DisableSSLCertificateValidation.disableSslVerification();

		SpringApplication.run(GuardianBffApplication.class, args);
	}

}
