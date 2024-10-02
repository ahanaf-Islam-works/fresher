package com.api_gateway.config.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

@Configuration
public class Routes {

    @Value("${explorer.url}")
    private String explorerUrl;

    @Value("${producer.url}")
    private String producerUrl;
    @Bean
    public RouterFunction<ServerResponse> explorerServiceRout() {
        return GatewayRouterFunctions.route("explorer_service")
                .route(RequestPredicates.path("/api/explorer"),
                        request -> (ServerResponse) HandlerFunctions.http(explorerUrl))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute"))) // Fallback route for circuit breaker
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> producerServiceRout() {
        return GatewayRouterFunctions.route("product_service")
                .route(RequestPredicates.path("/api/producer"),
                        request -> (ServerResponse) HandlerFunctions.http(producerUrl))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute"))) // Fallback route for circuit breaker
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return RouterFunctions.route(RequestPredicates.path("/fallbackRoute"),
                request -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fallback response: Service is unavailable."));
    }
}
