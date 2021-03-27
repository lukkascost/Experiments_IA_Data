package br.com.lukkascost.attributes.module.services;

import br.com.lukkascost.commons.module.models.entities.AttributeEntity;

import java.util.List;
import java.util.UUID;

public interface IAttributeService {

    List<AttributeEntity> findAll();

    List<AttributeEntity> findAllBySampleId(UUID sample_id);

}
