package br.com.lukkascost.commons.module.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AttributeCreateDTO {
    private UUID sampleId;
    private String value;
    private float order;
    private String name;
}
