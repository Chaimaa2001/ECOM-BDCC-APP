package com.example.gateway1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Gateway1Application {

    public static void main(String[] args) {
        SpringApplication.run(Gateway1Application.class, args);
    }

   //@Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r->r.path("/api/products/**").uri("lb://INVENTORY-SERVICE"))
                .route(r->r.path("/api/orders/**").uri("lb://ORDER-SERVICE"))
                .build();
    }
    @Bean
    public DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc,
                                                               DiscoveryLocatorProperties dlp ){
        return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
    }
}
