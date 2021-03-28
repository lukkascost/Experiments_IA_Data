package br.com.lukkascost.dataset.module.services;


import br.com.lukkascost.commons.module.models.dto.DatasetDTO;

import java.util.List;
import java.util.UUID;

public interface IDatasetService {
    DatasetDTO findByID(UUID dataset_id);

    List<DatasetDTO> findAll();
}
