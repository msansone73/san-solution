package br.com.msansone.sansecurity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import br.com.msansone.saneureka.SanEurekaApplication;

@SpringBootApplication
@EnableDiscoveryClient
public class SanSecurityApplication {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(SanSecurityApplication.class);
		logger.info("Started Eureka.");
		SpringApplication.run(SanSecurityApplication.class, args);
	}

}
