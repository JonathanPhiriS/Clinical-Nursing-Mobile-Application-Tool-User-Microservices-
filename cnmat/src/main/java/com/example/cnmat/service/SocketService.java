package com.example.cnmat.service;

import com.example.cnmat.dto.NurseAcceptanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SocketService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public SocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Method to notify the user of nurse acceptance
    public void notifyUser(Long userId, NurseAcceptanceDto acceptance) {
        messagingTemplate.convertAndSend("/topic/appointments/" + userId, acceptance);
    }

    // Additional methods for other WebSocket communications can be added here
}
