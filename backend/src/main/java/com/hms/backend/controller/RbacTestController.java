package com.hms.backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rbac")
public class RbacTestController {

    @GetMapping("/patient")
    @PreAuthorize("hasRole('PATIENT')")
    public String patientAccess() {
        return "PATIENT access granted";
    }

    @GetMapping("/doctor")
    @PreAuthorize("hasRole('DOCTOR')")
    public String doctorAccess() {
        return "DOCTOR access granted";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "ADMIN access granted";
    }
}