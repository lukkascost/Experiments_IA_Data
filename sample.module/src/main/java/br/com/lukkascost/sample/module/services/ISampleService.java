package br.com.lukkascost.sample.module.services;

import br.com.lukkascost.commons.module.models.dto.SampleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ISampleService {
    List<SampleDTO> findAll();

    Page<SampleDTO> findAll(SampleDTO sampleDTO, UUID dataset_id, Pageable pageable);
}
