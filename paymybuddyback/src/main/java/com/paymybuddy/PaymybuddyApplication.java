package com.paymybuddy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SecurityProperties.class)
public class PaymybuddyApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PaymybuddyApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("////////// Click to access the app : http://localhost:8080/swagger-ui/index.html  ///////////");
	}
}
