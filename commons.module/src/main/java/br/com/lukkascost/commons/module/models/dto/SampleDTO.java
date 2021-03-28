package br.com.lukkascost.commons.module.models.dto;

import br.com.lukkascost.commons.module.models.enuns.ExtractorType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SampleDTO {
    private UUID id;
    private String createdAt;
    private String updatedAt;
    private ExtractorType extractorType;
    private long attributes;
    private String originalFileName;
    private String label;
}
