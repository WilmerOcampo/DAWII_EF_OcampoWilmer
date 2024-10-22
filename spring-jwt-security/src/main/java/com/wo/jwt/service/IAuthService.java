package com.wo.jwt.service;

import com.wo.jwt.model.*;
import com.wo.jwt.payload.request.LoginRequest;
import com.wo.jwt.payload.request.UserRequest;
import com.wo.jwt.payload.response.RegisterResponse;

public interface IAuthService {

    User login(LoginRequest request) throws Exception;

    RegisterResponse save(UserRequest request) throws Exception;

}
