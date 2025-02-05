package com.techstart.jobportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/resume/upload")
                        // Allow your specific frontend URL
                        .allowedOrigins("*")
                        // Allow POST and preflight OPTIONS requests
                        .allowedMethods("POST", "OPTIONS")
                        // Allow headers required for file upload (e.g., Content-Type)
                        .allowedHeaders("Content-Type")
                        // Disable credentials unless you need cookies/auth
                        .allowCredentials(false);
            }
        };
    }
}