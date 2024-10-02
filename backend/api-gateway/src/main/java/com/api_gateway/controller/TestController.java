package com.api_gateway.controller;

import com.api_gateway.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.api_gateway.dto.UserContext;
import com.api_gateway.service.auth.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final JwtService jwtService;
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<UserContext> test() {
        UserContext authentication = (UserContext) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return ResponseEntity.ok(authentication);
    }

    @GetMapping("/producer")
    public String getProducerTest(){
        return authService.testRestClient();
    }

}