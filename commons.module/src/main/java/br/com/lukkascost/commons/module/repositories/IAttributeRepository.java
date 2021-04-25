package br.com.lukkascost.commons.module.repositories;

import br.com.lukkascost.commons.module.models.entities.AttributeEntity;
import br.com.lukkascost.commons.module.models.entities.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface IAttributeRepository extends JpaRepository<AttributeEntity, UUID> , JpaSpecificationExecutor<AttributeEntity> {

    List<AttributeEntity> findAllBySample(SampleEntity sampleEntity);
}
