package br.com.lukkascost.dataset.module.services.impl;

import br.com.lukkascost.commons.module.models.dto.DatasetDTO;
import br.com.lukkascost.commons.module.models.entities.DatasetEntity;
import br.com.lukkascost.commons.module.repositories.IDatasetRepository;
import br.com.lukkascost.dataset.module.mappers.DatasetMapper;
import br.com.lukkascost.dataset.module.services.IDatasetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DatasetServiceImpl implements IDatasetService {

    private final IDatasetRepository datasetRepository;
    private final DatasetMapper datasetMapper;



    public DatasetServiceImpl(IDatasetRepository datasetRepository, DatasetMapper datasetMapper) {
        this.datasetRepository = datasetRepository;
        this.datasetMapper = datasetMapper;
    }

    @Override
    public DatasetDTO findByID(UUID dataset_id) {
        DatasetEntity datasetEntity = datasetRepository.findById(dataset_id).get();
        return datasetMapper.convert(datasetEntity);
    }

    @Override
    public List<DatasetDTO> findAll() {
        List<DatasetEntity> datasetEntities = datasetRepository.findAll();
        return datasetMapper.convert(datasetEntities);
    }
}
