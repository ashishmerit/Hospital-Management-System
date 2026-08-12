package com.hms.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.hms.backend.dto.request.DoctorRequest;
import com.hms.backend.dto.response.DoctorResponse;
import com.hms.backend.service.DoctorService;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/doctors")
@Validated
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DoctorResponse> createDoctor(
            @Valid @RequestBody DoctorRequest request) {

        DoctorResponse response = doctorService.createDoctor(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @GetMapping
    public ResponseEntity<List<DoctorResponse>> getAllDoctors() {

        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> getDoctorById(
            @PathVariable Long id) {

        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponse> updateDoctor(
            @PathVariable Long id,
            @Valid @RequestBody DoctorRequest request) {

        return ResponseEntity.ok(
                doctorService.updateDoctor(id, request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(
            @PathVariable Long id) {

        doctorService.deleteDoctor(id);

        return ResponseEntity.noContent().build();
    }
}