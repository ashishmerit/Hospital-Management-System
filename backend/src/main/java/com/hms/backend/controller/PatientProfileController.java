package com.hms.backend.controller;

import com.hms.backend.dto.request.PatientProfileRequest;
import com.hms.backend.dto.response.PatientProfileResponse;
import com.hms.backend.service.PatientProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/patient-profiles")
public class PatientProfileController {

    private final PatientProfileService patientProfileService;

    public PatientProfileController(PatientProfileService patientProfileService) {
        this.patientProfileService = patientProfileService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientProfileResponse create(
            @Valid @RequestBody PatientProfileRequest request) {

        return patientProfileService.createProfile(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @GetMapping
    public List<PatientProfileResponse> getAll() {
        return patientProfileService.getAllProfiles();
    }

    @PreAuthorize("hasAnyRole('PATIENT', 'ADMIN')")
    @GetMapping("/{id}")
    public PatientProfileResponse getById(@PathVariable Long id) {
        return patientProfileService.getProfile(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public PatientProfileResponse update(
            @PathVariable Long id,
            @Valid @RequestBody PatientProfileRequest request) {

        return patientProfileService.updateProfile(id, request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        patientProfileService.deleteProfile(id);
    }
}