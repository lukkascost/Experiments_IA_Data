package br.com.lukkascost.executions.module.services.impl;

import br.com.lukkascost.commons.module.models.dto.ExecutionCreateDTO;
import br.com.lukkascost.commons.module.models.dto.ExecutionDetailsDTO;
import br.com.lukkascost.commons.module.models.entities.ExecutionEntity;
import br.com.lukkascost.commons.module.models.entities.RoundEntity;
import br.com.lukkascost.commons.module.models.entities.SampleEntity;
import br.com.lukkascost.commons.module.models.objects.SplitterDataset;
import br.com.lukkascost.commons.module.repositories.IDatasetRepository;
import br.com.lukkascost.commons.module.repositories.IExecutionRepository;
import br.com.lukkascost.commons.module.repositories.IRoundRepository;
import br.com.lukkascost.commons.module.repositories.ISampleRepository;
import br.com.lukkascost.executions.module.mappers.ExecutionMapper;
import br.com.lukkascost.executions.module.services.IExecutionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IExecutionServiceImpl implements IExecutionService {

    private final ExecutionMapper executionMapper;
    private final IExecutionRepository executionRepository;
    private final IDatasetRepository datasetRepository;
    private final IRoundRepository roundRepository;
    private final ISampleRepository sampleRepository;

    public IExecutionServiceImpl(ExecutionMapper executionMapper, IExecutionRepository executionRepository, IDatasetRepository datasetRepository, IRoundRepository roundRepository, ISampleRepository sampleRepository) {
        this.executionMapper = executionMapper;
        this.executionRepository = executionRepository;
        this.datasetRepository = datasetRepository;
        this.roundRepository = roundRepository;
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Page<ExecutionDetailsDTO> findAll(ExecutionDetailsDTO executionDetailsDTO, UUID round_id, Pageable pageable) {
        Specification<ExecutionEntity> spec = executionMapper.convert(executionDetailsDTO);
        Page<ExecutionEntity> sampleEntity = executionRepository.findAll(spec, pageable);
        return executionMapper.convert(sampleEntity);
    }

    @Override
    public ExecutionDetailsDTO create(ExecutionCreateDTO dto) {
        RoundEntity roundEntity = roundRepository.getOne(dto.getRoundId());
        List<SampleEntity> sampleEntities = sampleRepository.findAllByDataset(roundEntity.getDataset());

        SplitterDataset splitterDataset = dto.getSplitterMode().split(sampleEntities);
        splitterDataset.setSplitType(dto.getSplitterMode().getSplitType());
        ExecutionEntity entity = new ExecutionEntity();
        entity.setId(UUID.randomUUID());
        entity.setRound(roundEntity);
        entity.setSplitDataset(splitterDataset);
        entity = executionRepository.save(entity);

        return executionMapper.convert(entity);
    }
}
