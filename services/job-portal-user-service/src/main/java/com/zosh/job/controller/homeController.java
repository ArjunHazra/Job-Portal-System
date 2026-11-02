package com.zosh.job.controller;

import com.zosh.job.domain.UserRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {

    @GetMapping()
    public String homeController()
    {
        return "My name is Arjun--------->"+ UserRole.ROLE_JOB_SEEKER;
    }
}
