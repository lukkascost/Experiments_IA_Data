package br.com.lukkascost.attributes.module.models;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AttributeDetailsDTO {
    private UUID id;
    private String createdAt;
    private String updatedAt;
    private String value;
    private float order;
    private String name;
}
