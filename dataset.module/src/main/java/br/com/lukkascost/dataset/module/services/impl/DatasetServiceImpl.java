package br.com.lukkascost.dataset.module.services.impl;

import br.com.lukkascost.commons.module.models.dto.DatasetCreateDTO;
import br.com.lukkascost.commons.module.models.dto.DatasetDTO;
import br.com.lukkascost.commons.module.models.entities.DatasetEntity;
import br.com.lukkascost.commons.module.repositories.IDatasetRepository;
import br.com.lukkascost.commons.module.specifications.DatasetSpecifications;
import br.com.lukkascost.dataset.module.mappers.DatasetMapper;
import br.com.lukkascost.dataset.module.services.IDatasetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    @Override
    public Page<DatasetDTO> findAll(DatasetDTO datasetDTO, Pageable pageable) {
        Specification<DatasetEntity> spec = datasetMapper.convert(datasetDTO);
        Page<DatasetEntity> entities = datasetRepository.findAll(spec, pageable);
        return datasetMapper.convert(entities);
    }

    @Override
    public DatasetDTO create(DatasetCreateDTO dto) {
        Specification<DatasetEntity> spec = Specification.where(DatasetSpecifications.withName(dto.getName()));
        DatasetEntity entity = datasetRepository.findOne(spec).orElse(null);
        if (entity!= null ) return datasetMapper.convert(entity);
        entity = datasetMapper.convertEntity(dto);
        entity.setId(UUID.randomUUID());
        entity = datasetRepository.save(entity);
        return datasetMapper.convert(entity);

    }

    @Override
    public DatasetDTO update(DatasetDTO dto) {
        Specification<DatasetEntity> spec = datasetMapper.convert(dto);
        DatasetEntity entity = datasetRepository.findOne(spec).orElse(null);
        if (entity == null ) throw new IllegalArgumentException("Dataset name not found");
        entity.setDescription(dto.getDescription());
        entity = datasetRepository.save(entity);
        return datasetMapper.convert(entity);
    }

    @Override
    public DatasetDTO delete(UUID id) {
        Specification<DatasetEntity> spec =  Specification.where(DatasetSpecifications.withId(id));
        DatasetEntity entity = datasetRepository.findOne(spec).orElse(null);
        if (entity == null ) throw new IllegalArgumentException("Dataset name not found");
        datasetRepository.delete(entity);
        return datasetMapper.convert(entity);
    }
}
