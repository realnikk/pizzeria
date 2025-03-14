package com.example.apigateway.config;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {

//    public static final List<String> openApiEndpoints = List.of(
//            "/user",
//            "/auth",
//            "/eureka"
//    );

    public static final Map<String, List<HttpMethod>> openApiEndpoints = Map.of(
            "/user", List.of(HttpMethod.POST),
            "/auth", List.of(HttpMethod.POST),
            "/category", List.of(HttpMethod.GET),
            "/product", List.of(HttpMethod.GET)
    );

//    public static final List<String> apiEndpointsForAdmin = List.of(
//            "/product"
//    );

    public static final Map<String, List<HttpMethod>> adminApiEndpoints = Map.of(
            "/product", List.of(HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE),
            "/category", List.of(HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE)
    );

//    public Predicate<ServerHttpRequest> isSecured =
//            request -> openApiEndpoints
//                    .stream()
//                    .noneMatch(uri -> request.getURI().getPath().startsWith(uri));

//    public Predicate<ServerHttpRequest> isForAdmin =
//            request -> apiEndpointsForAdmin
//                    .stream()
//                    .allMatch(uri -> request.getURI().getPath().contains(uri));
    public Predicate<ServerHttpRequest> isSecured =
        request -> openApiEndpoints.entrySet()
                .stream()
                .noneMatch(uri -> request.getURI().getPath().startsWith(uri.getKey()) &&
                        uri.getValue().contains(request.getMethod()));

    public Predicate<ServerHttpRequest> isForAdmin =
        request -> adminApiEndpoints.entrySet()
                .stream()
                .anyMatch(uri -> request.getURI().getPath().startsWith(uri.getKey()) &&
                        uri.getValue().contains(request.getMethod()));

}

