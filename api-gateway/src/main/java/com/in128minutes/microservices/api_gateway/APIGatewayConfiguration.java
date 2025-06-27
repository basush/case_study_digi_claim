package com.in128minutes.microservices.api_gateway;

import com.in128minutes.microservices.api_gateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIGatewayConfiguration {

    @Autowired
    AuthenticationFilter authenticationFilter;
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){

        GatewayFilter gatewayFilter = authenticationFilter.apply(new AuthenticationFilter.Config());

        return builder.routes()
                .route(p->p.path("/get")
                    .filters(f->f
                            .addRequestHeader("MyHeader","MyHeaderValue")
                            .addRequestHeader("MyParam","MyParamValue"))
                    .uri("http://httpbin.org:80"))
                .route(p->p.path("/digi-claim/**")
                        .filters(f->f.filter(gatewayFilter))
                        .uri("lb://digi-claim-service"))
                .route(p->p.path("/auth/**")
                        .uri("lb://auth-service"))
                .build();
    }

}
