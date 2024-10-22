package com.wo.jwt.payload.response;

public record LoginResponse(String token, long expiration) {
}
