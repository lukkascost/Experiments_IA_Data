package br.com.lukkascost.rounds.module.services.impl;

import br.com.lukkascost.commons.module.models.dto.RoundCreateDTO;
import br.com.lukkascost.commons.module.models.dto.RoundDTO;
import br.com.lukkascost.commons.module.models.dto.RoundDetailsDTO;
import br.com.lukkascost.commons.module.models.entities.DatasetEntity;
import br.com.lukkascost.commons.module.models.entities.ExecutionEntity;
import br.com.lukkascost.commons.module.models.entities.ExperimentEntity;
import br.com.lukkascost.commons.module.models.entities.RoundEntity;
import br.com.lukkascost.commons.module.models.objects.ConfusionMatrix;
import br.com.lukkascost.commons.module.repositories.IDatasetRepository;
import br.com.lukkascost.commons.module.repositories.IExecutionRepository;
import br.com.lukkascost.commons.module.repositories.IExperimentRepository;
import br.com.lukkascost.commons.module.repositories.IRoundRepository;
import br.com.lukkascost.commons.module.specifications.ExecutionSpecifications;
import br.com.lukkascost.rounds.module.mappers.RoundMapper;
import br.com.lukkascost.rounds.module.services.IRoundService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class IRoundServiceImpl implements IRoundService {

    private final IRoundRepository roundRepository;
    private final IDatasetRepository datasetRepository;
    private final IExperimentRepository experimentRepository;
    private final IExecutionRepository executionRepository;
    private final RoundMapper roundMapper;

    public IRoundServiceImpl(IRoundRepository roundRepository, IDatasetRepository datasetRepository, IExperimentRepository experimentRepository, IExecutionRepository executionRepository, RoundMapper roundMapper) {
        this.roundRepository = roundRepository;
        this.datasetRepository = datasetRepository;
        this.experimentRepository = experimentRepository;
        this.executionRepository = executionRepository;
        this.roundMapper = roundMapper;

    }

    @Override
    public Page<RoundDTO> findAll(RoundDTO dto, Pageable pageable) {
        Specification<RoundEntity> spec = roundMapper.convert(dto);
        Page<RoundEntity> entities = roundRepository.findAll(spec, pageable);
        return roundMapper.convert(entities);
    }

    @Override
    public RoundDTO create(RoundCreateDTO dto) {
        DatasetEntity dataset = datasetRepository.getOne(dto.getDatasetId());
        ExperimentEntity experimentEntity = experimentRepository.getOne(dto.getExperimentId());
        RoundEntity entity = roundMapper.convertEntity(dto);
        entity.setDataset(dataset);
        entity.setExperiment(experimentEntity);
        entity.setId(UUID.randomUUID());
        entity = roundRepository.save(entity);
        return roundMapper.convert(entity);
    }

    @Override
    public RoundDetailsDTO doCompressResult(UUID round_id) {
        RoundEntity round  = roundRepository.findById(UUID.fromString("b6c58801-5218-40cf-b964-afe9d6f3beb5")).get();
        List<ExecutionEntity> executionEntityList = executionRepository.findAll(Specification.where(ExecutionSpecifications.withRoundId(round.getId())));
        round.setSumConfusionMatrix(new ConfusionMatrix());
        round.getSumConfusionMatrix().setConfusionMatrix(new HashMap<>());
        round.getSumConfusionMatrix().setLabels(new ArrayList<>());
        for (ExecutionEntity entity : executionEntityList) {
            for (String x : entity.getConfusionMatrix().getLabels()) {
                round.getSumConfusionMatrix().addLabel(x);
            }
            round.getSumConfusionMatrix().add(entity.getConfusionMatrix());
        }
        round = roundRepository.save(round);
        return roundMapper.convertDetails(round);
    }
}
