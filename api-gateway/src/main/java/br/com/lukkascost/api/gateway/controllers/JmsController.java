package br.com.lukkascost.api.gateway.controllers;

import br.com.lukkascost.api.gateway.models.EventPostMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JmsController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ObjectMapper mapper;

    @JmsListener(destination = "generic.events")
    public void receivePostEventMessage(String message) throws JsonProcessingException {
        EventPostMessage event = mapper.readValue(message, EventPostMessage.class);
        simpMessagingTemplate.convertAndSend(event.getSubscriptionUrl(), event.getMessage());
    }

}
