package com.example.app.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static org.springframework.boot.SpringApplication.*;

@SpringBootApplication
@EnableJpaAuditing
@Configuration
//@EnableAutoConfiguration
public class MobileAppWsApplication {

	public static void main(String[] args) {
		run(MobileAppWsApplication.class, args);
	}

}
