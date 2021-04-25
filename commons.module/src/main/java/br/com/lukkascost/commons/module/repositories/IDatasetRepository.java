package br.com.lukkascost.commons.module.repositories;

import br.com.lukkascost.commons.module.models.entities.DatasetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface IDatasetRepository extends JpaRepository<DatasetEntity, UUID> , JpaSpecificationExecutor<DatasetEntity> {

}
