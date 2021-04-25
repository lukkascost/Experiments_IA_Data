package br.com.lukkascost.commons.module.models.dto;

import br.com.lukkascost.commons.module.models.entities.SampleEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DatasetDetailsDTO {

    private String createdAt;
    private String updatedAt;
    private String description;
    private String name;
    private List<SampleDTO> samples;

}
