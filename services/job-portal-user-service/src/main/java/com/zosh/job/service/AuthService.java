package com.zosh.job.service;

import com.zosh.job.payload.AuthResponse;

public class AuthService {
    AuthResponse signup(SignupRequest req);
    AuthResponse login(LoginRequest req);
}
