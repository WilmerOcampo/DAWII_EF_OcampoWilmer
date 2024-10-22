package com.wo.jwt.payload.request;

import com.wo.jwt.model.enums.UserRole;

public record UserRequest(Long id, String fullName, String email, String password, UserRole role) {
}
