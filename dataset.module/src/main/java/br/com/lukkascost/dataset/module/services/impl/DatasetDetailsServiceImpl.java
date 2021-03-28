package br.com.lukkascost.dataset.module.services.impl;

import br.com.lukkascost.commons.module.models.dto.DatasetDetailsDTO;
import br.com.lukkascost.commons.module.models.entities.DatasetEntity;
import br.com.lukkascost.commons.module.repositories.IDatasetRepository;
import br.com.lukkascost.dataset.module.mappers.DatasetDetailsMapper;
import br.com.lukkascost.dataset.module.services.IDatasetDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DatasetDetailsServiceImpl implements IDatasetDetailsService {

    private final IDatasetRepository datasetRepository;
    private final DatasetDetailsMapper datasetMapper;

    public DatasetDetailsServiceImpl(IDatasetRepository datasetRepository, DatasetDetailsMapper datasetMapper) {
        this.datasetRepository = datasetRepository;
        this.datasetMapper = datasetMapper;
    }

    @Override
    public List<DatasetDetailsDTO> findAll() {
        List<DatasetEntity> datasetEntities = datasetRepository.findAll();
        return datasetMapper.convert(datasetEntities);
    }

    @Override
    public DatasetDetailsDTO findByID(UUID dataset_id) {
        DatasetEntity datasetEntity = datasetRepository.findById(dataset_id).get();
        return datasetMapper.convert(datasetEntity);
    }
}
