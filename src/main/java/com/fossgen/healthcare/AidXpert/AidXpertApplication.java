package com.fossgen.healthcare.AidXpert;

import java.net.UnknownHostException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class AidXpertApplication {

	public static void main(String[] args) {
		//SpringApplication.run(AidXpertApplication.class, args);
		new SpringApplicationBuilder(AidXpertApplication.class)
        .profiles("dev")
        .run(args);
	}

	/*
	 * For rest template bean
	 */
	@Bean
	public RestTemplate restTemplate() throws UnknownHostException {
		return new RestTemplate();
	}

}
