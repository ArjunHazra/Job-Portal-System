package com.zosh.job.service.impl;

import com.zosh.job.domain.UserRole;
import com.zosh.job.domain.UserStatus;
import com.zosh.job.mapper.UserMapper;
import com.zosh.job.model.User;
import com.zosh.job.payload.AuthResponse;
import com.zosh.job.payload.LoginRequest;
import com.zosh.job.payload.SignupRequest;
import com.zosh.job.repository.UserRepository;
import com.zosh.job.security.JwtProvider;
import com.zosh.job.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

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
                .lastLogin(LocalDateTime.now())
                .status(UserStatus.ACTIVE)
                .build();

        User savedUser = userRepository.save(user);


        Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt= jwtProvider.generateToken(auth,savedUser.getId());

        AuthResponse res =new AuthResponse();
        res.setTitle("Welcome "+savedUser.getFullName());
        res.setMessage("Registered Successfully");
        res.setJwt(jwt);
        res.setUser(UserMapper.toDTO(savedUser));
        return res;//changed
    }

    @Override
    public AuthResponse login(LoginRequest req) {
        return null;
    }
}
