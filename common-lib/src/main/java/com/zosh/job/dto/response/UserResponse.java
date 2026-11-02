package com.zosh.job.dto.response;

import com.zosh.job.domain.UserRole;
import com.zosh.job.domain.UserStatus;

import java.time.LocalDateTime;

public class UserResponse {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String profileImage;
    private UserRole role;
    private UserStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime lastLong;

}
