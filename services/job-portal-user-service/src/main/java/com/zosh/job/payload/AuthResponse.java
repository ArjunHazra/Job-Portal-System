package com.zosh.job.payload;

import com.zosh.job.dto.response.UserResponse;

public class AuthResponse {

    private String jwt;
    private String title;
    private String message;
    private UserResponse user;
}
