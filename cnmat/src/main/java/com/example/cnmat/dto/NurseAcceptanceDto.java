package com.example.cnmat.dto;

public class NurseAcceptanceDto {
    private Long nurseId;
    private Long requestId;
    private Long appointmentId; // Add this line
    private String status;  // e.g., "ACCEPTED" or "DECLINED"
    private String message; // Optional message from the nurse

    // Getters and Setters
    public Long getNurseId() {
        return nurseId;
    }

    public void setNurseId(Long nurseId) {
        this.nurseId = nurseId;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getAppointmentId() { // Add this getter method
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) { // Add this setter method
        this.appointmentId = appointmentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
