package br.com.lukkascost.attributes.module.services.impl;

import br.com.lukkascost.attributes.module.services.IAttributeService;
import br.com.lukkascost.commons.module.models.entities.AttributeEntity;
import br.com.lukkascost.commons.module.models.entities.SampleEntity;
import br.com.lukkascost.commons.module.repositories.IAttributeRepository;
import br.com.lukkascost.commons.module.repositories.ISampleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AttributeService implements IAttributeService {

    private final IAttributeRepository attributeRepository;
    private final ISampleRepository sampleRepository;

    public AttributeService(IAttributeRepository attributeRepository, ISampleRepository sampleRepository) {
        this.attributeRepository = attributeRepository;
        this.sampleRepository = sampleRepository;
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
}
