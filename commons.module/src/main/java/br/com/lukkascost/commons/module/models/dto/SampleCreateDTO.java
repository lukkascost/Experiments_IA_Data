package br.com.lukkascost.commons.module.models.dto;

import br.com.lukkascost.commons.module.models.enuns.ExtractorType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SampleCreateDTO {
    private UUID datasetId;
    private ExtractorType extractorType;
    private String originalFileName;
    private String label;
    private String fileId;
}
