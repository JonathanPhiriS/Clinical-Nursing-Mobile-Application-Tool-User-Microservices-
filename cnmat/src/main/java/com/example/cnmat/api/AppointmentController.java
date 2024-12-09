package com.example.cnmat.controller;

import com.example.cnmat.dto.AppointmentRequestDto;
import com.example.cnmat.dto.NurseAcceptanceDto;
import com.example.cnmat.service.AppointmentService;
import com.example.cnmat.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private SocketService socketService;

    // Request a new appointment
    @PostMapping("/request")
    public ResponseEntity<String> requestAppointment(@RequestBody AppointmentRequestDto requestDto) {
        try {
            if (requestDto == null || requestDto.getUserId() == null) {
                logger.error("Invalid AppointmentRequestDto received: {}", requestDto);
                return ResponseEntity.badRequest().body("Invalid input data");
            }

            appointmentService.requestAppointment(requestDto);
            logger.info("Appointment requested for user ID: {}", requestDto.getUserId());

            return ResponseEntity.ok("Appointment request created.");
        } catch (Exception e) {
            logger.error("Error during appointment request process", e);
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    // Get upcoming appointments
    @GetMapping("/upcoming")
    public ResponseEntity<List<AppointmentRequestDto>> getUpcomingAppointments() {
        try {
            List<AppointmentRequestDto> upcomingAppointments = appointmentService.getUpcomingAppointments();
            return ResponseEntity.ok(upcomingAppointments);
        } catch (Exception e) {
            logger.error("Error fetching upcoming appointments", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    // Get past appointments
    @GetMapping("/past")
    public ResponseEntity<List<AppointmentRequestDto>> getPastAppointments() {
        try {
            List<AppointmentRequestDto> pastAppointments = appointmentService.getPastAppointments();
            return ResponseEntity.ok(pastAppointments);
        } catch (Exception e) {
            logger.error("Error fetching past appointments", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    // Accept an appointment by a nurse
    @PostMapping("/nurse/acceptance")
    public ResponseEntity<String> acceptAppointment(@RequestBody NurseAcceptanceDto acceptanceDto) {
        try {
            if (acceptanceDto == null || acceptanceDto.getRequestId() == null) {
                logger.error("Invalid NurseAcceptanceDto received: {}", acceptanceDto);
                return ResponseEntity.badRequest().body("Invalid input data");
            }

            // Logic to handle the nurse acceptance (e.g., update the database)
            logger.info("Nurse acceptance for request ID: {}", acceptanceDto.getRequestId());

            // Notify the user via WebSocket
            socketService.notifyUser(acceptanceDto.getRequestId(), acceptanceDto);

            return ResponseEntity.ok("Nurse acceptance notification sent.");
        } catch (Exception e) {
            logger.error("Error during nurse acceptance process", e);
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
}
