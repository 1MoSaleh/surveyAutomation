package com.MoAlanazi.cst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {ReactiveSecurityAutoConfiguration.class})
@EnableScheduling
public class CstApplication {

	public static void main(String[] args) {
		SpringApplication.run(CstApplication.class, args);
	}

}
