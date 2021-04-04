package br.com.lukkascost.commons.module.models.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RoundCreateDTO {
    private UUID datasetId;
    private UUID experimentId;
}
