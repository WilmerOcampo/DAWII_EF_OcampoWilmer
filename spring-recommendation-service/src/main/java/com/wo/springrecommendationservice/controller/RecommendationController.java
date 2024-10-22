package com.wo.springrecommendationservice.controller;

import com.wo.springrecommendationservice.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recommendation")
public class RecommendationController {

    private final JwtUtil jwtUtil;

    @Autowired
    public RecommendationController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/")
    public ResponseEntity<?> hello(@RequestHeader("Authorization") String bearerToken) {
        try {
            jwtUtil.isTokenValid(bearerToken);
            return ResponseEntity.ok("Hello, this is MS Recommendation!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

}
