package com.paymentchain.trasaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class TrasactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrasactionServiceApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalancerWebClientbuilder() {
		return WebClient.builder();
	}

}
