package br.com.lukkascost.api.gateway.controllers;

import br.com.eletra.broker.topics.models.PostEventNotificationDTO;
import br.com.eletra.constants.topics.GenericNotification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
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
    @JmsListener(destination = GenericNotification.LOGGER_MESSAGE)
    public void receiveMessage(String message){
        simpMessagingTemplate.convertAndSend("/logger",message);
    }
    @JmsListener(destination = GenericNotification.POST_EVENT_MESSAGE)
    public void receivePostEventMessage(String message) throws JsonProcessingException {
        PostEventNotificationDTO event = mapper.convertValue(message, PostEventNotificationDTO.class);
        simpMessagingTemplate.convertAndSend(event.getSubscriptionUrl(), mapper.writeValueAsString(event.getMessage()));
    }
}
