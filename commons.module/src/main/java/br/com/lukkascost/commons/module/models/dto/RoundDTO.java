package br.com.lukkascost.commons.module.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RoundDTO {
    private UUID id;
    private String createdAt;
    private String updatedAt;
    private DatasetDTO datasetDTO;
    private ExperimentsDTO experimentsDTO;
}
