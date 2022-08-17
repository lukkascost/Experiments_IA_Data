package br.com.lukkascost.api.gateway.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class JmsController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ObjectMapper mapper;

    @JmsListener(destination = "generic.events")
    public void receivePostEventMessage(String message) throws JsonProcessingException {
        Map<String, String> event = mapper.convertValue(message, Map.class);
        simpMessagingTemplate.convertAndSend(event.get("subscriptionUrl"), event.get("message"));
    }

}
