package br.com.msansone.sansecurity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableMethodSecurity
public class BasicConfiguration {
	
	
	@Value("${springSecurityUser}")
	private String springSecurityUser;

	@Value("${springSecurityUserPass}")
	private String springSecurityUserPass;	
	
	@Value("${springSecurityUserAdmin}")
	private String springSecurityUserAdmin;
	
	@Value("${springSecurityUserAdminPass}")
	private String springSecurityUserAdminPass;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    
        /*
         http.csrf((csrf)-> csrf.disable())
         	.cors(  (cors) -> cors.configurationSource(corsConfigurationSource()))
        	.authorizeHttpRequests( 
        			(autoriza) -> autoriza.anyRequest().authenticated()
        			).httpBasic(Customizer.withDefaults());
        
       */

    	/*
        http.csrf((csrf)-> csrf.disable())
     	.cors(  (cors) -> cors.configurationSource(corsConfigurationSource()));
*/
/*
        http.csrf((csrf)-> csrf.disable())
     	.cors(  (cors) -> cors.disable())
     	.authorizeHttpRequests( 
    			(autoriza) -> autoriza.anyRequest().authenticated()
    			).httpBasic(Customizer.withDefaults());
*/

        //http.csrf((csrf)-> csrf.disable());

    	http.csrf((csrf)-> csrf.disable())        	
    		.authorizeHttpRequests( 
    			(autoriza) -> autoriza.anyRequest().authenticated()
    			).httpBasic(Customizer.withDefaults());

    	
    	return http.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
    	
    	UserDetails cliente = User.builder()
    			.username(springSecurityUser)
    			.password(passwordEncoder().encode(springSecurityUserPass))
    			.roles("USER")
    			.build();
    	
    	UserDetails admin = User.builder()
    			.username(springSecurityUserAdmin)
    			.password(passwordEncoder().encode(springSecurityUserAdminPass))
    			.roles("ADMIN")
    			.build();
    	
    	return new InMemoryUserDetailsManager(cliente, admin);
    }

    @Bean
    static PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        configuration.addAllowedOrigin("*"); // Adicione o domínio do seu site Angular
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        
        
        return source;
    }
    
}
