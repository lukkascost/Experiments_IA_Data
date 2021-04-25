package br.com.lukkascost.experiments.module.services.impl;

import br.com.lukkascost.commons.module.models.dto.ExperimentsCreateDTO;
import br.com.lukkascost.commons.module.models.dto.ExperimentsDTO;
import br.com.lukkascost.commons.module.models.entities.DatasetEntity;
import br.com.lukkascost.commons.module.models.entities.ExperimentEntity;
import br.com.lukkascost.commons.module.repositories.IExperimentRepository;
import br.com.lukkascost.commons.module.specifications.DatasetSpecifications;
import br.com.lukkascost.commons.module.specifications.ExperimentsSpecifications;
import br.com.lukkascost.experiments.module.mappers.ExperimentMapper;
import br.com.lukkascost.experiments.module.services.IExperimentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IExperimentServiceImpl implements IExperimentService {
    private final ExperimentMapper experimentMapper;
    private final IExperimentRepository experimentRepository;

    public IExperimentServiceImpl(ExperimentMapper experimentMapper, IExperimentRepository experimentRepository) {
        this.experimentMapper = experimentMapper;
        this.experimentRepository = experimentRepository;
    }

    @Override
    public Page<ExperimentsDTO> findAll(ExperimentsDTO dto, Pageable pageable) {
        Specification<ExperimentEntity> spec = experimentMapper.convert(dto);
        Page<ExperimentEntity> entities = experimentRepository.findAll(spec, pageable);
        return experimentMapper.convert(entities);
    }

    @Override
    public ExperimentsDTO create(ExperimentsCreateDTO dto) {
        Specification<ExperimentEntity> spec = Specification.where(ExperimentsSpecifications.withName(dto.getName()));
        ExperimentEntity entity = experimentRepository.findOne(spec).orElse(null);
        if (entity!= null ) return experimentMapper.convert(entity);
        entity = experimentMapper.convertEntity(dto);
        entity.setId(UUID.randomUUID());
        entity = experimentRepository.save(entity);
        return experimentMapper.convert(entity);
    }
}
