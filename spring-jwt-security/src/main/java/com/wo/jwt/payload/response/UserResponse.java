package com.wo.jwt.payload.response;

import com.wo.jwt.model.User;

public record UserResponse(Long id, String fullName, String email, String role) {
    public UserResponse(User user) {
        this(user.getId(), user.getFullName(), user.getEmail(), String.valueOf(user.getRole()));
    }
}
