package br.com.lukkascost.commons.module.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ExecutionPredictionDTO {
    private String real;
    private String predicted;
    private UUID id;
    private UUID sample_id;
}
