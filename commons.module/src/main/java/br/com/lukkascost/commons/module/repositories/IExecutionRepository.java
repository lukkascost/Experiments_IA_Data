package br.com.lukkascost.commons.module.repositories;

import br.com.lukkascost.commons.module.models.entities.ExecutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface IExecutionRepository extends JpaRepository<ExecutionEntity, UUID> , JpaSpecificationExecutor<ExecutionEntity> {

    boolean existsByModelId(UUID modelId);
}
