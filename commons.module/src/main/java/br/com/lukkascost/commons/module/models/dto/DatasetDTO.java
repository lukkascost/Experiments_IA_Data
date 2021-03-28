package br.com.lukkascost.commons.module.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DatasetDTO {
    private UUID id;
    private String description;
    private String name;
    private float samples;

}
