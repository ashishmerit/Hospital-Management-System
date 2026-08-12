package com.hms.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hms.backend.dto.request.DoctorRequest;
import com.hms.backend.dto.response.DoctorResponse;
import com.hms.backend.entity.Doctor;
import com.hms.backend.exception.ResourceNotFoundException;
import com.hms.backend.repository.DoctorRepository;
import com.hms.backend.service.DoctorService;
import com.hms.backend.exception.ResourceNotFoundException;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public DoctorResponse createDoctor(DoctorRequest request) {

        if (doctorRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (doctorRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone already exists");
        }

        Doctor doctor = new Doctor();

        doctor.setFirstName(request.getFirstName());
        doctor.setLastName(request.getLastName());
        doctor.setEmail(request.getEmail());
        doctor.setPhone(request.getPhone());
        doctor.setSpecialization(request.getSpecialization());
        doctor.setQualification(request.getQualification());
        doctor.setExperienceYears(request.getExperienceYears());
        doctor.setConsultationFee(request.getConsultationFee());
        doctor.setDepartment(request.getDepartment());
        doctor.setAvailable(request.getAvailable());

        Doctor savedDoctor = doctorRepository.save(doctor);

        return mapToResponse(savedDoctor);
    }

    @Override
    public List<DoctorResponse> getAllDoctors() {

        return doctorRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorResponse getDoctorById(Long id) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        return mapToResponse(doctor);
    }

    @Override
    public DoctorResponse updateDoctor(Long id, DoctorRequest request) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        if (!doctor.getEmail().equals(request.getEmail())
                && doctorRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (!doctor.getPhone().equals(request.getPhone())
                && doctorRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone already exists");
        }

        doctor.setFirstName(request.getFirstName());
        doctor.setLastName(request.getLastName());
        doctor.setEmail(request.getEmail());
        doctor.setPhone(request.getPhone());
        doctor.setSpecialization(request.getSpecialization());
        doctor.setQualification(request.getQualification());
        doctor.setExperienceYears(request.getExperienceYears());
        doctor.setConsultationFee(request.getConsultationFee());
        doctor.setDepartment(request.getDepartment());
        doctor.setAvailable(request.getAvailable());

        Doctor updatedDoctor = doctorRepository.save(doctor);

        return mapToResponse(updatedDoctor);
    }

    @Override
    public void deleteDoctor(Long id) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctorRepository.delete(doctor);
    }

    private DoctorResponse mapToResponse(Doctor doctor) {

        DoctorResponse response = new DoctorResponse();

        response.setId(doctor.getId());
        response.setFirstName(doctor.getFirstName());
        response.setLastName(doctor.getLastName());
        response.setEmail(doctor.getEmail());
        response.setPhone(doctor.getPhone());
        response.setSpecialization(doctor.getSpecialization());
        response.setQualification(doctor.getQualification());
        response.setExperienceYears(doctor.getExperienceYears());
        response.setConsultationFee(doctor.getConsultationFee());
        response.setDepartment(doctor.getDepartment());
        response.setAvailable(doctor.getAvailable());

        return response;
    }
}