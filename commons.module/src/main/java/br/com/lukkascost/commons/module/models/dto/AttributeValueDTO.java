package br.com.lukkascost.commons.module.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AttributeValueDTO {
    private UUID sampleId;
    private String[] valueOrdered;
    private String label;
}
