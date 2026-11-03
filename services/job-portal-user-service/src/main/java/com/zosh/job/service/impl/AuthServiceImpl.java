package com.zosh.job.service.impl;

import com.zosh.job.domain.UserRole;
import com.zosh.job.model.User;
import com.zosh.job.payload.AuthResponse;
import com.zosh.job.payload.LoginRequest;
import com.zosh.job.payload.SignupRequest;
import com.zosh.job.repository.UserRepository;
import com.zosh.job.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public AuthResponse signup(SignupRequest req) throws Exception{

        if(userRepository.existsByEmail(req.getEmail()))
        {
            throw new Exception("Email Already Registered: "+ req.getEmail());
        }

        if(req.getRole()== UserRole.ROLE_ADMIN)
        {
            throw new Exception("cannot self register as role admin");
        }

        User user = User.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .password(req.getPassword())
                .role(req.getRole())
                .phone(req.getPhone())
                .lastLong(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);

        AuthResponse res =new AuthResponse();
        res.setTitle("Welcome "+savedUser.getFullName());
        res.setMessage("Registered Successfully");
        res.setJwt("jwt");
        res.setUser();
        return null;
    }

    @Override
    public AuthResponse login(LoginRequest req) {
        return null;
    }
}
