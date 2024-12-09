package com.example.cnmat.service;

import com.example.cnmat.dto.NurseAcceptanceDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AppointmentListener {

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveNurseResponse(NurseAcceptanceDto acceptanceDto) {
        // Logic for handling the nurse's response to the appointment request
        System.out.println("Received nurse response from RabbitMQ: " + acceptanceDto);

        // Add your logic here to process the response, update the database, etc.
    }
}
