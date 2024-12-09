package com.example.cnmat.service;

import com.example.cnmat.dto.AppointmentRequestDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppointmentProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    public void sendAppointmentRequest(AppointmentRequestDto requestDto) {
        amqpTemplate.convertAndSend(exchange, routingKey, requestDto);
        System.out.println("Sent appointment request to RabbitMQ: " + requestDto);
    }
}
