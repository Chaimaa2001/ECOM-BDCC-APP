package com.example.gateway1.securityCORS;

import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebFluxSecurity // Correctement utiliser @EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, GatewayProperties gatewayProperties) {
        ServerHttpSecurity.AuthorizeExchangeSpec authorizeExchange = http.authorizeExchange();

        gatewayProperties.getRoutes().forEach(route -> {
            route.getPredicates().forEach(predicate -> {
                if (predicate.getName().equalsIgnoreCase("Path")) {
                    String path = predicate.getArgs().values().stream().findFirst().orElse(null);
                    if (path != null) {
                        authorizeExchange.pathMatchers(path).permitAll();
                    }
                }
            });
        });

        authorizeExchange.anyExchange().permitAll();
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }
}