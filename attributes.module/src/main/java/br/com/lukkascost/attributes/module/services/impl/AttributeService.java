package br.com.lukkascost.attributes.module.services.impl;

import br.com.lukkascost.attributes.module.mappers.AttributeMapper;
import br.com.lukkascost.attributes.module.services.IAttributeService;
import br.com.lukkascost.commons.module.models.dto.AttributeCreateDTO;
import br.com.lukkascost.commons.module.models.dto.AttributeDetailsDTO;
import br.com.lukkascost.commons.module.models.dto.AttributeValueDTO;
import br.com.lukkascost.commons.module.models.entities.AttributeEntity;
import br.com.lukkascost.commons.module.models.entities.SampleEntity;
import br.com.lukkascost.commons.module.repositories.IAttributeRepository;
import br.com.lukkascost.commons.module.repositories.IDatasetRepository;
import br.com.lukkascost.commons.module.repositories.ISampleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AttributeService implements IAttributeService {

    private final IAttributeRepository attributeRepository;
    private final ISampleRepository sampleRepository;
    private final IDatasetRepository datasetRepository;
    private final AttributeMapper attributeMapper;

    public AttributeService(IAttributeRepository attributeRepository, ISampleRepository sampleRepository, IDatasetRepository datasetRepository, AttributeMapper attributeMapper) {
        this.attributeRepository = attributeRepository;
        this.sampleRepository = sampleRepository;
        this.datasetRepository = datasetRepository;
        this.attributeMapper = attributeMapper;
    }

    @Override
    public AttributeDetailsDTO create(AttributeCreateDTO dto) {

        SampleEntity datasetEntity = sampleRepository.findById(dto.getSampleId()).get();
        Specification<AttributeEntity> spec = attributeMapper.convert(dto);
        AttributeEntity entity = attributeRepository.findOne(spec).orElse(null);
        if (entity!= null ) return attributeMapper.convert(entity);
        entity = attributeMapper.convertEntity(dto);
        entity.setId(UUID.randomUUID());
        entity.setSample(datasetEntity);
        entity = attributeRepository.save(entity);
        return attributeMapper.convert(entity);
    }

    @Override
    public Page<AttributeDetailsDTO> findAll(AttributeDetailsDTO attributeDetailsDTO, UUID sample_id, Pageable pageable) {
        Specification<AttributeEntity> spec = attributeMapper.convert(attributeDetailsDTO, sample_id);
        Page<AttributeEntity> entities = attributeRepository.findAll(spec, pageable);
        return attributeMapper.convert(entities);
    }

    @Override
    public AttributeValueDTO findAllValues(AttributeDetailsDTO attributeDetailsDTO, UUID sample_id, Pageable pageable) {
        Specification<AttributeEntity> spec = attributeMapper.convert(attributeDetailsDTO, sample_id);
        Page<AttributeEntity> entities = attributeRepository.findAll(spec, pageable);

        return attributeMapper.convertValue(entities.getContent());
    }
}
