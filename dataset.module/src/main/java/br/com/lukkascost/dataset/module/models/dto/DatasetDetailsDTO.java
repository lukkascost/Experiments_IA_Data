package br.com.lukkascost.dataset.module.models.dto;

import br.com.lukkascost.commons.module.models.entities.SampleEntity;

import java.util.List;

public class DatasetDetailsDTO {

    private String createdAt;
    private String updatedAt;
    private String description;
    private String name;
    private List<SampleEntity> samples;

}
