package br.com.lukkascost.sample.module.services.impl;

import br.com.lukkascost.commons.module.models.dto.SampleCreateDTO;
import br.com.lukkascost.commons.module.models.dto.SampleDTO;
import br.com.lukkascost.commons.module.models.entities.DatasetEntity;
import br.com.lukkascost.commons.module.models.entities.SampleEntity;
import br.com.lukkascost.commons.module.repositories.IDatasetRepository;
import br.com.lukkascost.commons.module.repositories.ISampleRepository;
import br.com.lukkascost.sample.module.mapper.SampleMapper;
import br.com.lukkascost.sample.module.services.ISampleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ISampleServiceImpl implements ISampleService {
    private final ISampleRepository sampleRepository;
    private final IDatasetRepository datasetRepository;
    private final SampleMapper sampleMapper;

    public ISampleServiceImpl(ISampleRepository sampleRepository, IDatasetRepository datasetRepository, SampleMapper sampleMapper) {
        this.sampleRepository = sampleRepository;
        this.datasetRepository = datasetRepository;
        this.sampleMapper = sampleMapper;
    }

    @Override
    public List<SampleDTO> findAll() {
        List<SampleEntity> entities = sampleRepository.findAll();
        return sampleMapper.convert(entities);
    }
    @Override
    public Page<SampleDTO> findAll(SampleDTO sampleDTO, UUID dataset_id, Pageable page) {
        Specification<SampleEntity> spec = sampleMapper.convert(sampleDTO,dataset_id);
        Page<SampleEntity> sampleEntity = sampleRepository.findAll(spec, page);
        return sampleMapper.convert(sampleEntity);
    }

    @Override
    public SampleDTO create(SampleCreateDTO dto) {
        DatasetEntity datasetEntity = datasetRepository.findById(dto.getDatasetId()).get();
        Specification<SampleEntity> spec = sampleMapper.convert(dto);
        SampleEntity entity = sampleRepository.findOne(spec).orElse(null);
        if (entity!= null ) return sampleMapper.convert(entity);
        entity = sampleMapper.convertEntity(dto);
        entity.setId(UUID.randomUUID());
        entity.setDataset(datasetEntity);
        entity = sampleRepository.save(entity);
        return sampleMapper.convert(entity);
    }
}
