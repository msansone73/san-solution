package br.com.msansone.saneureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SanEurekaApplication {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(SanEurekaApplication.class);
		logger.info("Started Eureka.");
		SpringApplication.run(SanEurekaApplication.class, args);
	}

}
