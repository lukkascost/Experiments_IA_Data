package br.com.lukkascost.sample.module.services.impl;

import br.com.lukkascost.commons.module.models.dto.SampleDTO;
import br.com.lukkascost.commons.module.models.entities.SampleEntity;
import br.com.lukkascost.commons.module.repositories.ISampleRepository;
import br.com.lukkascost.sample.module.mapper.SampleMapper;
import br.com.lukkascost.sample.module.services.ISampleService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ISampleServiceImpl implements ISampleService {
    private final ISampleRepository sampleRepository;
    private final SampleMapper sampleMapper;

    public ISampleServiceImpl(ISampleRepository sampleRepository, SampleMapper sampleMapper) {
        this.sampleRepository = sampleRepository;
        this.sampleMapper = sampleMapper;
    }

    @Override
    public List<SampleDTO> findAll() {
        List<SampleEntity> entities = sampleRepository.findAll();
        return sampleMapper.convert(entities);
    }
    @Override
    public List<SampleDTO> findAll(SampleDTO sampleDTO, UUID dataset_id) {
        Specification<SampleEntity> spec = sampleMapper.convert(sampleDTO,dataset_id);
        List<SampleEntity> sampleEntity = sampleRepository.findAll(spec);
        return sampleMapper.convert(sampleEntity);
    }
}
