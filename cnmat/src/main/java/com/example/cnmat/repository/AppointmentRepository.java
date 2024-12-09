package com.example.cnmat.repository;

import com.example.cnmat.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByUserId(Long userId);
    List<Appointment> findByNurseId(Long nurseId);
    List<Appointment> findByStatus(String status);
}
