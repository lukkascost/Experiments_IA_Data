package br.com.lukkascost.commons.module.models.repositories;

import br.com.lukkascost.commons.module.models.entities.AttributeEntity;
import br.com.lukkascost.commons.module.models.entities.DatasetEntity;
import br.com.lukkascost.commons.module.models.entities.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ISampleRepository extends JpaRepository<SampleEntity, UUID> {

    List<SampleEntity> findAllByDataset(DatasetEntity datasetEntity);
}
