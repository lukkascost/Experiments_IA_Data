package br.com.lukkascost.api.gateway.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventPostMessage {
    private String subscriptionUrl;
    private String message;
}
