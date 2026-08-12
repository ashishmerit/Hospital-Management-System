package com.hms.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hms.backend.dto.request.PatientProfileRequest;
import com.hms.backend.dto.response.PatientProfileResponse;
import com.hms.backend.entity.PatientProfile;
import com.hms.backend.entity.User;
import com.hms.backend.exception.ResourceNotFoundException;
import com.hms.backend.repository.PatientProfileRepository;
import com.hms.backend.repository.UserRepository;
import com.hms.backend.service.PatientProfileService;

@Service
public class PatientProfileServiceImpl implements PatientProfileService {

    private final PatientProfileRepository profileRepository;
    private final UserRepository userRepository;

    public PatientProfileServiceImpl(
            PatientProfileRepository profileRepository,
            UserRepository userRepository) {

        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PatientProfileResponse createProfile(PatientProfileRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (profileRepository.findByUserId(user.getId()).isPresent()) {
            throw new RuntimeException("Profile already exists");
        }

        PatientProfile profile = new PatientProfile();

        profile.setUser(user);
        profile.setDateOfBirth(request.getDateOfBirth());
        profile.setGender(request.getGender());
        profile.setBloodGroup(request.getBloodGroup());
        profile.setAddress(request.getAddress());
        profile.setEmergencyContact(request.getEmergencyContact());
        profile.setAllergies(request.getAllergies());
        profile.setChronicConditions(request.getChronicConditions());

        return map(profileRepository.save(profile));
    }

    @Override
    public List<PatientProfileResponse> getAllProfiles() {

        return profileRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public PatientProfileResponse getProfile(Long id) {

        PatientProfile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        return map(profile);
    }

    @Override
    public PatientProfileResponse updateProfile(Long id,
            PatientProfileRequest request) {

        PatientProfile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        profile.setUser(user);
        profile.setDateOfBirth(request.getDateOfBirth());
        profile.setGender(request.getGender());
        profile.setBloodGroup(request.getBloodGroup());
        profile.setAddress(request.getAddress());
        profile.setEmergencyContact(request.getEmergencyContact());
        profile.setAllergies(request.getAllergies());
        profile.setChronicConditions(request.getChronicConditions());

        return map(profileRepository.save(profile));
    }

    @Override
    public void deleteProfile(Long id) {

        PatientProfile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profileRepository.delete(profile);
    }

    private PatientProfileResponse map(PatientProfile profile) {

        PatientProfileResponse response = new PatientProfileResponse();

        response.setId(profile.getId());
        response.setUserId(profile.getUser().getId());
        response.setPatientName(
                profile.getUser().getFirstName() + " "
                        + profile.getUser().getLastName());

        response.setDateOfBirth(profile.getDateOfBirth());
        response.setGender(profile.getGender());
        response.setBloodGroup(profile.getBloodGroup());
        response.setAddress(profile.getAddress());
        response.setEmergencyContact(profile.getEmergencyContact());
        response.setAllergies(profile.getAllergies());
        response.setChronicConditions(profile.getChronicConditions());

        return response;
    }
}