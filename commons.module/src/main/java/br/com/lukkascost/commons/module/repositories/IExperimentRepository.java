package br.com.lukkascost.commons.module.repositories;

import br.com.lukkascost.commons.module.models.entities.ExperimentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface IExperimentRepository extends JpaRepository<ExperimentEntity, UUID> , JpaSpecificationExecutor<ExperimentEntity> {

}
