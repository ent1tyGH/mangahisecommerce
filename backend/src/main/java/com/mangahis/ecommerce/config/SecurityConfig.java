package com.mangahis.ecommerce.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Value("${allowed.origins}")
    private String[] allowedOrigins;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Enable CORS
        http.cors()
                .and()
                .authorizeRequests()
                // Permit access to public API endpoints and Swagger UI
                .antMatchers("/api/product-category/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                // Require authentication for other endpoints, like orders
                .antMatchers("/api/orders/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();

        // Add content negotiation strategy
        http.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());

        // Configure a friendly 401 response for Okta integration
        Okta.configureResourceServer401ResponseBody(http);

        // Disable CSRF, as the app doesn’t use cookies for session tracking
        http.csrf().disable();

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(allowedOrigins)); // Use property for allowed origins
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Add necessary headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply to all endpoints
        return source;
    }
}
