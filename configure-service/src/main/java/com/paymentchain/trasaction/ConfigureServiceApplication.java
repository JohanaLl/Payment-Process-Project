package com.paymentchain.trasaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigureServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigureServiceApplication.class, args);
	}

}
