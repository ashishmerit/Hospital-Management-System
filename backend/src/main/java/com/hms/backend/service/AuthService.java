package com.hms.backend.service;

import com.hms.backend.dto.request.LoginRequest;
import com.hms.backend.dto.request.RegisterRequest;
import com.hms.backend.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

}