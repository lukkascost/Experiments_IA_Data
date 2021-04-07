package br.com.lukkascost.executions.module.services;

import br.com.lukkascost.commons.module.models.dto.ExecutionCreateDTO;
import br.com.lukkascost.commons.module.models.dto.ExecutionDetailsDTO;
import br.com.lukkascost.commons.module.models.dto.ExecutionPredictionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IExecutionService {
    Page<ExecutionDetailsDTO> findAll(ExecutionDetailsDTO executionDetailsDTO, UUID round_id, Pageable pageable);

    ExecutionDetailsDTO create(ExecutionCreateDTO dto);

    ExecutionDetailsDTO insertPredictions(List<ExecutionPredictionDTO> predictions);
}
