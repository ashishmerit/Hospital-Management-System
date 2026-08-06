package com.hms.backend.service;

import java.util.List;

import com.hms.backend.dto.request.PatientProfileRequest;
import com.hms.backend.dto.response.PatientProfileResponse;

public interface PatientProfileService {

    PatientProfileResponse createProfile(PatientProfileRequest request);

    List<PatientProfileResponse> getAllProfiles();

    PatientProfileResponse getProfile(Long id);

    PatientProfileResponse updateProfile(Long id, PatientProfileRequest request);

    void deleteProfile(Long id);

}