package br.com.msansone.sangateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SanGatewayApplication {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(SanGatewayApplication.class);
		logger.info("Started Eureka.");
		SpringApplication.run(SanGatewayApplication.class, args);
	}

}
