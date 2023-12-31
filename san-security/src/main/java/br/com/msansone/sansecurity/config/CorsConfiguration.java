package br.com.msansone.sansecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			public void addCorsMappings(CorsRegistry corsRegistry) {
				corsRegistry.addMapping("/**")
					.allowedOrigins("*")
					.allowedMethods("*")
					.allowedHeaders("*")
					.maxAge(3600);
			}
		};
		
	}
	
}
