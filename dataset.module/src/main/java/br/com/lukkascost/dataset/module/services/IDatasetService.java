package br.com.lukkascost.dataset.module.services;


import br.com.lukkascost.commons.module.models.dto.DatasetCreateDTO;
import br.com.lukkascost.commons.module.models.dto.DatasetDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IDatasetService {
    DatasetDTO findByID(UUID dataset_id);

    List<DatasetDTO> findAll();

    Page<DatasetDTO> findAll(DatasetDTO datasetDTO, Pageable pageable);

    DatasetDTO create(DatasetCreateDTO dto);

    DatasetDTO update(DatasetDTO dto);

    DatasetDTO delete(UUID id);
}
