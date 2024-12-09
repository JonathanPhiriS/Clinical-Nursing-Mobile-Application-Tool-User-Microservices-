package com.example.cnmat.listener;

import com.example.cnmat.dto.NurseAcceptanceDto;
import com.example.cnmat.model.Appointment;
import com.example.cnmat.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NurseRequestQueueListener {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void processNurseAcceptance(NurseAcceptanceDto acceptanceDto) {
        // Fetch the appointment and update with the nurseId
        Appointment appointment = appointmentRepository.findById(acceptanceDto.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setNurseId(acceptanceDto.getNurseId()); // Set the nurse who accepted
        appointment.setStatus("Accepted by Nurse");

        appointmentRepository.save(appointment); // Save the updated appointment
    }
}
