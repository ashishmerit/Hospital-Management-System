package com.hms.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.backend.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

    Optional<Doctor> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

}