package br.com.lukkascost.api.gateway.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ObjectMapper mapper;

    public JmsController(SimpMessagingTemplate simpMessagingTemplate, JmsTemplate jmsTemplate, ObjectMapper mapper) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.mapper = mapper;
    }

}
