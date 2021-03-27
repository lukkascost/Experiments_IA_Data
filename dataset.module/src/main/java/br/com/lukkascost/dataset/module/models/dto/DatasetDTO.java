package br.com.lukkascost.dataset.module.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatasetDTO {
    private String id;
    private String description;
    private String name;
    private float samples;

}
