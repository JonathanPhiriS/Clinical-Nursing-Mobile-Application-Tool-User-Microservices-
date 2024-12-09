package com.example.cnmat.service;

import org.springframework.stereotype.Service;

@Service
public class SmsService {

    public void sendSms(String phoneNumber, String message) {
        // Logic for integrating with an SMS provider like Twilio
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }
}
