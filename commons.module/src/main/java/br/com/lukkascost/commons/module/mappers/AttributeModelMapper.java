package br.com.lukkascost.commons.module.mappers;

import br.com.lukkascost.commons.module.models.dto.AttributeDetailsDTO;
import br.com.lukkascost.commons.module.models.entities.AttributeEntity;

import java.util.List;

public abstract class AttributeModelMapper {
    public abstract List<AttributeDetailsDTO> convert(List<AttributeEntity> attributeEntityList);

}
