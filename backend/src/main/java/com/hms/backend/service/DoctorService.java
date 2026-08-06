package com.hms.backend.service;

import java.util.List;

import com.hms.backend.dto.request.DoctorRequest;
import com.hms.backend.dto.response.DoctorResponse;

public interface DoctorService {

    DoctorResponse createDoctor(DoctorRequest request);

    List<DoctorResponse> getAllDoctors();

    DoctorResponse getDoctorById(Long id);

    DoctorResponse updateDoctor(Long id, DoctorRequest request);

    void deleteDoctor(Long id);

}