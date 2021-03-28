package br.com.lukkascost.commons.module.repositories;

import br.com.lukkascost.commons.module.models.entities.DatasetEntity;
import br.com.lukkascost.commons.module.models.entities.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface ISampleRepository extends JpaRepository<SampleEntity, UUID> , JpaSpecificationExecutor<SampleEntity> {

    List<SampleEntity> findAllByDataset(DatasetEntity datasetEntity);
}
