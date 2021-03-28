package br.com.lukkascost.sample.module.services;

import br.com.lukkascost.commons.module.models.dto.SampleDTO;

import java.util.List;
import java.util.UUID;

public interface ISampleService {
    List<SampleDTO> findAll();

    List<SampleDTO> findAll(SampleDTO sampleDTO, UUID dataset_id);
}
