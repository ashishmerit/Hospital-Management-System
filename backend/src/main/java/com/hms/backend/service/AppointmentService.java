package com.hms.backend.service;

import com.hms.backend.dto.request.AppointmentRequest;
import com.hms.backend.dto.response.AppointmentResponse;

import java.util.List;

public interface AppointmentService {

    AppointmentResponse createAppointment(AppointmentRequest request);

    List<AppointmentResponse> getAllAppointments();

    AppointmentResponse getAppointmentById(Long id);

    AppointmentResponse updateAppointment(Long id,AppointmentRequest request);

    void deleteAppointment(Long id);
}