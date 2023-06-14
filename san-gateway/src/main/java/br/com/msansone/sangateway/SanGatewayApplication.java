package br.com.msansone.sangateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SanGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SanGatewayApplication.class, args);
	}

}
