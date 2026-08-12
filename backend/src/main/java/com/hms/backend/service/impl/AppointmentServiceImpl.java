package com.hms.backend.service.impl;

import com.hms.backend.dto.request.AppointmentRequest;
import com.hms.backend.dto.response.AppointmentResponse;
import com.hms.backend.entity.Appointment;
import com.hms.backend.entity.AppointmentStatus;
import com.hms.backend.entity.Doctor;
import com.hms.backend.entity.User;
import com.hms.backend.exception.ResourceNotFoundException;
import com.hms.backend.repository.AppointmentRepository;
import com.hms.backend.repository.DoctorRepository;
import com.hms.backend.repository.UserRepository;
import com.hms.backend.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    public AppointmentServiceImpl(
            AppointmentRepository appointmentRepository,
            DoctorRepository doctorRepository,
            UserRepository userRepository) {

        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AppointmentResponse createAppointment(AppointmentRequest request) {

        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        User patient = userRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Appointment appointment = new Appointment();

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setNotes(request.getNotes());
        appointment.setStatus(AppointmentStatus.BOOKED);

        return map(appointmentRepository.save(appointment));
    }

    @Override
    public List<AppointmentResponse> getAllAppointments() {

        return appointmentRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentResponse getAppointmentById(Long id) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        return map(appointment);
    }

    @Override
    public AppointmentResponse updateAppointment(Long id,
                                                 AppointmentRequest request) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        User patient = userRepository.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setNotes(request.getNotes());

        return map(appointmentRepository.save(appointment));
    }

    @Override
    public void deleteAppointment(Long id) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        appointmentRepository.delete(appointment);
    }

    private AppointmentResponse map(Appointment appointment) {

        AppointmentResponse response = new AppointmentResponse();

        response.setId(appointment.getId());

        response.setDoctorName(
                appointment.getDoctor().getFirstName()
        );

        response.setPatientName(
                appointment.getPatient().getFirstName()
                        + " "
                        + appointment.getPatient().getLastName()
        );

        response.setAppointmentDate(
                appointment.getAppointmentDate()
        );

        response.setAppointmentTime(
                appointment.getAppointmentTime()
        );

        response.setStatus(
                appointment.getStatus().name()
        );

        response.setNotes(
                appointment.getNotes()
        );

        return response;
    }

}