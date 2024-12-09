package com.example.cnmat.service;

import com.example.cnmat.dto.AppointmentRequestDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    // Mock data storage for simplicity; replace with database interaction
    private final List<AppointmentRequestDto> appointments = new ArrayList<>();

    public void requestAppointment(AppointmentRequestDto requestDto) {
        // Add the new appointment request to the list (or save it to a database)
        appointments.add(requestDto);
    }

    public List<AppointmentRequestDto> getUpcomingAppointments() {
        // Filter and return upcoming appointments (mock data here)
        return appointments; // Replace with actual filtering logic
    }

    public List<AppointmentRequestDto> getPastAppointments() {
        // Filter and return past appointments (mock data here)
        return appointments; // Replace with actual filtering logic
    }
}
