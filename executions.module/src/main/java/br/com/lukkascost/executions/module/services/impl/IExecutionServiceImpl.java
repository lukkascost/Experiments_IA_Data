package br.com.lukkascost.executions.module.services.impl;

import br.com.lukkascost.commons.module.models.dto.ExecutionCreateDTO;
import br.com.lukkascost.commons.module.models.dto.ExecutionDetailsDTO;
import br.com.lukkascost.commons.module.models.dto.ExecutionPredictionDTO;
import br.com.lukkascost.commons.module.models.entities.ClassifierModelCacheEntity;
import br.com.lukkascost.commons.module.models.entities.ExecutionEntity;
import br.com.lukkascost.commons.module.models.entities.RoundEntity;
import br.com.lukkascost.commons.module.models.entities.SampleEntity;
import br.com.lukkascost.commons.module.models.objects.ConfusionMatrix;
import br.com.lukkascost.commons.module.models.objects.SplitterDataset;
import br.com.lukkascost.commons.module.repositories.*;
import br.com.lukkascost.executions.module.mappers.ExecutionMapper;
import br.com.lukkascost.executions.module.services.IExecutionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class IExecutionServiceImpl implements IExecutionService {

    private final ExecutionMapper executionMapper;
    private final IExecutionRepository executionRepository;
    private final IDatasetRepository datasetRepository;
    private final IRoundRepository roundRepository;
    private final ISampleRepository sampleRepository;
    private final IModelRepository modelRepository;

    public IExecutionServiceImpl(ExecutionMapper executionMapper, IExecutionRepository executionRepository, IDatasetRepository datasetRepository, IRoundRepository roundRepository, ISampleRepository sampleRepository, IModelRepository modelRepository) {
        this.executionMapper = executionMapper;
        this.executionRepository = executionRepository;
        this.datasetRepository = datasetRepository;
        this.roundRepository = roundRepository;
        this.sampleRepository = sampleRepository;
        this.modelRepository = modelRepository;
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

    @Override
    public ExecutionDetailsDTO insertPredictions(List<ExecutionPredictionDTO> predictions) {
        ExecutionEntity executionEntity = null;
        for (int i = 0; i < predictions.size(); i++) {
            ExecutionPredictionDTO prediction = predictions.get(i);
            executionEntity = executionRepository.getOne(prediction.getId());
            ClassifierModelCacheEntity modelCacheEntity = modelRepository.findById(executionEntity.getModelId().toString()).get();

            if(modelCacheEntity.getSampleResult() == null ) modelCacheEntity.setSampleResult(new HashMap<>());
            modelCacheEntity.getSampleResult().put(prediction.getSample_id(),prediction.getPredicted());

            ConfusionMatrix cm = executionEntity.getConfusionMatrix();
            if (cm == null){
                cm = new ConfusionMatrix();
                cm.setLabels(new ArrayList<>());
                cm.setTotalElements(0);
                cm.setConfusionMatrix(new HashMap<>());
                executionEntity.setConfusionMatrix(cm);
            }
            if(!cm.getLabels().contains(prediction.getReal())) cm.addLabel(prediction.getReal());
            if(!cm.getLabels().contains(prediction.getPredicted())) cm.addLabel(prediction.getPredicted());
            cm.add(prediction.getPredicted(), prediction.getReal());

            executionEntity = executionRepository.save(executionEntity);
            modelCacheEntity = modelRepository.save(modelCacheEntity);
        }
        return executionMapper.convert(executionEntity);
    }

    @Override
    public ExecutionDetailsDTO insertModel(ClassifierModelCacheEntity predictions, UUID execution_id) {
        ExecutionEntity executionEntity = executionRepository.getOne(execution_id);
        predictions.setId(UUID.randomUUID());
        predictions = modelRepository.save(predictions);
        executionEntity.setModelId(predictions.getId());
        executionEntity = executionRepository.save(executionEntity);
        return executionMapper.convert(executionEntity);
    }
}
