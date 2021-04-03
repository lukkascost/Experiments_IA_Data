package br.com.lukkascost.attributes.module.services.impl;

import br.com.lukkascost.attributes.module.mappers.AttributeMapper;
import br.com.lukkascost.attributes.module.services.IAttributeService;
import br.com.lukkascost.commons.module.models.dto.AttributeCreateDTO;
import br.com.lukkascost.commons.module.models.dto.AttributeDetailsDTO;
import br.com.lukkascost.commons.module.models.entities.AttributeEntity;
import br.com.lukkascost.commons.module.models.entities.SampleEntity;
import br.com.lukkascost.commons.module.repositories.IAttributeRepository;
import br.com.lukkascost.commons.module.repositories.ISampleRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AttributeService implements IAttributeService {

    private final IAttributeRepository attributeRepository;
    private final ISampleRepository sampleRepository;
    private final AttributeMapper attributeMapper;

    public AttributeService(IAttributeRepository attributeRepository, ISampleRepository sampleRepository, AttributeMapper attributeMapper) {
        this.attributeRepository = attributeRepository;
        this.sampleRepository = sampleRepository;
        this.attributeMapper = attributeMapper;
    }

    @Override
    public List<AttributeEntity> findAll() {
        return attributeRepository.findAll();
    }

    @Override
    public List<AttributeEntity> findAllBySampleId(UUID sample_id) {
        SampleEntity sample = sampleRepository.findById(sample_id).get();
        return attributeRepository.findAllBySample(sample);
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
}
