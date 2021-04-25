package br.com.lukkascost.dataset.module.services;


import br.com.lukkascost.commons.module.models.dto.DatasetDetailsDTO;

import java.util.List;
import java.util.UUID;

public interface IDatasetDetailsService {
    List<DatasetDetailsDTO> findAll();

    DatasetDetailsDTO findByID(UUID dataset_id);
}
